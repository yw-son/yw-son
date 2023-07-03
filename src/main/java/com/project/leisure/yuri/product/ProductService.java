package com.project.leisure.yuri.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	// 해당 product_id로 상품의 정보를 조회
	public Product getProduct(Long product_id) {
		Optional<Product> findProduct = this.productRepository.findById(product_id);
		if (findProduct.isPresent()) {
			Product product = findProduct.get();

			return product;
		} else {
			return null; // 또는 예외 처리 등 추후 작성
		}
	}

	// 해당 상품의 pk를 가져와서 img 앤티티 연결
	public Integer updateImg(MultipartFile product_photo, Long product_id) {

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
		return 1;

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
	
	
	


	// 상품 수정 기능 구현
	public Integer updateProduct(Long product_id, String product_type, Integer product_count, Integer product_amount,
			Integer product_pernum, LocalTime product_checkin, LocalTime product_checkout) {
		Optional<Product> productOptional = productRepository.findById(product_id);

		if (productOptional.isPresent()) {
			Product product = productOptional.get();

			product.setProduct_type(product_type);
			product.setProduct_count(product_count);
			product.setProduct_amount(product_amount);
			product.setProduct_pernum(product_pernum);
			product.setCheckin(product_checkin);
			product.setCheckout(product_checkout);

			productRepository.save(product);

			return 1; // 성공
		}
		return 0; // 실패
	}

//	
//	// 해당상품 전체 삭제
//	public Integer pdDelete(Long product_id) {
//		Optional<Product> productOptional = this.productRepository.findById(product_id);
//		if (productOptional.isPresent()) {
//			Product product = productOptional.get();
//
//			// Product와 연결된 모든 ProductImg를 삭제
//			List<ProductImg> productImgs = product.getProductImgs();
//			for (ProductImg productImg : productImgs) {
//
//				String baseDirectory = "src/main/resources/static/";
//				String imgPath = productImg.getImg_url(); // 이미지 파일의 경로
//
//				String fileImgPath = baseDirectory + imgPath;
//
//				try {
//
//					Path imagePath = Paths.get(fileImgPath);
//					Files.delete(imagePath); // 서버 파일 삭제
//
//					System.out.println("이미지 파일 삭제: " + imagePath);
//
//				} catch (IOException e) {
//					System.out.println("이미지 파일 삭제 실패: " + fileImgPath);
//					e.printStackTrace();
//				}
//
//				// 외래키 제약 조건으로 인해서 1.서버 이미지 삭제 2.데이터 베이스 이미지 삭제
//				// 그리고 for문 다 돌리고 PK 존재인 3.해당 상품 삭제 순으로 간다
//
//				productImgRepository.delete(productImg); // 데이터 베이스 이미지 삭제
//			}
//			productRepository.delete(product); // 해당 상품 삭제
//
//			return 1; // 성공
//		}
//		return 0; // 실패
//
//	}

	// 상품 전체 삭제
	public Integer pdDelete(Long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();

			List<ProductImg> productImgs = product.getProductImgs();
			for (ProductImg productImg : productImgs) {
				deleteImage(productImg);
				productImgRepository.delete(productImg);
			}

			productRepository.delete(product);

			return 1; // 성공
		}

		return 0; // 실패
	}

	// 해당 이미지 삭제기능
	public void deleteImage(ProductImg productImg) {
		String baseDirectory = "src/main/resources/static/";
		String imgPath = productImg.getImg_url();
		String fileImgPath = baseDirectory + imgPath;

		try {
			Path imagePath = Paths.get(fileImgPath);
			Files.delete(imagePath);
			System.out.println("이미지 파일 삭제: " + imagePath);
		} catch (IOException e) {
			System.out.println("이미지 파일 삭제 실패: " + fileImgPath);
			e.printStackTrace();
		}
	}

	// 상품 수정 이미지 삭제
	public Integer pdEditImgDelete(List<Long> deletedImageIds) {
		for (Long imageId : deletedImageIds) {
			Optional<ProductImg> productImgOptional = productImgRepository.findById(imageId);
			if (productImgOptional.isPresent()) {
				ProductImg productImg = productImgOptional.get();
				deleteImage(productImg);
				productImgRepository.delete(productImg);
			}
		}
		return 1; // 성공
	}

}
