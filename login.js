function loginCheck(){
	
	var id = document.login.loginId.value;
	var pw = document.login.loginPass.value;

	if(id == "") {
		alert("ユーザーIDが未入力です");
		return false;
	}else if(!id.match(/^[0-9]{3}/)){
		alert("ユーザーIDは3桁の半角数字で入力してください");
		return false;
	}else if(pw == ""){
		alert("パスワードが未入力です");
		return false;
	}else if(!pw.match(/^[A-Za-z0-9]/)){
		alert("パスワードは半角英数字で入力してください");
		return false;
	}
	return true;
}