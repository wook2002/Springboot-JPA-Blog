let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{
				this.save();
			});
			$("#btn-delete").on("click", ()=>{
				this.deleteById();
			});
			$("#btn-update").on("click", ()=>{
				this.update();
			});
		},
		save: function(){
			let data = {
					title: $("#title").val(), 
					content: $("#content").val(),
			};
			$.ajax({
				type:"POST",
				url:"/api/board",
				data: JSON.stringify(data), 
				contentType: "application/json; charset=utf-8",
				dataType:"json" 
				}).done(function(resp){
					alert("글쓰기가 완료되었습니다.")
					console.log(resp);
					location.href="/";
				}).fail(function(error){
					alert(JSON.stringify(error)); 
				});
			},
			deleteById: function(){
//				var id = $("#id").val();
				let id = $("#id").text(); 
				console.log("js id : " + id)
				
				$.ajax({
					type:"DELETE",
					url:"/api/board/" + id,
					/*data: JSON.stringify(data), 
					contentType: "application/json; charset=utf-8",
					dataType:"json" */ // 데이터 보낼거 없음
					}).done(function(resp){
						alert("삭제가 완료되었습니다.")
						console.log(resp);
						location.href="/";
					}).fail(function(error){
						alert(JSON.stringify(error)); 
					});
				},
				
				update: function(){
					let id = $("#id").val();
					
					let data = {
							title: $("#title").val(), 
							content: $("#content").val(),
					};
					
//					console.log(id); // 11
//					console.log(data);  // {...}
					
					$.ajax({
						type:"PUT",
						url:"/api/board/"+id,
						data: JSON.stringify(data),
						contentType: "application/json; charset=utf-8",
						dataType:"json" 
						}).done(function(resp){
							alert("글수정이 완료되었습니다.")
							console.log(resp);
							location.href="/";
						}).fail(function(error){
							alert(JSON.stringify(error)); 
						});
				}
}

index.init();