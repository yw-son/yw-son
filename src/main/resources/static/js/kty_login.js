$(function () {
  $(document).ready(function() {
    $("#kakao_login_btn").on("click", function() {
      location.href = "/oauth2/authorization/kakao";
    });

    $("#google_login_btn").on("click", function() {
      location.href = "/oauth2/authorization/google";
    });

    $("#naver_login_btn").on("click", function() {
      location.href = "/oauth2/authorization/naver";
    });
  });
});