

document.addEventListener("DOMContentLoaded", function() {
	const pageElements = document.getElementsByClassName("page-link");
	Array.from(pageElements).forEach(function(element) {
		element.addEventListener('click', function() {
			document.getElementById('page').value = this.dataset.page;
			document.getElementById('searchForm').submit();
		});
	});

	const btnSearch = document.getElementById("btn_search_food");
	btnSearch.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_food').value;
		document.getElementById('page').value = 0;
		document.getElementById('searchForm').submit();
	});

	// Extract opening hours and compare with current time
	const foodEntries = document.getElementsByClassName("food-entry");
	Array.from(foodEntries).forEach(function(entry) {
		const openingHours = entry.getElementsByClassName("shop-opening-text")[0].textContent.trim().split(" ~ ");
		const currentTime = new Date();
		const currentHour = currentTime.getHours();

		const openingTime = parseInt(openingHours[0].split(":")[0]);
		const closingTime = parseInt(openingHours[1].split(":")[0]);

		const resultOpening = entry.getElementsByClassName("result_opening")[0];
		if ((currentHour >= openingTime && currentHour < closingTime) ||
			(closingTime < openingTime && (currentHour < closingTime || currentHour >= openingTime))) {
			resultOpening.textContent = "영업 중";
		} else {
			resultOpening.textContent = "영업 종료";
		}
	});

});


document.addEventListener("DOMContentLoaded", function() {
	const foodShopPhotos = document.getElementsByClassName("food_shop_photo");

	Array.from(foodShopPhotos).forEach(function(element) {
		// Get the total number of images in the folder
		const numImages = 100; // Replace with the actual number of images in the folder

		// Generate a random number between 1 and numImages
		const randomNumber = Math.floor(Math.random() * numImages) + 1;

		// Set the background image URL
		const imageURL = `/img/tour/food/shop/${randomNumber}.jpg`;

		// Set the background image for the current element
		element.style.backgroundImage = `url('${imageURL}')`;
	});
	  
	       function searchFilter() {
        // 드롭다운과 체크박스에서 선택한 값들을 가져옴
        var region = document.getElementById("region_dropdown").value;
        var koreanFood = document.getElementById("korean_food").checked;
        var dessert = document.getElementById("dessert").checked;
        var teaCoffee = document.getElementById("tea_coffee").checked;
        var chineseFood = document.getElementById("chinese_food").checked;
        var japaneseFood = document.getElementById("japanese_food").checked;
        var westernFood = document.getElementById("western_food").checked;
        var fusionBuffet = document.getElementById("fusion_buffet").checked;
        var bar = document.getElementById("bar").checked;
        var other = document.getElementById("other").checked;

        // GET 요청을 위한 URL 생성
        var url = "/tour/daegu_food?kw=";

        // 선택한 값들을 배열에 추가
        var keywords = [];
        if (koreanFood) {
            keywords.push("한식");
        }
        if (dessert) {
            keywords.push("디저트");
        }
        if (teaCoffee) {
            keywords.push("차·커피");
        }
        if (chineseFood) {
            keywords.push("중식");
        }
        if (japaneseFood) {
            keywords.push("일식");
        }
        if (westernFood) {
            keywords.push("양식");
        }
        if (fusionBuffet) {
            keywords.push("퓨전·뷔페");
        }
        if (bar) {
            keywords.push("술집");
        }
        if (other) {
            keywords.push("기타");
        }

        // 키워드들을 '+'로 구분하여 URL에 추가
        url += keywords.join('+') + '+' + region;

        // GET 요청 실행
        window.location.href = url;
    }

    // 검색할래요 버튼에 클릭 이벤트 리스너 추가
    document.getElementById("search_filter_btn").addEventListener("click", searchFilter);
	  
    
	  
});


