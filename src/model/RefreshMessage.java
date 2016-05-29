package model;

public class RefreshMessage extends Message {

	public RefreshMessage() {
		this("","", "");
	}
	
	public RefreshMessage(String from, String message, String name) {
		super(new MessageCode(MessageCode.REFRESH), from, name, message);
	}

}
