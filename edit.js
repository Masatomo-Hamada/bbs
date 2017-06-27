function editCheck(){

	var title = document.getElementById("title").value;
	var title_length = title.length;
	var message = document.getElementById("message").value;
	var message_length = message.length;
	
	if(title == "" || title == " " || title == "　") {
		alert("タイトルが未入力です");
		return false;
	}else if(title_length > 30){
		alert("タイトルは全角30字以内で入力してください");
		return false;
	}else if(message == "" || message == " " || message == "　"){
		alert("本文が未入力です");
		return false;
	}else if(message_length > 300){
		alert("本文は全角300字以内で入力してください");
		return false;
	}
	return true;
}