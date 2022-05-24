<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	
<%@ include file="../include/header.jsp" %>

<!-- Handlebars Script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

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

<!-- Reply -->
<div class="row">
	<div class="col-md-12">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>
			</div>
		</div>
		
		<div class="box-body">
			<label for="exampleInputEmail1">Writer</label>
			<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter" />
			
			<label for="exampleInputEmail1">Reply Text</label>
			<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText" />
		</div>
		
		<div class="box-footer">
			<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
		</div>
	</div>
</div>

<ul class="timeline">
	<li class="time-label" id="repliesDiv" data-vo="${boardVO}">
		<span class="bg-green">
			Replies List <small id="replycntSmall">[ ${boardVO.replycnt} ]</small>
		</span>
	</li>
</ul>

<div class="text-center">
	<ul id="pagination" class="pagination pagination-sm no-margin">
	</ul>
</div>

<!-- ModifyModal -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					&times;
				</button>
				<h4 class="modal-title"></h4>
			</div>
			
			<div class="modal-body" data-rno>
				<p>
					<input type="text" id="replytext" class="form-control" />
				</p>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
				<button type="button" class="btn btn-danger" id="replyDelBtn">Delete</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<style>
	.popup {
		position: absolute;
	}
	
	.back {
		background-color: gray;
		opacity: 0.5;
		width: 100%;
		height: 300%;
		overflow: hidden;
		z-index: 1101;
	}
	
	.front {
		z-index: 1110;
		opacity: 1;
		border: 1px;
		margin: auto;
	}
	
	.show {
		position: relative;
		max-width: 1200px;
		max-height: 800px;
		overflow: auto;
	}
</style>

<div class="popup back" style="display: none;"></div>
<div id="popupFront" class="popup front" style="display: none;">
	<img id="popupImg" />
</div>

<%@ include file="../include/footer.jsp" %>

<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="reply-li" data-rno="{{rno}}">
			<i class="fa fa-comments bg-blue"></i>
			<div class="timeline-item">
				<span>
					<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
				</span>
				<h3 class="timeline-header">
					<strong>{{rno}}</strong> -{{replyer}}
				</h3>
				
				<div class="timeline-body">{{replytext}}</div>
				
				<div class="timeline-footer">
					<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">
						Modify
					</a>
				</div>
			</div
		</li>
	{{/each}}
</script>

<script id="templateAttach" type="text/x-handlebars-template">
	<li data-src="{{fullName}}">
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgsrc}}" alt="Attachment" />
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
		</div>
	</li>
</script>

