package view;

import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OperatorChatBean {

	
	private String history="";
	
	private String message="";
	
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
		return conversations;
	}

	public void setConversations(List<ConversationDetails> conversations) {
		this.conversations = conversations;
	}

	
}
