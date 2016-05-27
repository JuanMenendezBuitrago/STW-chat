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
import model.ConversationsManager;
import model.Message;
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
    public void handleMessage(Message messge, Session session) {
    	
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam(value ="channel" ) String channel) {
    	
    	System.out.println("Opening socket for " + channel); //TODO:delete
    	
    	// add channel information to session data
    	session.getUserProperties().put("channel", channel);
    	sessions.add(session);
    	
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
    	
    	System.out.println("send refresh message..."); // TODO: delete
    	
    	UUID operatorId;
    	try {
    		operatorId = conversationsManager.getOperatorIdAssignedToAConversation(channel);
    	}
    	catch (ConversationNotFoundException e) {
    		System.out.println("Conversation not found."); //TODO: delete
    		return;
    	}
    	
    	Session operatorSession = getSessionByChannel(operatorId);
    	try {
			operatorSession.getBasicRemote().sendObject(new RefreshMessage());
		} catch (IOException | EncodeException e) {
			// TODO Auto-generated catch block
    		System.out.println("Couldn't send message."); //TODO: delete
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
	    		System.out.println("Session found."); //TODO: delete
				return session;
			}
		}
		System.out.println("Session not found."); //TODO: delete
    	return null;
    }
}
