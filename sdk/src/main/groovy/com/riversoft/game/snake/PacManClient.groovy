package com.riversoft.game.snake

import com.fasterxml.jackson.databind.ObjectMapper
import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.ClientPosition
import com.riversoft.game.snake.dto.Direction
import com.riversoft.game.snake.dto.Element
import com.riversoft.game.snake.dto.ElementType
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.*
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest
import org.eclipse.jetty.websocket.client.WebSocketClient

import java.util.concurrent.TimeUnit

abstract class PacManClient {

    protected WebSocketClient socket
    protected String host = 'localhost:8080'
    protected String username
    protected String password
    protected Session session

    boolean showMap = true
    ClientPosition me

    abstract Direction onRequest(ClientMessage message)

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

            me = message.positions.find { it.clientName == username }

            if (me) {
                if (showMap) {
                    showMap(message.map)
                }

                def answer = onRequest(message)

                println "answer $answer"
                session.getRemote().sendString(answer.toString().toLowerCase())
            }
        }
    }

    PacManClient(String host, String username, String password) {

        this.host       = host
        this.username   = username
        this.password   = password

        socket = new WebSocketClient()
        socket.start()

        def request = new ClientUpgradeRequest()
        request.setHeader("Authorization","Basic ${"$username:$password".bytes.encodeBase64()}")

        session = socket.connect(new ClientSocket(), ('ws://' + this.host + '/pacman-game').toURI(), request).get(1, TimeUnit.SECONDS)
    }

    Map symbol = [
            0: ' ',
            1: '+',
            3: '@',
            20: 'O',
            21: 'O'
    ]

    static List<Element> getAllObjects(List<List> map, ElementType... types) {
        List<Element> result = []
        map.eachWithIndex { List entry, int y ->
            entry.eachWithIndex{ Object cell, int x ->
                switch (cell) {
                    case 1:
                        result.add(new Element(type: ElementType.BORDER, x: x, y: y))
                        break
                    case 20:
                    case 21:
                        result.add(new Element(type: ElementType.PACMAN, x: x, y: y))
                        break
                    case 3:
                        result.add(new Element(type: ElementType.COIN, x: x, y: y))
                        break
                }
            }
        }
        result.findAll { types.contains(it.type) }
    }

    private void showMap(ArrayList<List<Integer>> lists) {

        lists.each {x ->
            x.each {y ->

                print symbol[y]
            }
            println()
        }
    }
}
