package view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OperatorChatBean {

	private int selectedConversationId;
	
	private String history;
	
	private String message;
	
	private List<ConversationDetails> conversations;
	
	public OperatorChatBean() {
		// TODO Auto-generated constructor stub
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ConversationDetails> getConversations() {
		if(conversations != null) {
			for(ConversationDetails details: conversations) {
				System.out.println("name: " + details.getUserName());
				System.out.println("style: " + details.getConversationLinkStyle());
			}
		}
		return conversations;
	}

	public void setConversations(List<ConversationDetails> conversations) {
		this.conversations = conversations;
	}

	
}
