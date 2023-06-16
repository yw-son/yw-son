package bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getParameter("file");
        String directory = getServletContext().getRealPath("/upload");
        File file = new File(directory + File.separator + fileName);

        if (file.exists()) {
            String mimeType = getServletContext().getMimeType(file.toString());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setContentLength((int) file.length());
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
            response.setHeader(headerKey, headerValue);

            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ServletOutputStream servletOutputStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    servletOutputStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.getWriter().println("File not found.");
        }
    }
}
