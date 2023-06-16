# leisure_PJT
leisure_PJT 개발 

<br>
1


# 개발환경
- IDE  : STS 4.18.1
- Framework : SpringBoot 3.0.1
- Language : JAVA 17 , HTML5, CSS3, JavaScript, JQuery
- Database : MySQL 8.0.(데이터베이스명 : leisure_project)


<br>


# 리더가 할일( 이 부분 리더말고  건들지마세요!)
1. 프론트 관련 JSP 사용할지, thymeleaf 사용할지 정해지면 해당 라이브러리 추가하기



<br>
<br>
<br>



# 배포 받은 후 할 일 


1. 자신의 MySQL의 접속을 위해 아래 내용 확인하기 
   - 로그인을 위한 계정정보가 ID :  root , PW : 1234  맞는지 확인 하기
   - MySQL의 포트가 3306으로 되어있는지 확인하기

3. MySQL 접속 후  Create database leisure_project 명령어 실행하여 
   데이터베이스 생성
   
   
3.  아래  사진과 같이 src/main/java/com/project/leisure 경로에서 
    마우스 우클릭  -> New -> Package 를 눌러 본인이 사용할 Package를 생성 
![image](https://github.com/taeyoung0504/SpringBootStudy/assets/128016593/3e25259c-b2b3-4d14-aee0-571b70a729fc)





패키지명은 본인이름으로 생성 후 각자 작업 진행<br>

![image](https://github.com/taeyoung0504/SpringBootStudy/assets/128016593/ec2a0aee-4c73-43d6-b124-f1e2d416a015)

<br>






# 개발 진행 시 참고사항 
   
 1. build.gradle 에서 라이브러리 추가 필요한 경우 아래 순서대로 <br>
 
     (1) 이미 중복된 라이브러리가 아닌지 확인 <br>
     
     (2) 중복 된 것이 없어  라이브러리 추가 해야할때 추가하기 전  pull로 내려받은 후 <br>
         다른사람이 이미 라이브러리를 등록하지 않았는지 다시 확인  <br>
   
   <br>
   
   
2. 아래와 같이 html 생성 시 head에 css 파일을 추가 해야할 때 <br>
   본인만의 css파일을 만들어 다른사람과 충돌이 일어나지 않게 하기 (즉, 우리 프로젝트에서는 7개의 css 파일이 있어야함) 
 ![image](https://github.com/taeyoung0504/leisure_Project/assets/128016593/37ec0e98-8434-420c-829f-6e9b20454bda)

<br>
3.  아래의 표시 된 부분은 css 파일과 이미지 파일을 넣는 기본 디렉토리이다.

![image](https://github.com/taeyoung0504/SpringBootStudy/assets/128016593/baf9f96b-e68d-47b9-9bb1-647a698083ae)

<br>

4. 아래와 같이 css파일에 내용을 작성 할 때 선택자는 태그 그 자체가 아닌 <br>
   수정을 원하는 태그안에 본인만의 id를 지정하여 선택자로 한다 <br>
 * 이렇게 안하면 공통된 태그(ex. div , form)를 선택자로 한 팀원들의 이 충돌남! <br>

<잘못된 사용> <br>
 form { <br>
       font-size : 50px; <br>
         } <br>

<올바른 사용> <br>
 #kty_form{ <br>
   fonnt-size:50px; <br>
} <br>


<br>



5. branch는  본인 이름 이니셜로! <br>  
   ex )  김태영이 생성하는 경우   <br>
         git checkout -b kty    <br>
   
   참고 : master 브랜치로 올리면 진짜 크게 혼납니다.
   <br>
   
   
   
   
   
 ###  <b> 매우 중요!!! <br>
   
깃허브에 소스코드 올리는 법 
   
   (1) 본인이 사용할 브랜치 생성 (최초 1회 진행) <br>
       git checkout -b 개인 브랜치명
   
   (2) 개발 작업 시작 전 다른 사람 작업한 걸 반영하기 위해 main 브랜치에서 <br>
       업데이트 된 내용 가져오기 
       명령어 :      git pull origin main

   (3) 개발작업 하기 

   (4) 개발작업이 완료 된 경우 아래 명령어 써서 변경분 모으기 <br>
       3-1. git add (작업 파일)   또는  git add .     <br>
  

        *  git add (작업 파일) 은 push 할 파일이 적을 때 사용 
        *  git add . 은 push 할 파일이 많을 때 
   
   
  (5) commit 진행 <br>
   
       3-2. git commit -m "작업 내용에 대한 메세지 문구" 
   
   (6) 다시 내가 작업한 동안 변경 사항 반영을 위해 pull 진행 <br>
      - git pull origin main 
   
   
   (7) push 하기
   
     - git push -u origin  개인 브랜치명 
   
   
   
   
   위의 과정을 진행 후 <br>
   (2) ~ (7) 번 과정의 반복으로 프로젝트 진행함.
   
   
   
   
   
   
   
   
