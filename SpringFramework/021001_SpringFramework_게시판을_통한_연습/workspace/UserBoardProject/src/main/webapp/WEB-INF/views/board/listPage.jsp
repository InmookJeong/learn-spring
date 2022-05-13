<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	
<%@ include file="../include/header.jsp" %>

<form id="jobForm">
	<input type="hidden" name="page" value="${pageMaker.cri.page}">
	<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>

<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>
	<c:forEach items="${list}" var="boardVO">
		<tr>
			<td>${boardVO.bno}</td>
			<td>
				<%-- <a href="/board/read?bno=${boardVO.bno}">
					${boardVO.title}
				</a> --%>
				<a href="/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}">
					${boardVO.title}
				</a>
			</td>
			<td>${boardVO.writer}</td>
			<td>
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/>
			</td>
			<td>
				<span class="badge bg-red">
					${boardVO.viewcnt}
				</span>
			</td>
		</tr>
	</c:forEach>
</table>

<div class="text-center">
	<ul class="pagination">
		<c:if test="${pageMaker.prev}">
			<li>
				<a href="listPage?page=${pageMaker.startPage - 1}">
					&laquo;
				</a>
			</li>
		</c:if>
		
		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			<li <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}" /> >
				<a href="listPage${pageMaker.makeQuery(idx)}">
					${idx}
				</a>
			</li>
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li>
				<a href="listPage?page=${pageMaker.endPage + 1}">
					&raquo;
				</a>
			</li>
		</c:if>
	</ul>
</div>

<%@ include file="../include/footer.jsp" %>

<script>
	const result = '${msg}';
	
	if(result == 'success') {
		alert('처리가 완료되었습니다.');
	}
	
	/*
		페이지 처리에 대해 PageMaker를 사용할 수도 있지만
		아래 작성된 Script를 통해서도 처리 가능
		- 반복적으로 개발해야 할 경우 PageMaker 또는 Script 방식이 효율적
	*/
	/* $('.pagenation li a').on('click', function(event) {
		event.preventDefault();
		const targetPage = $(this).attr('href');
		const jobForm = $('#jobForm');
		jobForm.find('[name="page"]').val(targetPage);
		jobForm.attr('action', '/board/listPage').attr('method', 'get');
		jobForm.submit();
	}); */
</script>