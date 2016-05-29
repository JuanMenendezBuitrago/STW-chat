/**
 * Client endPoint
 */
//Defining the serverEnd point of the websocket connection. 
var chatEndpoint = "ws://localhost:8080/chat/chat";
var ws;
var previous;
var $beginChat;
var $endChat;
var $message;
var $opening;
var $output;
var $closing;
var id = "";


$(function(){
	initiateChatSession();
})

/**
 * Function that creates a new websocket connection to the URI serverEndPoint 
 * specified in the global variable ws if the browser supports websockets 
 * technology.
 * After creating the connection to the serverEndPoint assigns a function to the 
 * connection events: onopen, onclose, onmessage, onerror
 */
function initiateChatSession() {
	
	console.log("Intiating chat session.");	

	if ("WebSocket" in window) {
		ws = new WebSocket(chatEndpoint + "/" + $("#personId").val());
		ws.onopen = onOpen;
		ws.onclose = onClose;
		ws.onmessage = onMessage;	
	} else {
		// The browser doesn't support WebSocket
		alert("WebSocket NOT supported!");
	}
}

function closeChatSession(event) {
	
	console.log("In closeChatSession");
	
	ws.close();
}

/**
 * This function is called on a connection error.
 * @param evt the event that contains the error.
 */
function onError(evt){
	connection.close();
}

/**
 * This function is called when the connection to the serverEndPoint is 
 * stablished.
 * @param evt the event that contains the connection data.
 */ 
function onOpen(evt){
	
	console.log("on open");
	
}

/**
 * This function is called when the connection to the serverEndPoint is closed.
 * @param evt the event that contains the disconnection data.
 */
function onClose(evt){	
	
	console.log("on close");
	
}

/**
 * This function is called when a message is received by the client end point
 * @param evt Event that contains the message data. Use evt.data to get the message.  
 */
function onMessage(evt){
	var received_msg = JSON.parse(evt.data);
	if (received_msg.code == 1) {
		console.log("refreshing");
		$("#refresh").click();
		return;
	}
	
	if(received_msg.from == $("#conversationId").val() || received_msg.from == $("#operatorId").val()) {
		appendMessage(received_msg.message, received_msg.name, "outside");
	}else{
		$("#conversationsList a").filter(function(){
			return received_msg.from == $(this).data("id");
		}).addClass("pending");
	}
}


function sendChatMessage() {
	console.log("In sendChatMessage");

	var message = $("#message").val();
	var name = $("#name").val();
	if(message != "") {
		var chatobj = {"code": 0, "from": $("#personId").val() , "name": name, "message" : message};
		var chatstr = JSON.stringify(chatobj);
		
		if (ws == null) {
			alert('problem with WebSocket, please initiate session again');
		} else {
			ws.send(chatstr);
			appendMessage(message, name, "local");
			$("#message").val('').focus();
		}	
	}
}

function appendMessage(msg, name, author) {
	msg = msg.replace(/(?:\r\n|\r|\n)/g, '<br />');
	var msg_line = $('<div class="line ' + author + '">').append(name + ":" + msg);
	$("#chatText").append(msg_line);
}


