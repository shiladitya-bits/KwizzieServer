var server_url = "http://"+window.location.host+"/KwizzieServer/UploadServlet/private/";
var server_url_text = "http://"+window.location.host+"/KwizzieServer/kwizzie/quizRoom/private/addQuestion/";
var server_url_create = "http://"+window.location.host+"/KwizzieServer/kwizzie/quizRoom/private/add"
function addmediaQues(typeOfMmedia) {
	$("#AnswerDiv").html("");
	var roomID = document.getElementById("roomID").value;
	var url = server_url + roomID + "/" + typeOfMmedia;
	var html = '<form action ="' + url
			+ '" method="post" enctype="multipart/form-data">';
	html += '<table><tr><td>Question Title :	</td>	<td><textarea id="quesTitle" rows ="4" cols="30" name="quesTitle"> </textarea></td>';
	html += '</tr><tr><td>Choose a picture :</td>	<td><input type = "file" name ="picture" /><br/> </td>';
	html += '</tr><tr><td><button type="button" onclick="showLocationDiv()">Add location to Question</button><br/></td></tr>';
	html += '<tr><td><div id="location"></div></td></tr>';
	html += '<tr><td>Answer Type:</td><td>';
	html += '<input type="radio" name="answerType" id ="mcq" onclick="answertypeSelect()" value="mcq"/> MCQ';
	html += '<input type="radio" name="answerType" id ="text" onclick="answertypeSelect()" value="text" /> Text Answer Type';
	html += '</td></tr></table><div id ="AnswerDiv"></div>';
	html += '</form>';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
};

function addQrQues() {
	var roomID = document.getElementById("roomID").value;
	var url = server_url_text + roomID + "/qr";
	var html = '<form action ="' + url + '" method="post">';

	// html+='<br/> Answer : <input type ="text" /> <br/><br/>';
	html += '<br/><button type="button" onclick="showLocationDiv()">Add location to Question</button><br/><br/>';
	html += '<div id="location"></div>';
	html += '<div id="AnswerDiv"></div>';
	html += '</form>';
	quesDiv = document.getElementById("addQues");
	quesDiv.innerHTML = html;
	addTextAns();

};

function addTextQues() {
	$("#AnswerDiv").html("");
	var roomID = document.getElementById("roomID").value;
	var url = server_url_text + roomID + "/text";
	var html = '<form action ="' + url + '" method="post">';
	html += '<table><tr><td>Question Title</td><td><textarea cols="30" rows="4" name="quesTitle"></textarea></td>';
	html += '</tr><tr><td>Question Subtitle</td><td><input  type="text" name="quesSubtitle"/> </td>';
	html += '</tr><tr><td><button type="button" onclick="showLocationDiv()">Add location to Question</button><br/></td></tr>';
	html += '<tr><td><div id="location"></div></td></tr>';

	html += '<tr><td>Answer Type</td><td>';
	html += '<input type="radio" name="answerType" id ="mcq" onclick="answertypeSelect()" value="mcq" /> MCQ';
	html += '<input type="radio" name="answerType" id ="text" onclick="answertypeSelect()" value="text"/> Text Answer Type';
	html += '</td></tr></table><div id ="AnswerDiv"></div></form>';
	/*
	 * quesDiv = document.getElementById("addQues"); quesDiv.innerHTML = html;
	 */
	$("#addQues").html(html);

};

function showLocationDiv() {
	var html = '<table><tr><td>Latitude : </td><td><input typr="text" name="latitude"></td></tr>';
	html += '<tr><td>Longitude : </td><td><input typr="text" name="longitude"></td></tr>';
	html += '<tr><td>Destination information : </td><td><textarea name="destinationInfo" id="destinationInfo" rows ="4" cols="30"> </textarea></td></tr>';
	html += '</table>';
	$("#location").html(html);
};

function addMCQAns() {

	var html = '';
	html += 'Give Options: <br/><table><tr> <td>';
	html += '	A :<input name = "optiona" type ="text"  /> <br/>';
	html += '	B :<input name = "optionb"  type ="text"  /> <br/>';
	html += '	C :<input name = "optionc"  type ="text"  /> <br/>';
	html += '	D :<input name = "optiond"  type ="text"  /> <br/>';
	html += '</td>	<td><table><tr><td>Correct Answer : </td>';
	html += '<td> <select name = "options">';
	html += '<option value="0">A</option>';
	html += '<option value="1">B</option>';
	html += '<option value="2">C</option>';
	html += '	<option value="3">D</option>';
	html += '</select></td></tr></table></td></tr></table><br/><button type="submit" onclick="addThisQuestion()">Add this question</button>';
	$("#AnswerDiv").html(html);
}
function createRoom(){
	var html='';
	html += '<form method ="post" action="'+server_url_create+'"><table>';
	html +=	'<tr><td>Quiz Room Name</td><td><input id ="roomName" type ="text" name = "roomName"></td></tr>';
	html += '<tr><td>Quiz Room ID</td><td><input id ="roomID" type ="text" name = "roomID"></td></tr>';
	html +=	'<tr><td>Security Key</td><td><input id ="key" type ="text" name = "key"></td></tr>';
	html += '<tr><td>Description</td><td><input id ="description" type ="text" name = "description"></td></tr>';
	html +=	'<tr><td><button id="createQuizRoom" type="submit" onclick="createQuizRoom()">Create Quiz Room</button></td></tr>';
	html += '</table></form>';
	$("#formDiv").html(html);
}
function addTextAns() {
	var html = 'Correct Answer : <input name ="correctAns" type="text" /><br/>';
	html += '<button onclick="addThisQuestion()">Add this question</button>';
	$("#AnswerDiv").html(html);
}

function answertypeSelect() {
	if (document.getElementById('mcq').checked) {
		addMCQAns();
	} else if (document.getElementById('text').checked) {
		addTextAns();
	}

	
}
