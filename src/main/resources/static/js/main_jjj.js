// 햄버거 영역
//  toggleBtn 변수에 .navbar_toogleBtn 클래스를 찾아서 변수에 연결
const toggleBtn = document.querySelector(".jj_navbar_toogleBtn");
const menu = document.querySelector(".jj_navbar_menu");

// 검색 영역 
const jj_search_input = document.querySelector(".jj_search_input");
const jj_search_btn = document.querySelector(".jj_search_btn");
const jj_search = document.querySelector(".jj_search");

// 슬라이드 영역
const jj_slide_container = document.querySelector(".jj_slide_container");

// 도트 영역
const jj_dot_btn1 = document.querySelector(".jj_dot_btn_slide-1");
const jj_dot_btn2 = document.querySelector(".jj_dot_btn_slide-2");
const jj_dot_btn3 = document.querySelector(".jj_dot_btn_slide-3");

// 햄버거 실행 
toggleBtn.addEventListener("click", () => {
    menu.classList.toggle("active");
});

// 검색 실행
jj_search_btn.addEventListener("click", () => {
    jj_search.classList.toggle("active");
    jj_search_input.focus();
});




// 슬라이드 버튼
document.querySelector('.jj_dot_btn_slide-1').addEventListener('click', function () {
    document.querySelector('.jj_slide_container').style.transform = 'translateX(0)';

});

document.querySelector('.jj_dot_btn_slide-2').addEventListener('click', function () {
    document.querySelector('.jj_slide_container').style.transform = 'translateX(-1200px)';

});

document.querySelector('.jj_dot_btn_slide-3').addEventListener('click', function () {
    document.querySelector('.jj_slide_container').style.transform = 'translateX(-2400px)';

});



// 추천 숙소 
const jj_recommend_slide = document.querySelector('.jj_recommend_slide');
const jj_recommend_slides = document.querySelector('.jj_recommend_slide li');
const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');

var currentIdx = 0, jj_recommend_slidesCount = jj_recommend_slides.length;


