package com.project.leisure.yuri.product;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.leisure.DataNotFoundException;
import com.project.leisure.taeyoung.user.UserRepository;
import com.project.leisure.taeyoung.user.Users;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
public class ProductController {

	private final ProductService productService;
	private final UserRepository userRepository;


	// 숙소 등록 페이지 (파트너만 접속 가능)
	@GetMapping("/addproduct")
	public String setAddProduct(Principal principal) {
		String username = principal.getName();
		Optional<Users> _siteUser = this.userRepository.findByusername(username);
		int partner = _siteUser.get().getPartner_code();

		if (!(partner > 3000)) {
			throw new DataNotFoundException("접근 권한이 없습니다.");
		}

		return "pyr/add_product";
	}

	//파트너 상품 등록  이 곳에 파트너 아이디도 추가해야 한다. 
	@PostMapping("/addproduct")
	public String addProduct(@RequestParam("product_photo") MultipartFile[] product_photo,
			@RequestParam("product_type") String product_type, @RequestParam("product_count") Integer product_count,
			@RequestParam("product_amount") Integer product_amount,
			@RequestParam("product_pernum") Integer product_pernum,
			@RequestParam("product_checkin") LocalTime product_checkin,
			@RequestParam("product_checkout") LocalTime product_checkout)

	{

		// 상품 등록 처리
		long product_id = this.productService.pdCreate(product_type, product_count, product_amount, product_pernum,
				product_checkin, product_checkout);

		// for 문으로 여려개의 이미지를 상품PK와 연결
		for (MultipartFile product_photos : product_photo) {
			this.productService.updateImg(product_photos, product_id); // updateImg 메서드는 이미지를 저장하고 연결하는 로직을 구현해야 합니다.

		}

		return "redirect:/product/addproduct";
	}

	// 등록한숙소 보여주기
	@GetMapping("/product_List")
	public String productList(Model model) {

		// 전체 리스트를 불러옴
		List<Product> productList = this.productService.getList();
		model.addAttribute("productList", productList);

		// 전체 리스트의 product_id에 해당하는 첫번째 이미지 사진을 보여준다.
		List<ProductImg> productImgList = new ArrayList<>();
		for (Product product : productList) {
			List<ProductImg> productImgs = product.getProductImgs();
			if (!productImgs.isEmpty()) {
				ProductImg productImg = productImgs.get(0);
				productImgList.add(productImg);
			}

		}
		model.addAttribute("productImgList", productImgList);

		return "pyr/product_List";
	}

}
