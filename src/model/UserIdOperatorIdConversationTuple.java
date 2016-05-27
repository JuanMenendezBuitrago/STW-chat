package model;

import java.util.UUID;

public class UserIdOperatorIdConversationTuple {

	private UUID userId;
	
	private UUID operatorId;
	
	private Conversation conversation;
	
	

	public UserIdOperatorIdConversationTuple(UUID userId, UUID operatorId, Conversation conversation) {
		super();
		this.userId = userId;
		this.operatorId = operatorId;
		this.conversation = conversation;
	}

	public UUID getUserId() {
		return userId;
	}

	public UUID getOperatorId() {
		return operatorId;
	}

	public Conversation getConversation() {
		return conversation;
	}

	
}
