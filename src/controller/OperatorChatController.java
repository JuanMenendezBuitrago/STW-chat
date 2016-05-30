package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
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

	public Operator getOperator() {
		return operator;
	}

	public void setOperatorChatBean(OperatorChatBean operatorChatBean) {
		this.operatorChatBean = operatorChatBean;
	}

	public UUID getCurrentConversationId() {
		return currentConversationId;
	}

	private void refreshConversationArea(Conversation conversation) {
		operatorChatBean.setHistory(conversation.toString());
	}
	
	private void changeToConversation(ConversationDetails newConversationDetails) {
		if(currentConversationId != null) {
			ConversationDetails oldConversationDetails = getConversationDetailsById(currentConversationId);
			oldConversationDetails.setConversationLinkStyleToUnelectedChat();
		}
		
		currentConversationId = newConversationDetails.getConversationId();
		newConversationDetails.setPending(false);
		Conversation conversation = operator.getConversationById(currentConversationId);
		operator.setCurrentConversation(conversation);
		newConversationDetails.setConversationLinkStyleToSelectedChat();
		
		refreshConversationArea(conversation);
		refreshConversations();
	}
	
	
	public void changeToConversation(String conversationId) {
		UUID newConversationId = UUID.fromString(conversationId);
		ConversationDetails newCoversationDetails = getConversationDetailsById(newConversationId); 
		changeToConversation(newCoversationDetails);
	}
	
	public ConversationDetails getConversationDetailsById(UUID conversationId) {

		System.out.println("getting onversation details by id " + conversationId); // TODO: delete
		List<ConversationDetails>  conversationsDetails = operatorChatBean.getConversations();
		
		for(ConversationDetails conversationDetails : conversationsDetails) {
			System.out.println("comparing with conversation id " + conversationDetails.getConversationId()); // TODO: delete
			if(conversationDetails.getConversationId().equals(conversationId)) {
				System.out.println("returning " + conversationDetails); // TODO: delete
				return conversationDetails;
			}
		}
		System.out.println("returning null??"); // TODO: delete
		return null;
	}
	
	public void refreshConversations() {
		// fetch current conversation's id
		setupCurrentConversationId();
		
		// get active chats for this operator
		this.conversations = (ArrayList<Conversation>) this.operator.getActiveChats();
		
		// compose list of conversations details
		ArrayList<ConversationDetails> conversationsDetails = new ArrayList<ConversationDetails>();
		for(Conversation conversation : this.conversations) {
			// instantiate ConversationDetails object 
			ConversationDetails conversationDetails = new ConversationDetails(conversation.getUserName(), conversation.getProduct(), conversation.getConversationId(), conversation.isPending());
		
			// test equality of UUIDs 
			if(conversation.getConversationId().equals(this.currentConversationId)) {
				conversationDetails.setConversationLinkStyleToSelectedChat();
			}
			conversationsDetails.add(conversationDetails);
		}
		
		this.operatorChatBean.setConversations(conversationsDetails);
	}
	
	private void setupCurrentConversationId() {
		
		if( this.currentConversationId == null) {
		
			
			Conversation currentConversation = this.operator.getCurrentConversation();
			if(currentConversation != null) {				
				this.currentConversationId = currentConversation.getConversationId();
			}
		}
	}
	
	private void removeConversation(ConversationDetails conversationdetails) {
		
	}
	
	public void removeConversation(String conversationId) {
		UUID conversationUUID = UUID.fromString(conversationId);
		ConversationDetails conversationDetails = getConversationDetailsById(conversationUUID);
		removeConversation(conversationDetails);
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
