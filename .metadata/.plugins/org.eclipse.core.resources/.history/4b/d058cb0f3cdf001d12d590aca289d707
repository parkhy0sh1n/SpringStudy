<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function fnEdit(){
		$('#edit_screen').show();
		$('#detail_screen').hide();
	}
	function fnRemove(){
		if(confirm('공지사항을 삭제할까요?')){
			location.href = '${contextPath}/notice/remove.do?notice_no=${notice.notice_no}';
		}
	}
	function fnList(){
		location.href = '${contextPath}/notice/list.do';
	}
	function fnBack(){
		$('#edit_screen').hide();
		$('#detail_screen').show();
	}
	function fnInit(){		
		$('#edit_screen').hide();
		$('#gubun').val(${notice.gubun});
		let modifyResult = '${modifyResult}';
		if(modifyResult != '') {
			if(modifyResult == '1'){
				alert('공지사항이 수정되었습니다.');
			} else {
				alert('공지사항이 수정되지 않았습니다.');
			}
		}
	}
	$(function(){		
		fnInit();
	})
</script>
</head>
<body>

	<div id="detail_screen">
		<h1>${n.notice_no}번 공지사항</h1>
		<div>구분 : ${n.gubun}</div>
		<div>제목 : ${n.title}</div>
		<div>${n.content}</div>
		<hr>
		<div>
			<input type="button" value="편집" onclick="fnEdit()">
			<input type="button" value="삭제" onclick="fnRemove()">
			<input type="button" value="목록" onclick="fnList()">
		</div>
	</div>
	
	<div id="edit_screen">
		<div style="cursor: pointer;" onclick="fnBack()">← 뒤로 가기</div>
		<h1>공지사항 편집하기</h1>
		<form>
			<div>
				<select name="gubun" id="gubun">
		            <option value="${n.gubun}">구분</option>
		            <option value="gin">긴급</option>
		            <option value="ill">일반</option>
        		</select>
			</div>
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="${n.title}">
			</div>
			<div>
				<div><label for="content">내용</label>	</div>
				<textarea id="content" name="content">${n.content}</textarea>
			</div>
			<div>
				<input type="hidden" name="notice_no" value="${n.notice_no}">
				<button>편집완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
	
</body>
</html>