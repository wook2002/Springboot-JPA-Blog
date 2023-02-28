<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 상대경로 -->
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<!-- <form action="/user/join" method="POST"> -->
	<form>
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="username">Username:</label> 
			<!-- header.jsp에 pringcipal있음  -->
			<input type="text" value="${principal.user.username }" class="form-control" placeholder="Enter Username" id="username" readonly>
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>

		<!-- <button type="submit" class="btn btn-primary">회원가입완료</button> -->
	</form>

	<button id="btn-update" class="btn btn-primary">회원수정</button>

</div>

<!-- / 있는거랑 없는거 구분 : context관련, 상대,절대 -->
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>
