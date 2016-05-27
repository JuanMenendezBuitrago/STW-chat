package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import model.Conversation;
import model.Operator;
import view.ConversationDetails;
import view.OperatorChatBean;

@ManagedBean(name="operatorChat")
@SessionScoped
public class OperatorChatController {

	private Operator operator;
	
	@ManagedProperty(value="#{operatorChatBean}")
	private OperatorChatBean operatorChatBean;
	
	private List<Conversation> conversations; 
	
	private UUID currentConversationId = null;
	
	private static final String PUSH_CHANNEL = "";
	
	public OperatorChatController() {
		// TODO Auto-generated constructor stub
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public void setOperatorChatBean(OperatorChatBean operatorChatBean) {
		this.operatorChatBean = operatorChatBean;
	}

	private void refreshConversationArea(Conversation conversation) {
		
	}
	
	public void changeToConversation(ConversationDetails conversationDetails) {
		
	}
	
	private void changeToConversation(long conversationId) {
		
	}
	
	public ConversationDetails getConversationDetailsById(long conversationId) {
		return null;
	}
	
	public void refreshConversations() {
		// fetch current conversation's id
		setupCurrentConversationId();
		
		// get active chats for this operator
		this.conversations = (ArrayList<Conversation>) this.operator.getActiveChats();
		
		System.out.println("Operators list:"); //TODO:delete
		System.out.println(this.conversations); //TODO:delete
		
		// compose list of conversations details
		ArrayList<ConversationDetails> conversationsDetails = new ArrayList<ConversationDetails>();
		for(Conversation conversation : this.conversations) {
			// instantiate ConversationDetails object 
			ConversationDetails conversationDetails = new ConversationDetails(conversation.getUserName(), conversation.getProduct(), conversation.getConversationId());
			
			// test equality of UUIDs 
			if(conversation.getConversationId().equals(this.currentConversationId)) {
				conversationDetails.setConversationLinkStyleToSelectedChat();
			}
			conversationsDetails.add(conversationDetails);
		}
		
		this.operatorChatBean.setConversations(conversationsDetails);
	}
	
	private void setupCurrentConversationId() {
		
		System.out.println("OperatorChatController::setupCurrentConversationId()");//TODO:delete
		
		if( this.currentConversationId == null) {
		
			System.out.println("currentConversationId is null"); //TODO:delete
			
			Conversation currentConversation = this.operator.getCurrentconversation();
			if(currentConversation != null) {
			
				System.out.println("currentConversationId is not null"); //TODO:delete
				
				this.currentConversationId = currentConversation.getConversationId();
			}
		}
		System.out.println("currentConversationId is " + this.currentConversationId); //TODO:delete
		System.out.println("--------------"); //TODO:delete
		
	}
	
	public void removeConversation(ConversationDetails conversationdetails) {
		
	}
	
	public String tryToLogout() {
		return "";
	}
	
	public boolean isAllowedToLogout() {
		return true;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}
	
	
	
}
