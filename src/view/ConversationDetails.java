package view;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConversationDetails {

	private String userName;
	
	private String product;
	
	private boolean pending;
	
	private String userNameAndProduct;
	
	private UUID conversationId;

	private String conversationLinkStyle;
	
	private static final String NOT_SELECTED_CHAT_STYLE = "not-selected";
	
	private static final String SELECTED_CHAT_STYLE = "selected";
	
	public ConversationDetails(String userName, String product, UUID conversationId, boolean pending) {
		this.userName = userName;
		this.product = product;
		this.pending = pending;
		this.conversationId = conversationId;
		conversationLinkStyle = ConversationDetails.NOT_SELECTED_CHAT_STYLE;
	}

	public String getUserName() {
		return userName;
	}
	
	public boolean isPending() {
		return pending;
	}
	
	public boolean setPending(boolean pending) {
		return this.pending = pending;
	}
	
	public String getProduct() {
		return product;
	}

	public String getUserNameAndProduct() {
		return userNameAndProduct;
	}

	public UUID getConversationId() {
		return conversationId;
	}

	public String getConversationLinkStyle() {
		return conversationLinkStyle;
	}

	public void setConversationLinkStyleToSelectedChat() {
		conversationLinkStyle = ConversationDetails.SELECTED_CHAT_STYLE;
	}
	
	public void setConversationLinkStyleToUnelectedChat() {
		conversationLinkStyle = ConversationDetails.NOT_SELECTED_CHAT_STYLE;
	}
}