<script>
	const bno = ${boardVO.bno},
		  replyPage = 1;
	
	$(document).ready(function() {
		
		const template = Handlebars.compile($('#templateAttach').html());
		
		function getPage(pageInfo) {
			$.getJSON(pageInfo, function(data) {
				printData(data.list, $('#repliesDiv'), $('#template'))
				printPaging(data.pageMaker, $('.pagination'));
				
				$('#modifyModal').modal('hide');
				$('replycntSamll').html("[ " + data.pageMaker.totalCount + " ]");
			});
		}
		
		var printPaging = function(pageMaker, target) {
			let str = '';
			
			if(pageMaker.prev)
				str += '<li><a href="'+(pageMaker.startPage-1)+'"> << </a></li>';
			
			for(let i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++) {
				const strClass = pageMaker.cri.page == i? 'class="active"' : '';
			}
			
			if(pageMaker.next)
				str += '<li><a href="'+(pageMaker.endPage+1)+'"> >> </a></li>';
		}
		
		Handlebars.registerHelper('prettifyDate', function(timeValue) {
			const dateObj = new Date(timeValue),
				  year = dateObj.getFullYear(),
				  month = dateObj.getMonth() + 1,
				  date = dateObj.getDate();
			return year+"/"+month+"/"+date;
		});
		
		/* Handlebars Template 구현 */
		const printData = function(replyArr, target, templateObject) {
			const template = Handlebars.compile(templateObject.html());
			const html = template(replyArr);
			$('.reply-li').remove();
			target.after(html);
		};
		
		/* 목록 가져오는 버튼만 보일경우 1페이지 댓글 목록 가져오기 */
		$('#repliesDiv').on('click', function() {
			if($('.timeline li').size() > 1) return;
			getPage('/replies/' + bno + '/1');
		});
		
		$('.pagination').on('click', 'li a', function(event) {
			event.preventDefatul();
			replyPage = $(this).attr('href');
			getPage('/replies/' + bno + '/' + replyPage);
		});
		
		/* 댓글 등록 */
		$('#replyAddBtn').on('click', function() {
			const replyerObj = $('#newReplyWriter'),
				  replytextObj = $('#newReplyText'),
				  replyer = replyerObj.val(),
				  replytext = replytextObj.val();
			
			const data = JSON.stringify(
				{bno:bno, replyer:replyer, replytext: replytext}
			);
			
			$.ajax({
				type: 'POST',
				url: '/replies/',
				headers: {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'POST'
				},
				dataType: 'text',
				data : JSON.stringify(
					{bno:bno, replyer:replyer, replytext: replytext}
				),
				success: function(result) {
					console.log('result', result);
					if(result == 'SUCCESS') {
						alert('등록 되었습니다.');
						replyPage = 1;
						getPage('/replies/' + bno + '/' + replyPage);
						replyerObj.val('');
						replytextObj.val('');
					}
				}
			});
		});
		
		/* 댓글 수정 */
		$('.timeline').on('click', '.reply-li', function(event) {
			const reply = $(this);
			$('#replytext').val(reply.find('.timeline-body').text());
			$('.modal-title').html(reply.attr('data-rno'));
		});
		
		$('#modifyModal').on('click', '#replyModBtn', function() {
			const rno = $('.modal-title').html();
			const replytext = $('#replytext').val();
			
			const data = JSON.stringify(
				{replytext: replytext}
			);
			$.ajax({
				type: 'PUT',
				url: '/replies/'+rno,
				headers: {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'PUT'
				},
				dataType: 'text',
				data : data,
				success: function(result) {
					console.log('result', result);
					if(result == 'SUCCESS') {
						alert('수정 되었습니다.');
						getPage('/replies/' + bno + '/' + replyPage);
					}
				}
			});
			
		}).on('click', '#replyDelBtn', function() {
			const rno = $('.modal-title').html();
			const replytext = $('#replytext').val();
			
			$.ajax({
				type: 'DELETE',
				url: '/replies/'+rno,
				headers: {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'DELETE'
				},
				dataType: 'text',
				success: function(result) {
					console.log('result', result);
					if(result == 'SUCCESS') {
						alert('삭제 되었습니다.');
						getPage('/replies/' + bno + '/' + replyPage);
					}
				}
			});
		});
		
		function callAjax(httpMethod, url, dataTpe, data, successCallback) {
			$.ajax({
				type: httpMethod,
				url: url,
				headers: {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : httpMethod
				},
				dataType: dataTpe,
				data : data,
				success: function(result) {
					console.log('result', result);
					if(successCallback) {
						successCallback();
					}
				}
			});
		}
		
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
	
	$.getJSON('/sboard/getAttach/'+bno, function(list) {
		$(list).each(function() {
			const fileInfo = getFileInfo(this);
			const html = template(fileInfo);
			$('.uploaded-list').append(thml);
		});
	});
	
	$('.uploaded-list').on('click', 'mailbox-attachment-info a', function(event) {
		const fileLink = $(this).attr('href');
		
		if(checkImageType(fileLink)) {
			event.preventDefault();
			let imgTag = $('#popupImg');
			imgTag.attr('src', fileLink);
			console.log(imgTag.attr('src'));
			
			$('.popup').show('slow');
			imgTag.addClass('show');
		}
	});
	
	$('#popupImg').on('click', function() {
		$('.popup').hide('slow');
	});
	
	$('#removeBtn').on('click', function() {
		const replyCnt = $('#replycntSmall').html();
		if(replyCnt > 0) {
			alert('댓글이 달린 게시물을 삭제할 수 없습니다.');
			return;
		}
		
		let arr = [];
		$('.uploaded-list li').each(function(index) {
			arr.push($(this).data('src'));
		});
		
		if(arr.length > 0) {
			$.post('/upload/deleteAllFiles', {files:arr}, function() {
				
			});
		};
		
		formObj.aattr('action', '/sboard/removePage');
		formObj.submit();
	});
</script>