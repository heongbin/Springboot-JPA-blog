let index = {
		init: function(){
			$("#btn-save").on("click",()=>{
				this.save();
			});
		},

	save: function(){
		let data = {
				username: $("#username").val(),
				email: $("#email").val(),
				password: $("#password").val()
				
		}
		
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8",//body 데이터가 어떤타입인지.
			dataType: "json"//서버로부터 응답이 왔을때 
		}).done(function(resp){
			alert("회원 가입 성공");	
			alert(resp);
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax로 데이터를 json으로 insert요청
	}
}

index.init();
