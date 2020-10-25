let index = {
		init: function(){
			$("#btn-save").on("click",()=>{
				this.save();
			});
			$("#btn-delete").on("click",()=>{
				this.deleteById();
			});
			$("#btn-update").on("click",()=>{
				this.update();
			});
		},

	save: function(){
		let data = {
				title: $("#title").val(),
				content: $("#content").val()
				
		}
		
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8",//body 데이터가 어떤타입인지.
			dataType: "json"//서버로부터 응답이 왔을때 
		}).done(function(resp){
			alert("글쓰기 완료");	
			alert(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax로 데이터를 json으로 insert요청
	},
		
	deleteById: function(){
			var id = $("#id").text();
			$.ajax({
				type: "DELETE",
				url: "/api/board/"+id,
				dataType: "json"//서버로부터 응답이 왔을때 
			}).done(function(resp){
				alert("DELETE");	
				alert(resp);
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); //ajax로 데이터를 json으로 insert요청
		}
	,
	update: function(){
		let id = $("#id").val();
		
		let data={
				title: $("#title").val(),
				content: $("#content").val()
		};
		console.log(data);
		
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"//서버로부터 응답이 왔을때 
		}).done(function(resp){
			alert("UPDATE!");	
			alert(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax로 데이터를 json으로 insert요청
	}

}

index.init();
