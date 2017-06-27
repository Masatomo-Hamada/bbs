function displayCheck(){
	
	var check = document.getElementsByName("check");
	var flag = 0;
	var i;
	
	for (i = 0; i < check.length; i++) {
		if(check[i].checked) {
			flag = 1;
		}
	}
	
	if(flag == 0) {
		alert("投稿が選択されていません");
		return false;
	}
	return true;
}