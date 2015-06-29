package tsuyoyo.fikaflag.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import tsuyoyo.fikaflag.websocket.ClientSocketHandler
import tsuyoyo.fikaflag.websocket.FikaPointSocketHandler

@Configuration
@EnableWebSocket
class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private ClientSocketHandler messageHandler;

	@Autowired
	private FikaPointSocketHandler fikaPointSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(messageHandler, "/client").setAllowedOrigins("*");

		registry.addHandler(fikaPointSocketHandler, "/point").setAllowedOrigins("*");
	}
}
