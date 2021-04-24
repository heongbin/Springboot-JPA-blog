<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
  <form>
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		
		<div>
		  <textarea rows="5" class="form-control" id="editor"></textarea>
		</div>
		
		<button id="btn-save" class="btn btn-primary">±Û¾²±â</button>	
	</form>
</div>

<script>
  var quill = new Quill('#editor', {
    theme: 'snow'
  });
</script>
 <script src="/js/board.js"></script>
<!--

//-->

<%@ include file="../layout/footer.jsp" %>