	$(function () {

			// username 유효성 검증
			var $username = $('#username');
			var $usernameError = $('#username-error');

			$username.on('input focusout', function () {
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
						data: {"username": username},
						dataType: 'json',
						success: function (data) {
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

			$password1.on('input focusout', function () {
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
			$checkEmailNumber.on('click', function () {
				var email = $email.val()
				emconfirmchk = false;
				if (email.trim() !== '') {
					$.ajax({
						url: "/user/emailcode",
						type: "POST",
						data: {
							"email": email
						},
						success: function (response) {
							data = response; // 인증번호 데이터 저장
							 Swal.fire({
       				 		title: 'Success',
        					html: '<b>인증번호를 발송하였습니다 3분안에 입력해주세요</b>',
        					icon: 'success'
      						});
                           
							$check.prop('disabled', false);
							startStopwatch($('#join_timer'));
							$checkEmailNumber.prop('disabled', true);
							$email.prop('readonly', true);
							$('#join_timer').show();
						},
						error: function () {
							 Swal.fire({
       				 		title: 'Error!',
        					html: '<b>인증번호 발송에 실패하였습니다 다시 시도해 주세요</b>',
        					icon: 'error'
      						});
							$email.prop('readonly', false);
						}
					});
				} else{
					$email_error.text('이메일을 입력하세요').show();
				}
				
			
			});

				$email.on('input focusout', function(){
					var email = $('#email').val();
					if(email.trim() !==''){
						$email_error.hide();
					}
				});


			$check.on("click", function () {
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
				interval = setInterval(function () {
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

      
		});