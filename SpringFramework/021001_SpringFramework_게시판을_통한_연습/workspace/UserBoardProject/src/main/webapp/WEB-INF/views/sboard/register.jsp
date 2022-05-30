<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	
<%@ include file="../include/header.jsp" %>

<script src="/resources/js/upload.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script id="template" type="text/x-handlebars-template">
	<li>
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgsrc}}" alt="Attachment" />
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
			<a href="{{fullName}}" class="btn btn-deafult btn-xs pull-right delbtn">
				<i class="fa fa-fw fa-remove"></i>
			</a>
		</div>
	</li>
</script>

<style>
	.file-drop {
		height: 100px;
		background-color: aliceblue;
	}
</style>

<form role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label>
			<input type="text" name="title" class="form-control" placeholder="Enter Title" />
		</div>
		
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3" placeholder="Enter Content"></textarea>
		</div>
		
		<div class="form-group">
			<label for="exampleInputEmail1" >Writer</label>
			<!-- <input type="text" name="writer" class="form-control" placeholder="Enter Writer" /> -->
			<input type="text" name="writer" class="form-control" value="${login.uid}" readonly="readonly" />
		</div>
		
		<div class="form-group">
			<label for="exampleInputEmail1" >File Drop Here</label>
			<div class="file-drop"></div>
		</div>
	</div>
	
	<div class="box-footer">
		<div>
			<hr>
		</div>
		
		<ul class="mailbox-attachments clearfix uploaded-list">
		</ul>
		
		<button type="submit" class="btn btn-primary">Save</button>
	</div>
	
</form>

<%@ include file="../include/footer.jsp" %>

<script>
	const template = Handlebars.compile($('#template').html());
	
	$('.file-drop').on('dragenter dragover', function(event) {
		event.preventDefault();
	}).on('drop', function(event) {
		event.preventDefault();
		
		// event.originalEvent를 통해 순수한 원래의 DOM 이벤트 가져오기
		const files = event.originalEvent.dataTransfer.files;
		const file = files[0];
		
		// FormData는 HTML5부터 지원
		const formData = new FormData();
		formData.append('file', file);
		
		$.ajax({
			url: '/upload/uploadAjax',
			data: formData,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(data) {
				const fileInfo = getFileInfo(data);
				const html = template(fileInfo);
				
				$('.uploaded-list').append(html);
			}, error: function(){
				alert('error...')
			}
		});
	});
	
	$('#registerForm').submit(function(event) {
		event.preventDefault();
		const that = $(this);
		const str = '';
		
		$('.uploaded-list .delbtn').each(function(index) {
			str += '<input type="hidden" name="files[' + index + ']" value="' + $(this).attr('href') + '" />'
		});
		
		that.append(str);
		that.get(0).submit();
	});
</script>