// 회원가입 js 부분 
function execPostCode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 도로명 조합형 주소 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
			if (fullRoadAddr !== '') {
				fullRoadAddr += extraRoadAddr;
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			//console.log(data.zonecode);
			//console.log(fullRoadAddr);


			$("[name=addr1]").val(data.zonecode);
			$("[name=addr2]").val(fullRoadAddr);

			/* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
			document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
			document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
		}
	}).open();
}

$(function() {

	// username 유효성 검증
	var $username = $('#join_username');
	var $usernameError = $('#username-error');

	$username.on('input focusout', function() {
		var username = $username.val();

		if (username.trim() === '') {
			$usernameError.text(' 아이디는 필수 값입니다.').show();
		} else if (username.length < 6 || username.length > 12) {
			$usernameError.text('아이디는 6-12자리여야 합니다.').show();
		} else {
			$usernameError.hide();

			$.ajax({
				type: 'POST',
				url: '/user/check', // 중복 아이디 검증을 위한 엔드포인트
				data: { "username": username },
				dataType: 'json',
				success: function(data) {
					if (data == 1) {
						$usernameError.text('이미 존재하는 아이디입니다.').show();
					} else {
						$usernameError.text('사용 가능한 아이디입니다.').show();
					}
				}
			});
		}
	});



	// password 유효성 검증
	var $password1 = $('#join_password1');
	var $passwordError = $('#password1-error');

	$password1.on('input focusout', function() {
		var password = $password1.val();

		var pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{6,12}$/;

		if (password.trim() === '') {
			$passwordError.text('패스워드는 필수 값입니다.').show();
		} else if (!pattern.test(password)) {
			$passwordError.text('패스워드 입력 규칙이 맞지 않습니다.').show();
		} else {
			$passwordError.hide();
		}
	});


	// 인증번호 발송
	var $checkEmailNumber = $('#checkEmailNumber');
	var $email = $('#email');
	var $email_error = $('#email-error');
	var $inputCode = $('#inputCode');
	var $inputCode_error = $('#inputCode_error');
	var $check = $('#check');
	var interval; // 전역 변수로 선언
	var data; // 인증번호 데이터 전역 변수로 선언
	//var $modal = $('#modal');
	//var $modal2 = $('#modal2');
	$checkEmailNumber.on('click', function() {
		var email = $email.val()
		emconfirmchk = false;
		if (email.trim() !== '') {
			$.ajax({
				url: "/user/emailcode",
				type: "POST",
				data: {
					"email": email
				},
				success: function(response) {
					data = response; // 인증번호 데이터 저장
					Swal.fire({
						title: 'Success',
						html: '<b>인증번호를 발송하였습니다 3분안에 입력해주세요</b>',
						icon: 'success'
					});
					stopInterval();
					$('#join_timer').hide();
					$check.prop('disabled', false);
					startStopwatch($('#join_timer'));
					$checkEmailNumber.prop('disabled', true);
					$email.prop('readonly', true);
					$('#join_timer').show();
				},
				error: function() {
					Swal.fire({
						title: 'Error!',
						html: '<b>인증번호 발송에 실패하였습니다 다시 시도해 주세요</b>',
						icon: 'error'
					});
					$email.prop('readonly', false);
				}
			});
		} else {
			$email_error.text('이메일을 입력하세요').show();
		}


	});

	$email.on('input focusout', function() {
		var email = $('#email').val();
		if (email.trim() !== '') {
			$email_error.hide();
		}
	});


	$check.on("click", function() {
		
		if (emconfirmchk == false && data == $inputCode.val()) {
			Swal.fire({
				title: 'Success',
				html: '<b>인증이 완료되었습니다.</b>',
				icon: 'success'
			});
			$('#join_timer').hide();
			$checkEmailNumber.prop('disabled', true);
			$email.prop('readonly', true);
			$signupButton.prop('disabled', false);
			$check.prop('disabled', true);
			clearInterval(interval); // 전역 변수인 interval 사용
		} else if (emconfirmchk == true && data == $inputCode.val()) {
			Swal.fire({
				title: 'Error!',
				html: '<b>인증번호가 맞지 않습니다 다시 확인해주세요.</b>',
				icon: 'error'
			});
			$email.prop('readonly', false);
			$checkEmailNumber.prop('disabled', false);
			emconfirmchk = false;
			$signupButton.prop('disabled', true);
		} else if (emconfirmchk == false && data !== $inputCode.val()) {
			Swal.fire({
				title: 'Error!',
				html: '<b>인증번호가 맞지 않습니다 다시 확인해주세요.</b>',
				icon: 'error'
			});
			$email.prop('readonly', false);
			$checkEmailNumber.prop('disabled', false);
			emconfirmchk = false;
			$signupButton.prop('disabled', true);
		} else if (emconfirmchk == true && data == $inputCode.val()) {
			Swal.fire({
				title: 'Error!',
				html: '<b>인증번호가 맞지 않습니다 다시 확인해주세요.</b>',
				icon: 'error'
			});
			$email.prop('readonly', false);
			$checkEmailNumber.prop('disabled', false);
			emconfirmchk = false;
			$signupButton.prop('disabled', true);
		}
	});


	// 스톱워치
	function startStopwatch(display) {
		
		var stopwatch = 180; // 초기 스톱워치 값 (60초)
		interval = setInterval(function() {
			var minutes = Math.floor(stopwatch / 60);
			var seconds = stopwatch % 60;

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			display.text(minutes + ":" + seconds);

			if (stopwatch <= 0) {
				clearInterval(interval); // 전역 변수인 interval 사용
				$('#join_timer').hide();
				$email.prop('readonly', false);
				emconfirmchk = true;
				Swal.fire({
					title: 'Warning!',
					html: '<b>인증번호가 만료되었습니다 다시 시도해주세요</b>',
					icon: 'warning'
				});
				$signupButton.prop('disabled', true);
				$checkEmailNumber.prop('disabled', false);
				$check.prop('disabled', true);
			}

			stopwatch--;
		}, 1000);
	}
function stopInterval() {
  clearInterval(interval);
}
	var $signupButton = $('button[type="submit"]');
	$signupButton.prop('disabled', true);
	/*
	$modal.on('click', function () {
		// 모달 창 영역을 클릭한 경우는 제외하고 숨기기
		  $modal.css('display', 'none');
		});
	  
	$modal2.on('click', function () {
		// 모달 창 영역을 클릭한 경우는 제외하고 숨기기
		  $modal2.css('display', 'none');
		});
	  */

	$(".join_title").on("click", function() {
			// 타임리프 URL을 정확히 지정해야 합니다.
			var url = "/";

			// 페이지 이동
			location.href = url;
		});
});


/* 로그인 js 부분 

$(document).ready(function() {
	$("#kakao_login_btn").click(function() {
		location.href = "/oauth2/authorization/kakao";
	});

	$("#google_login_btn").click(function() {
		location.href = "/oauth2/authorization/google";
	});

	$("#naver_login_btn").click(function() {
		location.href = "/oauth2/authorization/naver";
	}); 
});
*/
