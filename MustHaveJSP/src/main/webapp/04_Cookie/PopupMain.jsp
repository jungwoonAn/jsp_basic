<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String popupMode = "on";  // 레이어 팝업창 띄울지 여부

// 쿠키를 읽어 popupMode값 설정
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie c : cookies){
		String cookieName = c.getName();
		String cookieValue = c.getValue();
		if(cookieName.equals("PopupClose")){  // "PopupClose"쿠키가 존재하면
			popupMode = cookieValue;  // popupMode값 변경
		}
	}
}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 이용한 팝업 관리</title>
<style type="text/css">
	#popup {
	position:absolute; top:100px; left:50px; color: yellow;
	width: 270px; height:100px; background-color: gray;
	}
	#popup>div {
	position: relative; background-color: #fff; top:0;
	border: 1px solid gray; padding: 10px; color: black;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#closeBtn').click(function(){
			$('#popup').hide();
			
			let chkVal = $('#inactiveToday:checked').val();  // 체크여부
			if(chkVal == 1){
				$.ajax({
					url: './PopupCookie.jsp',  // 쿠키 응답 객체로 요청
					type: 'get',
					data: {inactiveToday : chkVal},
					dataType: "text",
					success: function(resData){
						if(resData != '') location.reload();
					}
				})
			}
		})
	});
</script>
</head>
<body>
	<h2>팝업 메인 페이지</h2>
	<%
	for(int i=1; i<10; i++)
		out.print("현재 팝업창은 " + popupMode + " 상태입니다. <br>");
	
	if(popupMode.equals("on")){
	%>
		<div id="popup">
			<h2 style="text-align:center;">공지사항 팝업입니다.</h2>
			<div style="text-align:right;">
				<input type="checkbox" id="inactiveToday" value="1">
				하루 동안 열지 않음
				<button type="button" id="closeBtn">닫기</button>
			</div>
		</div>
	<%		
	}
	%>

</body>
</html>