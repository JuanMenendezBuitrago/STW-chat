package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ejb.EJB;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jboss.weld.util.collections.ArraySet;

import exceptions.ConversationNotFoundException;
import model.Conversation;
import model.ConversationsManager;
import model.Message;
import model.Operator;
import model.OperatorsBean;
import model.RefreshMessage;

/**
 * Session Bean implementation class ChatServerEndpoint
 */
@ServerEndpoint(value="/chat/{channel}", encoders = controller.MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatServerEndpoint {
	// sessions storage
	private static Set<Session> sessions = new ArraySet<Session>();
	// 
	@EJB
	private OperatorsBean operatorsBean;
	
	@EJB
	private ConversationsManager conversationsManager; 
    /**
     * Default constructor. 
     */
    public ChatServerEndpoint() {
        // TODO Auto-generated constructor stub
    }

    @OnMessage
    public void handleMessage(Message message, Session session) {
    	UUID channel = UUID.fromString((String)session.getUserProperties().get("channel"));
    	System.out.println("Received message from channel: " + channel);
    	Conversation conversation = conversationsManager.getConversationByid(channel);
    	
    	
    	Operator operator;
    	UUID operatorId, toChannel;
    	boolean pendingMessage = false;
    	if(conversation != null){
    		System.out.println("Conversation found with that Id (it's a message from a user): " + channel); // TODO:delete
    		operator = conversation.getOperator();
    		operatorId = operator.getId();
    		toChannel = operatorId;
    		if(conversation != operator.getCurrentConversation()){
				pendingMessage = true;
			}
    	}else{
    		System.out.println("No conversation found with that Id (it's a message from an operator): " + channel); // TODO:delete
    		operatorId = channel;
    		operator = operatorsBean.getOperatorById(operatorId);
    		conversation = operator.getCurrentConversation();
    		toChannel = conversation.getConversationId();
    	}
    	
    	Session toSession = getSessionByChannel(toChannel);
    	try {
			toSession.getBasicRemote().sendObject(message);
			conversation.addMessage(message);
			conversation.setPending(pendingMessage);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam(value ="channel" ) String channel) {
    	
    	System.out.println("Opening websocket for " + channel); //TODO:delete
    	
    	// add channel information to session data
    	session.getUserProperties().put("channel", channel);
    	sessions.add(session);

    	System.out.println("Sessions in storage: "); //TODO:delete
    	for(Session s: sessions){
    		System.out.println(s.getUserProperties().get("channel"));//TODO:delete
    	}
    	// send message to operator
    	sendRefreshMsgIfUserOpenedASession(UUID.fromString(channel));
    }
    
    @OnClose
    public void onClose(Session session) {
    	
    }
    
    /**
     * Send message to operator chat so the interface refreshes the conversations list.
     * @param channel The user's channel
     */
    private void sendRefreshMsgIfUserOpenedASession(UUID channel) {
    	
    	
    	UUID operatorId;
    	try {
    		operatorId = conversationsManager.getOperatorIdAssignedToAConversation(channel);
    	}
    	catch (ConversationNotFoundException e) {
    		return;
    	}
    	
    	Session operatorSession = getSessionByChannel(operatorId);
    	try {
			operatorSession.getBasicRemote().sendObject(new RefreshMessage());
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Finds the session that corresponds to the given channel
     * @param id The channel id.
     * @return Session|null
     */
    private Session getSessionByChannel(UUID id) {
    	for (Session session : sessions) {
    		Map<String, Object> properties = session.getUserProperties();
    		UUID channel = UUID.fromString((String)properties.get("channel"));
			if(channel.equals(id)) {
				return session;
			}
		}
    	return null;
    }
}
