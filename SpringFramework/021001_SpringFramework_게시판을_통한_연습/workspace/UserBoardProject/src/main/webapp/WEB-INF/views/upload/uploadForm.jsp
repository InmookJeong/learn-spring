<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../include/header.jsp" %>

<style>
	iframe {
		width: 0px;
		height: 0px;
		border: 0px;
	}
</style>
	
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="zeroFrame">
		<input type="file" name="file" />
		<input type="submit" />
	</form>
	
	<iframe name="zeroFrame"></iframe>
	
<%@ include file="../include/footer.jsp" %>

<script>
	function addFilePath(msg) {
		alert(msg);
		document.getElementById('form1').reset();
	}
</script>