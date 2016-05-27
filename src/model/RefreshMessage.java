package model;

public class RefreshMessage extends Message {

	public RefreshMessage() {
		this("","");
	}
	
	public RefreshMessage(String from, String message) {
		super(new MessageCode(MessageCode.REFRESH), from, message);
	}

}
