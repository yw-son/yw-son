$(function() {
	/* 비밀번호 변경 활성화 */
	$(document).ready(function() {
		$("#mypage_password_modify").on("click", function() {
			var modifyEmailInput = $("#modify_password_input");
			if (modifyEmailInput.css("display") === "none") {
				modifyEmailInput.css("display", "block");
			} else {
				modifyEmailInput.css("display", "none");
			}
		});
	});

/* 페이지 새로 고침 시 프로필 사진 변경 */
	$(document).ready(function() {
		var profileImages = [
			"pro_img1.jpg",
			"pro_img2.jpg",
			"pro_img3.jpg",
			"pro_img4.jpg",
			"pro_img5.jpg"
		];


		var randomIndex = Math.floor(Math.random() * profileImages.length);

	
		$("#profile_lion").attr("src", "/img/profile_img/" + profileImages[randomIndex]);
	});
	
/* 주소 부분 */
  var $mypage_addr_modify = $('#mypage_addr_modify');


  if ($mypage_addr_modify.length === 0) {
    console.log("버튼이 존재하지 않거나 선택이 잘못되었습니다.");
    return;
  }


  $mypage_addr_modify.on('click', function() {

    
    alert('아직 미구현~');
  });
	
});