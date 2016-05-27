package controller;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import model.Message;

public class MessageEncoder implements Encoder.Text<Message> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final Message chatMessage) throws EncodeException {
		String result = Json.createObjectBuilder()
				.add("message", chatMessage.getMessage())
				.add("from", chatMessage.getFrom())
				.add("timestamp", chatMessage.getTimeStamp())
				.add("code", chatMessage.getCode().intValue())
				.build().toString();
		return 	result;
	}
}