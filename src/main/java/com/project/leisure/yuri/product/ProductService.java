package com.project.leisure.yuri.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductImgRepository productImgRepository;

	// Product에 대한 모든 것을 출력
	public List<Product> getList() {
		return this.productRepository.findAll();
	}

	// ProductImg에 대한 모든 것을 출력
	public List<ProductImg> getImgList() {
		return this.productImgRepository.findAll();
	}

	/*
	 * // 해당 product에 해당되는 이미지를 보여준다. public ProductImg getImg(Product product) {
	 * Optional<ProductImg> img = this.productImgRepository.findById(null); if
	 * (img.isPresent()) { return img.get(); } return null; }
	 */

	public ProductImg getImg(Product product) {
		List<ProductImg> productImgs = product.getProductImgs();
		if (!productImgs.isEmpty()) {
			return productImgs.get(0);
		}
		return null;
	}

	// 해당 상품의 pk를 가져와서 img 앤티티 연결
	public void updateImg(MultipartFile product_photo, Long product_id) {

		Optional<Product> productOptional = this.productRepository.findById(product_id);

		if (productOptional.isPresent()) {
			Product product = productOptional.get();

			try {
				// 파일을 저장할 경로와 파일명 설정
				String fileName = product_photo.getOriginalFilename(); // 원본 파일 이름을 반환
				String filePath = "src/main/resources/static/img/product_img/" + fileName;

				// 파일 저장
				Path targetPath = Path.of(filePath);
				Files.copy(product_photo.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

				// 이미지 URL 설정
				ProductImg productImg = new ProductImg();
				productImg.setImg_url("/img/product_img/" + fileName);

				// 이미지를 저장한다
				this.productImgRepository.save(productImg);

				// 이미지와 상품 연결
				productImg.setProduct(product); // 이미지와 상품 연결
				this.productRepository.save(product);

			} catch (IOException e) {
				// 예외 처리
				e.printStackTrace();
			}
		}

	}

	// 상품 등록 기능 구현
	public Long pdCreate(String product_type, Integer product_count, Integer product_amount, Integer product_pernum,
			LocalTime product_checkin, LocalTime product_checkout) {
		Product product = new Product();
		product.setProduct_type(product_type);
		product.setProduct_count(product_count);
		product.setProduct_amount(product_amount);
		product.setProduct_pernum(product_pernum);
		product.setCheckin(product_checkin);
		product.setCheckout(product_checkout);

		this.productRepository.save(product);

		return product.getProduct_id();
	}

}
