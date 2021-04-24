let index = {
		init: function(){
			$("#btn-save").on("click",()=>{
				this.save();
			});
			$("#btn-update").on("click",()=>{
				this.update();
			});
		},

	save: function(){
		let data = {
				username: $("#username").val(),
				email: $("#email").val(),
				password: $("#password").val(),
				//hrsf: $("_crsf").val()
				
		}
		
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8",//body 데이터가 어떤타입인지.
			dataType: "json"//서버로부터 응답이 왔을때 
		}).done(function(resp){
			if(resp.status===500){
				alert("회원가입 성공")
			}
			else{
				alert("fail");
				location.href="/";
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax로 데이터를 json으로 insert요청
	},
	
	update: function(){
		let data = {
				id: $("#id").val(),
				username: $("#username").val(),
				email: $("#email").val(),
				password: $("#password").val(),
				//hrsf: $("_crsf").val()
		}
		
		console.log(data);
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8",//body 데이터가 어떤타입인지.
			dataType: "json"//서버로부터 응답이 왔을때 
		}).done(function(resp){
			alert("회원 UPDATE 성공");	
			alert(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax로 데이터를 json으로 insert요청
	}
	
	
	
	/*login: function(){
		let data = {
				username: $("#username").val(),
				password: $("#password").val()
		}
		
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8",//body 데이터가 어떤타입인지.
			dataType: "json"//서버로부터 응답이 왔을때 
		}).done(function(resp){
			alert("로그인 성공");	
			alert(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax로 데이터를 json으로 insert요청
	}*/
}

index.init();
