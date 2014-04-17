addPictureQues = function(){
	var html  ='';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
};

function addVideoQues(){
	var html  ='';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
};

function addQRQues(){
	var html  ='';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
};

function addAudioQues(){
	var html  ='';
	quesDiv = document.getElementById("addQues");
	
	quesDiv.innerHTML = html;
};

function addTextQues(){
	var html = '';
	html+='<table><tr><td>Question Title</td><td><textarea cols="30" rows="4"></textarea></td>';
	html+='</tr><tr><td>Question Subtitle</td><td><input  type="text"/> </td>';
	html+='</tr><tr><td>Answer Type</td><td>';
	html+='<input type="radio" name="answerType" id ="mcq" /> MCQ';
	html+='<input type="radio" name="answerType" id ="mcq" /> Text Answer Type';
	html+='</td>	</tr></table>';
	/*quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;*/
	$("#addQues").html(html);
	
};