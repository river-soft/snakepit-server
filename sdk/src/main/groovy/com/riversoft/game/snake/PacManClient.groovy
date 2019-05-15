package com.riversoft.game.snake

import com.fasterxml.jackson.databind.ObjectMapper
import com.riversoft.game.snake.dto.ClientMessage
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.*
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest
import org.eclipse.jetty.websocket.client.WebSocketClient

import java.util.concurrent.TimeUnit

class PacManClient {

    private WebSocketClient socket
    private String url = 'localhost:8080'
    private String username
    private String password
    private Session session

    def onRequest = {ClientMessage x -> }

    @WebSocket
    class ClientSocket {

        @OnWebSocketConnect
        void onConnect(Session session) {
            print("Opened connection " + session.toString());
        }

        @OnWebSocketClose
        void onClose(int closeCode, String message) {
            print("Closed with message: '" + message + "' and code: " + closeCode);
        }

        @OnWebSocketError
        void onError(Session session, Throwable reason) {
            println "Error $reason"
        }

        @OnWebSocketMessage
        void onMessage(String data) {

            def message = new ObjectMapper().readValue(data, ClientMessage)
            showMap(message.map)

            def answer = onRequest(message)

            println "answer $answer"
            session.getRemote().sendString(answer)
        }
    }

    PacManClient(String url, String username, String password) {

        this.url        = url
        this.username   = username
        this.password   = password

        socket = new WebSocketClient()
        socket.start()

        def request = new ClientUpgradeRequest();
        request.setHeader("Authorization","Basic ${"$username:$password".bytes.encodeBase64()}")

        session = socket.connect(new ClientSocket(), ('ws://' + url + '/pacman-game').toURI(), request).get(1, TimeUnit.SECONDS)
    }

    Map symbol = [
            0: ' ',
            1: '+',
            3: '@',
            2: 'O'
    ]

    def showMap(ArrayList<List<Integer>> lists) {

        lists.each {x ->
            x.each {y ->

                print symbol[y]
            }
            println()
        }
    }
}
