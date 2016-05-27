package model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Message {

	private String message;
	
	private String from;
	
	private String timeStamp;
	
	private MessageCode code;
	
	public Message(String from, String message){
		this(MessageCode.MESSAGE, from, message);
	}

	public Message(int code, String from, String message){
		this(new MessageCode(code), from, message);
	}
	
	public Message(MessageCode code, String from, String message){
		this.code = code;
		this.from = from;
		this.message = message;
		this.timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public MessageCode getCode() {
		return code;
	}
	
	public void setCode(MessageCode code) {
		this.code = code;
	}
	
	public String toString() {
		return "";
	}
}
