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

	$(document).ready(function() {
		$("#modify_email_input_button").on("click", function() {
			alert('hello');
		});
	});

});