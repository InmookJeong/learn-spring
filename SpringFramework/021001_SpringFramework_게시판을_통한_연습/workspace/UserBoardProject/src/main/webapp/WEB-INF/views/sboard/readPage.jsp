<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	
<%@ include file="../include/header.jsp" %>

<form role="form" method="post">
	<input type="hidden" name="bno" value="${boardVO.bno}" />
	<input type="hidden" name="page" value="${cri.page}" />
	<input type="hidden" name="perPageNum" value="${cri.perPageNum}" />
	<input type="hidden" name="searchType" value="${cri.searchType}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">TITLE</label>
		<input type="text" name="title" class="form-control" value="${boardVO.title}" readonly="readonly" />
	</div>
	
	<div class="form-group">
		<label for="exampleInputPassword1">content</label>
		<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
	</div>
	
	<div class="form-group">
		<label for="exampleInputPassword1">Writer</label>
		<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly" />
	</div>
</div>

<div id="boardBtnGroup" class="box-footer">
	<button type="submit" class="btn btn-warning">MODIFY</button>
	<button type="submit" class="btn btn-danger">REMOVE</button>
	<button type="submit" class="btn btn-primary">LIST</button>
</div>

<%@ include file="../include/footer.jsp" %>

<script>
	$(document).ready(function() {
		
		const formObj = $('form[role="form"]');
		
		$('#boardBtnGroup').on('click', '.btn-warning', function() {
			formObj.attr('action', '/sboard/modifyPage');
			formObj.attr('method', 'get');
			formObj.submit();
		}).on('click', '.btn-danger', function() {
			/* 목록으로 갈 때 Page 정보 유지를 위한 설정 */
			formObj.attr('action', '/sboard/removePage');
			formObj.submit();
		}).on('click', '.btn-primary', function() {
			/* 목록으로 갈 때 Page 정보 유지를 위한 설정 */
			/* List로 이동할 때 bno 값도 같이 보내주는 문제 해결 필요 */
			formObj.attr('action', '/sboard/list');
			formObj.attr('method', 'get');
			formObj.submit();
		});
		
	});
</script>