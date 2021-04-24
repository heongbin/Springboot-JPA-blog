<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
<button class ="btn btn-secondary" onclick = "history.back()">back</button>
<a href="/board/${board.id}/updateForm" class ="btn btn-warning">update</a>
<c:if test="${board.user.id == principal.user.id}">
<button id = "btn-delete" class ="btn btn-danger">delete</button>
</c:if>
<br/><br/>
<div>
	글 번호: <span id="id"><i>${board.id}</i></span>
	작성자: <span><i>${board.user.username}</i></span>
	날짜: <span><i>${board.createDate}</i></span>
	조회수: <span><i>${board.count}</i></span>
</div>
</br>
		<div>
			<h3>${board.title}</h3>
		</div>
		<hr/>
		<div>
		  <div>
		  	${board.content}
		  </div>
		</div>
		<hr/>
		
		<div class="card">
			<form>
				<input type="hidden" id="userId" value="${principal.user.id}"/>
				<input type="hidden" id="boardId" value="${board.id}"/>
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
				</div>
			</form>
		</div>
		
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자:${reply.user.username} <span>날짜 :${reply.createDate}  </span>&nbsp;</div>
						<button onClick="index.replyDelete(${board.id},${reply.id})" class="badge">delete</button>
					</div>
				</li>
			</c:forEach>
			</ul>
		</div>
</div>


<script src="/js/board.js"></script>
<!--

//-->

<%@ include file="../layout/footer.jsp" %>