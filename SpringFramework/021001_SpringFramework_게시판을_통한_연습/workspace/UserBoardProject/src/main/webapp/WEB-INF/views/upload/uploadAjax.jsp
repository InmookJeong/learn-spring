<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../include/header.jsp" %>

<style>
	.file-drop {
		width: 50em;
		height: 30em;
		border: 1px dotted blue;
		margin-left: 5em;
	}
	
	small {
		margin-left: 3px;
		font-weight: bold;
		color: gray;
	}
</style>
	
	<h3>Ajax File Upload</h3>
	<div class="file-drop"></div>
	<div class="uploaded-list"></div>
	
<%@ include file="../include/footer.jsp" %>

<script>
	$('.file-drop').on('dragenter dragover', function(event) {
		event.preventDefault();
	}).on('drop', function(event) {
		event.preventDefault();
		
		// event.originalEvent를 통해 순수한 원래의 DOM 이벤트 가져오기
		const files = event.originalEvent.dataTransfer.files;
		const file = files[0];
		console.log(file);
		
		// FormData는 HTML5부터 지원
		const formData = new FormData();
		formData.append('file', file);
		console.log('formData : ', formData)
		
		$.ajax({
			url: '/upload/uploadAjax',
			data: formData,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(data) {
				
				let str = "";
				if(checkImageType(data)) {
					str += "<div>";
					str += "<a href='displayFile?fileName="+getImageLink(data)+"'>";
					str += "<img src='displayFile?fileName="+data+"' />";
					str += "</a>";
					str += "<small data-src="+data+">";
					// str += data;
					str += "X";
					str += "</samll>";
					str += "</div>";
				} else {
					str += "<div><a href='displayFile?fileName="+data+"'>";
					str += getOriginalName(data);
					str += "</a>";
					str += "<small data-src="+data+">";
					str += " X";
					str += "</samll>";
					str += "</div>";
				}
				
				$('.uploaded-list').append(str);
			}, error: function(){
				alert('error...')
			}
		});
	});
	
	$('.uploaded-list').on('click', 'small', function(event) {
		const that = $(this);
		$.ajax({
			url: '/upload/deleteFile',
			type: 'post',
			data: {fileName:$(this).data('src')},
			dataType: 'text',
			success: function(result) {
				if(result == 'deleted') that.parent('div').remove();
			}
		});
	});
	
	function checkImageType(fileName) {
		const pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);
	}
	
	function getOriginalName(fileName) {
		if(checkImageType(fileName)) return;
		const idx = fileName.indexOf('_')+1;
		return fileName.substr(idx);
	}
	
	function getImageLink(fileName) {
		if(!checkImageType(fileName)) return;
		const front = fileName.substr(0,12);
		const end = fileName.substr(14);
		return front + end;
	}
</script>