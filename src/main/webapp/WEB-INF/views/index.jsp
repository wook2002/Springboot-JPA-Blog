<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- /blog/" 컨텍스트까지 해줘야해서 -->
		<a class="navbar-brand" href="/blog">홈</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/user/join">회원가입</a></li>
			</ul>
		</div>
	</nav>
	<br>

	<div class="container">
	
	<!-- m-2 : margin -->
	
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목 부분</h4>
				<p class="card-text">내용 부분</p>
				<a href="#" class="btn btn-primary">상세 부분</a>
			</div>
		</div>
		
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목 부분</h4>
				<p class="card-text">내용 부분</p>
				<a href="#" class="btn btn-primary">상세 부분</a>
			</div>
		</div>
		
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목 부분</h4>
				<p class="card-text">내용 부분</p>
				<a href="#" class="btn btn-primary">상세 부분</a>
			</div>
		</div>
		
	</div>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>Created by Jae</p>
		<p>📞010-1111-1111</p>
		<p>🏴인천 연수구 XX동</p>
	</div>
</body>
</html>
