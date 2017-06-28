<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>新規投稿画面</title>
	<link rel="stylesheet" type="text/css" href="css/entry.css">
	<script type="text/javascript" src="js/entry.js"></script>
</head>
<body>
	<form method="POST" action="./EntryServlet"　name="entry">
		<div class="main_title">
			<h1>新規投稿</h1>
		</div>
		<div class="error">
			${NO_ENTRY_TITLE}
			${OVER_ENTRY_TITLE}
		</div>
		<div class="error">
			${NO_ENTRY_MESSAGE}
			${OVER_ENTRY_MESSAGE}
		</div>
		<ul>
			<li>
				<label for="title" class="title">タイトル</label>
				<input id="title" type="text" name="title" size="55">
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
				<textarea id="message" name="message" rows="7" cols="70"></textarea>
			</li>
			<li class="button">
				<button id="toko" class="toko" type="submit" name="toko" onClick="return entryCheck()">投稿</button>
				<button id="cancel" class="cancel" type="submit" name="cancel" value="cancel">キャンセル</button>
			</li>
		</ul>
	</form>
</body>
</html>
