$(function() {
	$(document).ready(function() {
		$("#mypage_email_modify").on("click", function() {
			var modifyEmailInput = $("#modify_email_input");
			if (modifyEmailInput.css("display") === "none") {
				modifyEmailInput.css("display", "block");
			} else {
				modifyEmailInput.css("display", "none");
			}
		});
	});

	var $modify_email = $('.modify_email_input');
	var $modify_email_check_button = $('#modify_email_input_button');

	$modify_email_check_button.on('click', function() {

		var check_modify_email = $modify_email.val();
		alert(check_modify_email);

	});
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
});