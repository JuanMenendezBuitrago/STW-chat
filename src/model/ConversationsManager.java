package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import exceptions.ConversationNotFoundException;
import exceptions.OperatorsNotAvailableException;

@Singleton
@LocalBean
public class ConversationsManager {

	@EJB
	private OperatorsBean operatorsBean;
	
	private Map<UUID,UserIdOperatorIdConversationTuple> conversationTuplesById;
	
	@PostConstruct
	private void init() {
		conversationTuplesById = new HashMap<UUID, UserIdOperatorIdConversationTuple>();
	}

	public Conversation createConversation(UUID userId, String category, String product, String subject, String userName) throws OperatorsNotAvailableException {
		
		Conversation conversation = new Conversation(userId, category, product, subject, userName);

		Operator operator = getAvailableOperator();
		if (operator == null){
			throw new OperatorsNotAvailableException("No operator availale at the moment.");
		}
		
		
		conversation.setOperator(operator);
		operator.addConversation(conversation);
		UUID operatorId = operator.getId();
		conversationTuplesById.put(userId, new UserIdOperatorIdConversationTuple(userId, operatorId, conversation));
		
		return conversation;
	}
	
	public Operator getAvailableOperator() {
		List<Operator> operatorsList = operatorsBean.getOperatorList();
		if(operatorsList.isEmpty()){
			return null;
		}
		
		Operator result = null;
		int minActiveChats = 100;
		for(Operator operator : operatorsList) {
			int activeChats = operator.getNumberOfActiveChats(); 
			if(operator.isOnline() && activeChats < minActiveChats) {
				result = operator;
				minActiveChats = activeChats;
			}
		}
		
		return result;
	}
	
	public UUID getOperatorIdAssignedToAConversation(UUID conversationId) throws ConversationNotFoundException{
		try {
			return conversationTuplesById.get(conversationId).getOperatorId();
		} 
		catch (NullPointerException e) {
			throw new ConversationNotFoundException();
		}
	}
	
	public Conversation getConversationByid(UUID conversationId) {
		try{
			return conversationTuplesById.get(conversationId).getConversation();
		}catch(NullPointerException e){
			return null;
		}
	}
}
