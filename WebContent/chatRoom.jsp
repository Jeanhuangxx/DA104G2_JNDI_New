<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style type="text/css">
/*  OK  */
body{
	font-family: "Microsoft JhengHei UI";
}

.msger {
	position: fixed;
	display: flex;
	flex-flow: column wrap;
	justify-content: space-between;
	width: 100%;
	max-width: 400px;
	margin: 34px 27px;
	border: 2px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 15px 15px -5px rgba(0, 0, 0, 0.2);
}

.msger-chat {
	height: 300px;
	padding: 10px;
	overflow: scroll;
	overflow-x: hidden;
	overflow-y: auto;
	background-image: url("images/chat_room.jpg");
	background-size: cover;
/* 	opacity: 0.5; */
}

.msger-chat::-webkit-scrollbar {
	width: 6px;
}

.msger-chat::-webkit-scrollbar-track {
	background: #ddd;
}

.msger-chat::-webkit-scrollbar-thumb {
	background: #bdbdbd;
}

.msg {
	display: flex;
	align-items: flex-end;
	margin-bottom: 10px;
}

.msg:last-of-type {
	margin: 0;
}

.msg-img {
	width: 50px;
	height: 50px;
	margin-right: 10px;
	background: #ddd;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	border-radius: 50%;
}

.msg-bubble {
	max-width: 312px;
	padding: 15px;
	border-radius: 15px;
	background: #ececec;
	word-break: break-all;
}

.msg-info {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.msg-info-name {
	margin-right: 10px;
	font-weight: bold;
}

.msg-info-time {
	font-size: 0.85em;
}

.left-msg .msg-bubble {
	border-bottom-left-radius: 0;
}

.right-msg {
	flex-direction: row-reverse;
}

.right-msg .msg-bubble {
	background: #53FF53;
	/* 	color: #fff; */
	border-bottom-right-radius: 0;
}

.right-msg .msg-img {
	margin: 0 0 0 10px;
}

.message-submit {
	z-index: 1;
	top: 9px;
	right: 10px;
	color: #fff;
	border: none;
	background: #248A52;
	font-size: 10px;
	text-transform: uppercase;
	line-height: 1;
	padding: 6px 10px;
	border-radius: 10px;
	outline: none !important;
	transition: background .2s ease;
}
</style>
</head>
<body onload="connect();" onunload="disconnect();">
	<div class="">
		<div class="chat_room msger">
			<div class="text-center">聊天室</div>
			<h3 id="statusOutput" class="statusOutput"></h3>
			<div id="messagesArea" class="panel msger-chat"></div>
			<div class="panel input-area">
				<div id="userName" class="text-field">${memVO.mem_name}</div>
				<input id="message" class="text-field" type="text"
					placeholder="Message"
					onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
					type="submit" id="sendMessage" class="message-submit" value="Send"
					onclick="sendMessage();" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var MyPoint = "/WebSocketChatServlet/${memVO.mem_name}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + host + webCtx + MyPoint;

		var websocket;

		function connect() {
			webSocket = new WebSocket(endPointURL);

			webSocket.open = function(event) {
				$("#sendMessage").disable = false;
			};

			webSocket.onmessage = function(event) {
				var messagesArea = document.getElementById("messagesArea");
				var message;
				var jsonObj = JSON.parse(event.data);
				var imgSrc= "/ShowPic_V1?mem_no=" + jsonObj.mem_no;
				
				if (jsonObj.length > 1) {
					for (var arrlen = 0; arrlen < jsonObj.length; arrlen++) {
						if ("${memVO.mem_name}" == jsonObj[arrlen].mem_name) {
							imgSrc = "/ShowPic_V1?mem_no="
									+ jsonObj[arrlen].mem_no;
							message = "<div class='msg right-msg'>"
									+ "<img class='msg-img' src='"+ webCtx + imgSrc +"'>"
									+ "<div class='msg-bubble'>"
									+ "<div class='msg-text'>"
									+ jsonObj[arrlen].message + "</div>"
									+ "</div>" + "</div>";
							messagesArea.innerHTML = messagesArea.innerHTML
									+ message;
						} else {
							imgSrc = "/ShowPic_V1?mem_no="
									+ jsonObj[arrlen].mem_no;
							message = "<div class='msg left-msg'>"
									+ "<img class='msg-img' src='"+ webCtx + imgSrc +"'>"
									+ "<div class='msg-bubble'>"
									+ "<div class='msg-text'>"
									+ jsonObj[arrlen].message + "</div>"
									+ "</div>" + "</div>";
							messagesArea.innerHTML = messagesArea.innerHTML
									+ message;
						}
						messagesArea.scrollTop = messagesArea.scrollHeight;
					}
				} else {
					if ("${memVO.mem_name}" == jsonObj.mem_name) {
						message = "<div class='msg right-msg'>"
								+ "<img class='msg-img' src='"+ webCtx + imgSrc +"'>"
								+ "<div class='msg-bubble'>"
								+ "<div class='msg-text'>" + jsonObj.message
								+ "</div>" + "</div>" + "</div>";
					} else {
						message = "<div class='msg left-msg'>"
								+ "<img class='msg-img' src='"+ webCtx + imgSrc +"'>"
								+ "<div class='msg-bubble'>"
								+ "<div class='msg-text'>" + jsonObj.message
								+ "</div>" + "</div>" + "</div>";
					}
					messagesArea.innerHTML = messagesArea.innerHTML
							+ message.replace(/\n/gi, "<br>");
					messagesArea.scrollTop = messagesArea.scrollHeight;
				}
			};
		}

		function sendMessage() {
			var inputMessage = document.getElementById("message");
			var message = inputMessage.value.trim();

			if (message === "") {
				inputMessage.focus();
			} else {
				var jsonObj = {
					"mem_no" : "${memVO.mem_no}",
					"mem_name" : "${memVO.mem_name}",
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
	</script>
</body>
</html>