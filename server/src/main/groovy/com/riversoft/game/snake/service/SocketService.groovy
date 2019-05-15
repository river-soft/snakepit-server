package com.riversoft.game.snake.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.riversoft.game.snake.dto.ClientMessage
import org.springframework.stereotype.Service
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@Service
class SocketService extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = []
    private Map<WebSocketSession, CompletableFuture<TextMessage>> result = new ConcurrentHashMap()

    @Override
    void afterConnectionEstablished(WebSocketSession session) throws Exception {
        def s = sessions.find {it.principal.name == session.principal.name}
        if (s) {
            sessions.remove(s)
            result.remove(s)
        }
        sessions.add(session)
        result.put(session, new CompletableFuture())
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        result[session].complete(message)
    }

    @Override
    void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session)
        result.remove(sessions)
    }

    List<Map> getClientAnswer(ClientMessage message) {

        for (def r : result) {
            r.value = new CompletableFuture()
        }

        sessions*.sendMessage(new TextMessage(new ObjectMapper().writeValueAsBytes(message)))

        result.collect {
            try {
                return [client: it.key.principal?.name, data: it.value.get(1, TimeUnit.SECONDS).payload]
            } catch (Exception e) { }

            [client: it.key.principal?.name, data: '']
        }
    }
}
