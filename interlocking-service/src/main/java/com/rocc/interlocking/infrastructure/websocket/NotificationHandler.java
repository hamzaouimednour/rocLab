package com.rocc.interlocking.infrastructure.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class NotificationHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("[+] New WebSocket connection: " + session.getId());
    }

    /**
     * Send position update using modern Java 21 formatted() method
     */
    public void sendPositionUpdate(String lrtNumber, double lat, double lon, int speed, String message) {
        String payload = """
            {
              "type": "POSITION_UPDATE",
              "lrtNumber": "%s",
              "latitude": %.6f,
              "longitude": %.6f,
              "speed": %d,
              "message": "%s"
            }
            """.formatted(lrtNumber, lat, lon, speed, message);

        broadcast(payload);
    }

    /**
     * Send route validation result
     */
    public void sendRouteUpdate(String lrtNumber, Object result) {
        String payload = """
            {
              "type": "ROUTE_UPDATE",
              "lrtNumber": "%s",
              "result": %s
            }
            """.formatted(lrtNumber, result);

        broadcast(payload);
    }

    private void broadcast(String message) {
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            } catch (Exception e) {
                sessions.remove(session);
            }
        }
    }
}