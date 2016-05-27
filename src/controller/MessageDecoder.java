package controller;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import model.Message;
 
public class MessageDecoder implements Decoder.Text<Message> {
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
 
	@Override
	public Message decode(final String textMessage) throws DecodeException {
		JsonReader reader = Json.createReader(new StringReader(textMessage)); 
		JsonObject obj = reader.readObject();
		
		try{
			return new Message(obj.getInt("code"), obj.getString("from"), obj.getString("message"));
		}
		catch(Exception e){
			return null;
		}
	}
 
	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}