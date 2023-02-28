<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- https://www.baeldung.com/spring-security-taglibs  -->
<!-- https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html -->
<!-- property="principal"  // 위에 사이트에서 확인-->
<!-- var="principal" => 변수임 -->

<!-- session에 있는 로그인객체를 principal에(PrincipalDetail.java)-->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal"  var="principal"/>

	<script>
		console.log("로그인 완료")
	</script>
</sec:authorize>


<!-- utf-8 소문자로 하니 에러 났다 안났다 -> 이런것도 대문자로-->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Jae</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<%-- <h1>${principal}</h1> --%>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">홈</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<c:choose>
		
			<%-- <c:when test="${empty sessionScope.principal}"> --%>
			<c:when test="${empty principal}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
		</div>
	</nav>
	<br />