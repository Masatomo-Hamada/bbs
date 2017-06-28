<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>投稿編集画面</title>
	<link rel="stylesheet" type="text/css" href="css/entry.css">
	<script type="text/javascript" src="js/edit.js"></script>
</head>
<body>
	<form method="POST" action="./EditServlet" name="edit">
		<div class="main_title">
			<h1>投稿編集</h1>
		</div>
		<div class="error">
			${TITLE_ERROR}
		</div>
		<div class="error">
			${MESSAGE_ERROR}
		</div>
		<ul>
			<li>
				<label for="title" class="title">タイトル</label>
				<input id="title" type="text" name="title" value="${title}" size="55">
				<span>〔全角30字以内〕</span>
			</li>
			<li>
				<span class="name">投稿者</span>
				<span>${name}</span>
			</li>
			<li>
				<label for="message" class="message">本文</label>
				<span>〔全角300字以内〕</span>
				<br>
				<textarea id="message" name="message" rows="7" cols="70">${message}</textarea>
			</li>
			<li class="button">
				<button id="edit" class="edit" type="submit" name="edit" onClick="return editCheck()">編集</button>
				<button id="cancel" class="cancel" type="submit" name="cancel" value="cancel">キャンセル</button>
			</li>
		</ul>
	</form>
</body>
</html>
