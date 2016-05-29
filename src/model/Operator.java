package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;


public class Operator {
	
	private boolean online;
	
	private String welcomeMsg;
	
	private String name;
	
	private String login;
	
	private String password;
	
	private UUID id;
	
	private String department;
	
	private String pictureSrc;
	
	private List<Conversation> activeChats = new ArrayList<Conversation>();
	
	private Conversation currentConversation;
	
	private Hashtable<UUID, Conversation> conversationsById = new Hashtable<UUID, Conversation>();
	
	public Operator(UUID id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
		// TODO Auto-generated constructor stub
	}

	public Operator(UUID id, String login, String password, String name, String department) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.department = department;
		// TODO Auto-generated constructor stub
	}
	
	public Operator(UUID id, String login, String password, String name, String department, String welcomeMsg) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.department = department;
		this.welcomeMsg = welcomeMsg;
		// TODO Auto-generated constructor stub
	}
	
	public Operator(UUID id, String login, String password, String name, String department, String welcomeMsg, String pictureSrc) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.department = department;
		this.welcomeMsg = welcomeMsg;
		this.pictureSrc = pictureSrc;
		// TODO Auto-generated constructor stub
	}

	public boolean isOnline() {
		return online;
	}


	public void setOnline(boolean online) {
		this.online = online;
	}


	public String getWelcomeMsg() {
		return welcomeMsg;
	}

	public void setWelcomeMsg(String msg) {
		welcomeMsg = msg;
	}
	
	public String getName() {
		return name;
	}


	public String getLogin() {
		return login;
	}


	public String getPassword() {
		return password;
	}


	public UUID getId() {
		return id;
	}


	public String getDepartment() {
		return department;
	}


	public String getPictureSrc() {
		return pictureSrc;
	}
	
	public int getNumberOfActiveChats() {
		return getActiveChats().size();
	}
	
	public void addConversation(Conversation conversation) {
		activeChats.add(conversation);
		conversationsById.put(conversation.getConversationId(), conversation);
	}
	
	public List<Conversation> getActiveChats() {
		return activeChats;
	}
	
	public Conversation getCurrentConversation() {
		return currentConversation;
	}
	
	public void setCurrentConversation(Conversation conversation) {
		this.currentConversation = conversation;
	}
	
	public Conversation getConversationById(UUID id) {
		return conversationsById.get(id);
	}
	
	public void removeConversation(long id) {
		
	}
	
	public boolean hasActiveConversations() {
		return getNumberOfActiveChats() > 0;
	}
	
	public String toString(){
		String result = "\nonline: " + this.online + "\n";
		result += "welcome message: " + this.welcomeMsg + "\n";
		result += "name: " + this.name + "\n";
		result += "login: " + this.login + "\n";
		result += "password: " + this.password + "\n";
		result += "id: " + this.id + "\n";
		result += "department: " + this.department + "\n";
		result += "picture: " + this.pictureSrc + "\n";

		return result;
	}
}
