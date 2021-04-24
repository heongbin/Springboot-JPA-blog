<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="layout/header.jsp" %>

<div class="container">
<c:forEach var="board" items="${boards.content}">
  <div  class="card m-2">
	  <div style="float:left" class="card-body">
	    <div><h4><a style="color:black" class="card-title" href="/board/${board.id}">${board.title}</a></h4></div>
	    
	    <div><a style="color:black" class="card-title">${board.createDate}</a></div>
	  </div>
	  
	</div>
</c:forEach>
</div>

<ul class="pagination justify-content-center">
<c:choose>
	<c:when test="${boards.first}">
		<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
	</c:when>
	
	<c:otherwise>
		<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${boards.last}">
		<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>	
	</c:when>
	
	<c:otherwise>
		<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
	</c:otherwise>
</c:choose>
  
</ul>

<%@ include file="layout/footer.jsp" %>