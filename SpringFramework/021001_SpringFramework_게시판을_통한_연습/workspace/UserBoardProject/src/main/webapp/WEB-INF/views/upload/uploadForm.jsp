<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../include/header.jsp" %>
	
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" />
	</form>
	
<%@ include file="../include/footer.jsp" %>
