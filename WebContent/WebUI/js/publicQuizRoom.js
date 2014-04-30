var server_url = "http://"+window.location.host+"/KwizzieServer/UploadServlet/public/";
var server_url_text = "http://"+window.location.host+"/KwizzieServer/kwizzie/quizRoom/public/addQuestion/";
var server_url_create = "http://"+window.location.host+"/KwizzieServer/quizRoom/public/categories/add";

function addmediaQues(typeOfMmedia){
	$("#AnswerDiv").html("");
	var categoryCode =document.getElementById("categoryCode").value;
	var url = server_url +categoryCode+"/"+typeOfMmedia;
	var html = '<form action ="'+url+'" method="post" enctype="multipart/form-data">';
	html+='<table><tr><td>Question Title :	</td>	<td><textarea id="quesTitle" rows ="4" cols="30" name="quesTitle"> </textarea></td>';
	html+='</tr><tr><td>Choose a picture :</td>	<td><input type = "file" name ="picture" /><br/> </td>';
	html+='</tr><tr><td>Answer Type:</td><td>';
	html+='<input type="radio" name="answerType" id ="mcq" onclick="answertypeSelect()" value="mcq"/> MCQ';
	html+='<input type="radio" name="answerType" id ="text" onclick="answertypeSelect()" value="text" /> Text Answer Type';
	html+='</td></tr></table><div id ="AnswerDiv"></div>';
	html+='</form>';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
};

function createCategory(){
	var html='';
	html += '<form method ="post" action="'+server_url_create+'"><table>';
	html +=	'<tr><td>Category Name</td><td><input id ="categoryName" type ="text" name = "categoryName"></td></tr>';
	html += '<tr><td>Category ID</td><td><input id ="categoryCode" type ="text" name = "categoryCode"></td></tr>';
	html +=	'<tr><td><button id="createCategory" type="submit" onclick="createQuizRoom()">Create Category</button></td></tr>';
	html += '</table></form>';
	$("#formDiv").html(html);
}

function addQrQues(){
	var categoryCode =document.getElementById("categoryCode").value;
	var url = server_url_text +categoryCode+"/qr";
	var html = '<form action ="'+url+'" method="post">';
	
	//html+='<br/> Answer : <input  type ="text" 	/> <br/><br/>';
	html+='<div id="AnswerDiv"></div>';
	html+='</form>';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
	addTextAns();
	
};

function addTextQues(){
	$("#AnswerDiv").html("");
	var categoryCode =document.getElementById("categoryCode").value;
	var url = server_url_text +categoryCode+"/text";
	var html = '<form action ="'+url+'" method="post">';
	html+='<table><tr><td>Question Title</td><td><textarea cols="30" rows="4" name="quesTitle"></textarea></td>';
	html+='</tr><tr><td>Question Subtitle</td><td><input  type="text" name="quesSubtitle"/> </td>';
	html+='</tr><tr><td>Answer Type</td><td>';
	html+='<input type="radio" name="answerType" id ="mcq" onclick="answertypeSelect()" value="mcq" /> MCQ';
	html+='<input type="radio" name="answerType" id ="text" onclick="answertypeSelect()" value="text"/> Text Answer Type';
	html+='</td></tr></table><div id ="AnswerDiv"></div></form>';
	/*quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;*/
	$("#addQues").html(html);
	
};

function addMCQAns(){
	
	var html='';
	html+='Give Options: <br/><table><tr> <td>';
	html+='	A :<input name = "optiona" type ="text"  /> <br/>';
	html+='	B :<input name = "optionb"  type ="text"  /> <br/>';
	html+='	C :<input name = "optionc"  type ="text"  /> <br/>';
	html+='	D :<input name = "optiond"  type ="text"  /> <br/>';
	html+='</td>	<td><table><tr><td>Correct Answer : </td>';
	html+='<td> <select name = "options">';
	html+='<option value="0">A</option>';
	html+='<option value="1">B</option>';
	html+='<option value="2">C</option>';
	html+='	<option value="3">D</option>';
	html+='</select></td></tr></table></td></tr></table><br/><button type="submit" onclick="addThisQuestion()">Add this question</button>';
	$("#AnswerDiv").html(html);
}

function addTextAns(){
	var html='Correct Answer : <input name ="correctAns" type="text" /><br/>';
	html+='<button onclick="addThisQuestion()">Add this question</button>';
	$("#AnswerDiv").html(html);
}

function answertypeSelect(){
	if(document.getElementById('mcq').checked){
		addMCQAns();
	} else if(document.getElementById('text').checked) {
		addTextAns();
	}
	
}
