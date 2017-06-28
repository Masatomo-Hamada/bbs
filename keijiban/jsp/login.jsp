<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>ログイン画面</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
	<form method="POST" action="./LoginServlet" name="login" onSubmit="return loginCheck()">
		<div class="title">
			<h1>ログイン</h1>
		</div>
		<div class="error">
			${ERROR}
		</div>
		<div>
		<p>ユーザーIDとパスワードを入力し、ログインしてください。</p>
		</div>
		<ul class="form">
			<li>
				<label for="id">ユーザーID</label>
				<input id="id" type="text" name="loginId" maxlength="3">
			</li>
			<li>
				<label for="pw">パスワード</label>
				<input id="pw" type="password" name="loginPass" maxlength="8">
			</li>
			<li>
				<button type="submit">ログイン</button>
			</li>
		</ul>
	</form>
</body>
</html>
