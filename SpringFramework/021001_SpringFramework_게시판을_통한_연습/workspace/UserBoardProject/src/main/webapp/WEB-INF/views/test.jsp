<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	<head>
		<script type="text/javascript" src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
		<title>Ajax Test</title>
		
		<style>
			#modDiv {
				width: 300px;
				height: 100px;
				background-color: gray;
				position: absolute;
				top: 50%;
				left: 50%;
				margin-top: -50px;
				margin-left: -150px;
				padding: 10px;
				z-index: 1000;
			}
			
			.d-none {
				display: none;
			}
		</style>
		
	</head>
	<body>
		
		<h2>Ajax Test Page</h2>
		
		<div>
			<div>
				REPLYER <input type="text" name="replyer" id="newReplyWriter" />
			</div>
			
			<div>
				REPLY TEXT <input type="text" name="replytext" id="newReplyText" />
			</div>
			
			<button id="replyAddBtn">ADD REPLY</button>
		</div>
		
		<ul id="replies" data-v="test">
		</ul>
		
		<ul class="pagination">
		</ul>
		
		<!-- 수정 Modal -->
		<div id="modDiv" class="d-none">
			<div class="modal-title"></div>
			<div>
				<input type="text" id="replytext" />
			</div>
			
			<div>
				<button type="button" id="replyModBtn">Modify</button>
				<button type="button" id="replyDelBtn">Delete</button>
				<button type="button" id="closeBtn">Close</button>
			</div>
		</div>
		
	</body>
</html>

<script>
	const bno = 10;
	const MOD_DIV = $('#modDiv');
	// getAllList();
	getPageList(1);
	
	function getAllList() {
		$.getJSON("/replies/all/"+bno, function(data) {
			console.log(data.length);
			let str = '';
			$(data).each(function() {
				str += '<li data-rno="'+this.rno+'" class="reply-li">';
				str += this.rno + " : " + this.replytext
				str += "<button>MOD</button>"
				str += "</li>"
			});
			$('#replies').html(str);
		});
	}
	
	$('#replyAddBtn').on('click', function() {
		
		const replyer = $('#newReplyWriter').val(),
			  replytext = $('#newReplyText').val();
		
		/*
			$.post(...)를 사용하게 되면 RestController에서 @RequestBody Annotaion이 제대로 처리 못함
		*/
		$.ajax({
			type: 'POST',
			url: '/replies',
			headers: {
				'Content-type' : 'application/json',
				'x-HTTP-Method-Override' : 'POST'
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success: function(result) {
				if(result == 'SUCCESS') alert('등록 되었습니다.');
				// getAllList();
				getPageList(1);
			}
		});
	});
	
	MOD_DIV.on('click', '#replyDelBtn', function() {
		const rno = $('.modal-title').html(),
			  replytext = $('#replytext').val();
	
		$.ajax({
			type: 'DELETE',
			url: '/replies/'+rno,
			headers: {
				'Content-type' : 'application/json',
				'x-HTTP-Method-Override' : 'DELETE'
			},
			dataType : 'text',
			success: function(result) {
				console.log('result : ', result);
				if(result == 'SUCCESS') alert('삭제 되었습니다.');
				closeModal();
				// getAllList();
				getPageList(1);
			}
		});
	}).on('click', '#replyModBtn', function() {
		const rno = $('.modal-title').html(),
			  replytext = $('#replytext').val();
		$.ajax({
			type: 'PUT',
			url: '/replies/'+rno,
			headers: {
				'Content-type' : 'application/json',
				'x-HTTP-Method-Override' : 'PUT'
			},
			dataType : 'text',
			data : JSON.stringify({
				replytext : replytext
			}),
			success: function(result) {
				console.log('result : ', result);
				if(result == 'SUCCESS') alert('수정 되었습니다.');
				closeModal();
				// getAllList();
				getPageList(replyPage)
			}
		});
	});
	
	$('#replies').on('click', '.reply-li button', function() {
		
		const reply = $(this).parent();
		const rno = reply.data('rno');
		const replytext = reply.text();
		
		$('.modal-title').html(rno);
		$('#replytext').val(replytext);
		MOD_DIV.show('slow');
		
	});
	
	function getPageList(page) {
		$.getJSON('/replies/'+bno+'/'+page, function(data) {
			console.log(data.list.length);
			let str = '';
			$(data.list).each(function() {
				str += '<li data-rno="'+this.rno+'" class="reply-li">';
				str += this.rno + " : " + this.replytext
				str += "<button>MOD</button>"
				str += "</li>"
			});
			
			$('#replies').html(str);
			printPaging(data.pageMaker);
		});
	}
	
	function printPaging(pageMaker) {
		let str = '';
		if(pageMaker.prev)
			str += '<li><a href="'+(pageMaker.startPage-1)+'"> << </a></li>';
		
		const len = pageMaker.endPage;
		for(let i=pageMaker.startPage; i<=len; i++) {
			const strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += '<li ' + strClass + '><a href='+i+'>' + i + '</a></li>';
		}
		
		if(pageMaker.next)
			str += '<li><a href="'+(pageMaker.endPage+1)+'"> >> </a></li>';
		
		$('.pagination').html(str);
	}
	
	$('#closeBtn').on('click', function() {
		closeModal();
	});
	
	function closeModal() {
		MOD_DIV.hide('slow');
	}
	
</script>