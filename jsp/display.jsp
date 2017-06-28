<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource url="jdbc:mysql://localhost/DB_KEIJIBAN" driver="com.mysql.jdbc.Driver" user="KEIUSER" password="SSS" var="db"/>
<html>
<head>
	<title>投稿一覧画面</title>
	<link rel="stylesheet" type="text/css" href="css/display.css">
	<script type="text/javascript" src="js/display.js"></script>
</head>

<body>
	<form method="POST" action="./DisplayServlet" name="display">
		<div class="title">
			<h1>投稿一覧</h1>
		</div>
			<button type="submit" name="entry">新規投稿</button>
			<button type="submit" name="delete" onclick="return displayCheck()">選択削除</button>
			<span class="error">
				${ERROR}
			</span>
		<hr>
		<table>
			<tr>
				<td rowspan="2">
					<label for="sort" class="sort">ソート</label>
					<select id="sort" name="sort" onChange="this.form.submit()">	
						<c:choose>
							<c:when test = "${sessionScope.sort == 'default'}">
								<option value="default" selected></option>
							</c:when>
							<c:otherwise>
								<option value="default"></option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test = "${sessionScope.sort == 'EDIT_DATE DESC'}">
								<option value="EDIT_DATE DESC" selected>更新日時（降順）</option>
							</c:when>
							<c:otherwise>
								<option value="EDIT_DATE DESC">更新日時（降順）</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test = "${sessionScope.sort == 'EDIT_DATE ASC'}">
								<option value="EDIT_DATE ASC" selected>更新日時(昇順)</option>
							</c:when>
							<c:otherwise>
								<option value="EDIT_DATE ASC">更新日時(昇順)</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test = "${sessionScope.sort == 'TOKO_DATE DESC'}">
								<option value="TOKO_DATE DESC" selected>投稿日時(降順)</option>
							</c:when>
							<c:otherwise>
								<option value="TOKO_DATE DESC">投稿日時(降順)</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test = "${sessionScope.sort == 'TOKO_DATE ASC'}">
								<option value="TOKO_DATE ASC" selected>投稿日時(昇順)</option>
							</c:when>
							<c:otherwise>
								<option value="TOKO_DATE ASC">投稿日時(昇順)</option>
							</c:otherwise>
						</c:choose>
				</td>
				<td>
					<label for="search_title" class="search_title">タイトル</label>
					<input id="search_title" type="text" name="search_title" value="${sessionScope.search_title}">
				</td>
				<td>
					<select name="date" class="date">
						<option value="EDIT_DATE">更新日時</option>
						<option value="TOKO_DATE">投稿日時</option>
					</select>
					<select name="start_year">
						<option value="year">年</option>
						<c:forEach begin="0" end="9" var="year">
							<option value="${2017 - year}">${2017 - year}年</option>
						</c:forEach>
					</select>
					<span>／</span>
					<select name="start_month">
						<option value="month">月</option>
						<c:forEach begin="1" end="12" var="month">
							<option value="0${month}">${month}月</option>
						</c:forEach>
					</select>
					<span>～</span>
					<select name="finish_year">
						<option value="year">年</option>
						<c:forEach begin="0" end="9" var="year">
							<option value="${2017 - year}">${2017 - year}年</option>
						</c:forEach>
					</select>
					<span>／</span>
					<select name="finish_month">
						<option value="month">月</option>
						<c:forEach begin="1" end="12" var="month">
							<option value="0${month}">${month}月</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<sql:query var="query" dataSource="${db}" >
					SELECT DISTINCT mt.USER_NAME FROM TB_TOKO tb INNER JOIN MT_USER mt ON tb.USER_ID = mt.USER_ID
					</sql:query>
					<label for="search_name" class="name">投稿者</label>
					<select id="search_name" name="search_name">
						<option value="default" selected></option>
						<c:forEach var="data" items="${query.rows}" >
							<c:choose>
								<c:when test="${sessionScope.search_name == data.USER_NAME}">
									<option value="${data.USER_NAME}" selected>${data.USER_NAME}</option>
								</c:when>
								<c:otherwise>
									<option value="${data.USER_NAME}">${data.USER_NAME}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td>
					<button type="submit" value="search" name="search">絞込み</button>
				</td>
			</tr>
		</table>
		<hr>
		<c:forEach var="data" items="${listData}" begin="0" step="1" varStatus="s">
			<table class="toko">
				<tr>
					<td class="check">
						<input id="check" type="checkbox" value="${s.index}" name="check">
					</td>
					<td class="subject">
						<div>${data.title}</div>
						<div>${data.name}</div>
					</td>
					<td class="time">
						<div>投稿:${data.tokoDate}</div>
						<div>更新：${data.editDate}</div>
					</td>
					<td class="blank" rowspan="2"></td>
				</tr>
				<tr>
					<td></td>
					<td class="message" colspan="2">${data.message}</td>
				</tr>
				<tr>
					<td colspan="3"></td>
					<td class="edit">
					<button type="submit" value="${s.index}" name="edit">編集</button>
						</td>
					</tr>
			</table>
			<br>
		</c:forEach>
	</form>
</body>
</html>
