package tsuyoyo.fikaflag.websocket

import groovy.transform.TupleConstructor
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import javax.validation.constraints.NotNull
import java.util.concurrent.ConcurrentHashMap

@Component
class FikaPointSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        super.afterConnectionEstablished(session)
        sessionPool.put(session.getId(), session)
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        super.afterConnectionClosed session, status
        sessionPool.remove(session.getId())
    }

    @TupleConstructor
    private static class JoinMessage implements WebSocketMessage<String> {

        @NotNull
        final String clientId;

        String getPayload() {
            return null
        }

        int getPayloadLength() {
            return 0
        }

        boolean isLast() {
            return false
        }
    }

    public void notifyJoin(String clientId, String pointSessionId) {
        sessionPool.get(pointSessionId)?.sendMessage(new JoinMessage(clientId));
    }
}
