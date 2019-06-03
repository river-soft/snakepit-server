package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.Direction

class MyPackmanClient extends PacManClient {

    MyPackmanClient(String host, String username, String password) {
        super(host, username, password)
    }

    @Override
    Direction onRequest(ClientMessage message) {
    return Direction.LEFT

    }
}

class TestSdkApplication {

    static void main(String[] args) {
        new MyPackmanClient('localhost:8080', 'Николай', 'coly26341')
    }
    //snakepit.westeurope.cloudapp.azure.com
}
