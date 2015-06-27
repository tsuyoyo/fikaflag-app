package tsuyoyo.fikaflag.websocket

import java.util.concurrent.ConcurrentHashMap

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.Invitation

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

@Component
class ClientSocketHandler extends TextWebSocketHandler {
	
	private Map<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		super.afterConnectionEstablished(session)
		sessionPool.put(session.getId(), session)
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		sessionPool.entrySet().each {
			it.getValue().sendMessage(message.payload);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed session, status
		sessionPool.remove(session.getId())
	}
			
	public void broadcastFlag(FikaFlag flag) {
		
		Invitation invitation = new Invitation();
		invitation.message = flag.message + " at " + flag.location;
//		invitation.createdby = session.getId();
		invitation.startTime = new Date(Calendar.getInstance().getTimeInMillis());//flag.date;
			
		String json = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(invitation);
		} catch (JsonProcessingException e) {
			json = e.getMessage();
		}

		TextMessage msg = new TextMessage(json);
				
		sessionPool.entrySet().each {
			it.getValue().sendMessage(msg)
		}
	}

}
