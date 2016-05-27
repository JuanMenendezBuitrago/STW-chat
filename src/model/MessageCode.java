package model;

public class MessageCode {
	
	public static final int MESSAGE = 0;
	
	public static final int REFRESH = 1;
	
	private int intCode;
	
	MessageCode(int code) {
		this.intCode = code;		
	}
	
	public int intValue() {
		return intCode;
	}

}
