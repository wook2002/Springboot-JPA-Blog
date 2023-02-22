let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{
				this.save();
		});
			
//		let _this = this; // function(){} -> this를 바인딩해서 사용해야됨
//		init: function(){
//			$("#btn-save").on("click", function(){
//				_this.save();
//		});
	},

	save: function(){
//			alert("user의 save함수 호출됨");
//		console.log("save")
		
		// data => JS객체,, => ,
		let data = {
				username: $("#username").val(), 
				password: $("#password").val(),
				email: $("#email").val(),
		};
		
//		console.log(data);
		
		// jquery
		// ajax호출 시 default는 비동기
		// 통신 성공하면 서버가 json을 리턴해주면 서버가 자동으로 자바객체로 변환해주네
		$.ajax({
			// 요청 (url에 보통 /api/user/join 이렇게 안함 POST는 어차피 Insert라
			type:"POST",
			url:"/blog/api/user",
			data: JSON.stringify(data), // js객체 -> JSON 문자열 (http body데이터)
			contentType: "application/json; charset=utf-8", // data정보 알려줌(body가 어떤 타입인지(MIME))
			dataType:"json" // 응답은 기본적으로 버퍼라서 모든게 문자열 -> 생긴게 json이라면 -> js객체로 변환 
					// => (사실 없어도 자동으로 됨, 캐싱이 안돼서 그런가?)(암튼 캐시 지워도 자동변환됨)
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.")
			console.log(resp);
//			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error)); // error도 json으로 날라옴
		});
		
	}
}

index.init();