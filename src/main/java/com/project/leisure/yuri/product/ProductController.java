package com.project.leisure.yuri.product;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String setAddProduct(Principal principal, Model model) {
		String username = principal.getName();
		Optional<Users> _siteUser = this.userRepository.findByusername(username);
		int partner = _siteUser.get().getPartner_code();

		if (!(partner > 3000)) {
			throw new DataNotFoundException("접근 권한이 없습니다.");
		}

		// 전체 리스트를 불러와서 등록한 숙소를 보여준다.
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

		return "pyr/add_product";
	}

	// 파트너 상품 등록 이 곳에 파트너 아이디도 추가해야 한다.
	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestParam("product_photo") MultipartFile[] product_photo,
			@RequestParam("product_type") String product_type, @RequestParam("product_count") Integer product_count,
			@RequestParam("product_amount") Integer product_amount,
			@RequestParam("product_pernum") Integer product_pernum,
			@RequestParam("product_checkin") LocalTime product_checkin,
			@RequestParam("product_checkout") LocalTime product_checkout)

	{

		// 상품 등록 처리
		long product_id = this.productService.pdCreate(product_type, product_count, product_amount, product_pernum,
				product_checkin, product_checkout);

		// for 문으로 여려개의 이미지를 상품PK와 연결해서 저장
		for (MultipartFile product_photos : product_photo) {
			this.productService.updateImg(product_photos, product_id);
		}

		// 등록된 상품 조회 하여 prouct 저장
		Product product = this.productService.getProduct(product_id);

		// 성공하면 해당 product_id값을 보냄 추후 비동기로 상품 추가 보여준다.
		return ResponseEntity.ok(product);

	}

//	 
//	// 등록한숙소의 첫 번째 사진 보여주기
//	@GetMapping("/product_List")
//	public String productList(Model model) {
//
//		// 전체 리스트를 불러옴
//		List<Product> productList = this.productService.getList();
//		model.addAttribute("productList", productList);
//
//		// 전체 리스트의 product_id에 해당하는 첫번째 이미지 사진을 보여준다.
//		List<ProductImg> productImgList = new ArrayList<>();
//		for (Product product : productList) {
//			List<ProductImg> productImgs = product.getProductImgs();
//			if (!productImgs.isEmpty()) {
//				ProductImg productImg = productImgs.get(0);
//				productImgList.add(productImg);
//			}
//
//		}
//		model.addAttribute("productImgList", productImgList);
//
//		return "pyr/product_List";
//	}
//
//	

	// 버튼을 누르면 상품을 삭제한다
	@PostMapping("/deleteProduct")
	public ResponseEntity<Integer> deleateProduct(@RequestParam("productId") Long product_id) {

		int response = 0;

		response = this.productService.pdDelete(product_id);

		return ResponseEntity.ok(response);
	}

	// ====! 예외처리 필수 if-else 로 return된 값 1일 경우에만 다음 코드가 실행되도록 처리 !=========
	// 수정된 상품의 값, 이미지를 받아와서 적용
	@PostMapping("/updateProduct")
	public ResponseEntity<Integer> uploadFiles(@RequestParam("productId") Long product_id,
			@RequestParam("images[]") MultipartFile[] editedImages,
			@RequestParam("deletedImages[]") List<Long> deletedImageIds, @RequestParam("count") Integer count,
			@RequestParam("type") String type, @RequestParam("pernum") Integer pernum,
			@RequestParam("amount") Integer amount, @RequestParam("checkin") LocalTime checkin,
			@RequestParam("checkout") LocalTime checkout) {

		// 상품 조회 해당하는 상픔의 pk를 가져와서 조회한다.
		Product product = this.productService.getProduct(product_id);
		if (product == null) {
			throw new DataNotFoundException("상품을 찾을 수 없습니다.");
		}

		// 개별 이미지 삭제 (해당 이미지의 pk를 가져와서 삭제한다)
		int pdEditImgDelete = this.productService.pdEditImgDelete(deletedImageIds);

		// 들어온 이미지 추가 for 문으로 여려개의 이미지를 상품PK와 연결해서 저장
		for (MultipartFile product_photos : editedImages) {
			this.productService.updateImg(product_photos, product_id);
		}

		// 이제 값을 받은 부분들을 다시 업데이트 해야 한다.

		int updateProduct = this.productService.updateProduct(product_id, type, count, amount, pernum, checkin,
				checkout);

		return ResponseEntity.ok(updateProduct);
		// return null;
	}
}