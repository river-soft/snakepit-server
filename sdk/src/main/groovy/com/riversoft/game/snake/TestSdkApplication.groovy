package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.Direction

class MyPackmanClient extends PacManClient {

    MyPackmanClient(String host, String username, String password) {
        super(host, username, password)
    }

    @Override
    Direction onRequest(ClientMessage message) {
    return Direction.RIGHT

    }
}

class TestSdkApplication {

    static void main(String[] args) {
        new MyPackmanClient('192.168.1.192:8080', 'qwe', 'qwe')
    }

    //snakepit.westeurope.cloudapp.azure.com
}
