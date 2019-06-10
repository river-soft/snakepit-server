package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.Direction
import com.riversoft.game.snake.dto.Element
import com.riversoft.game.snake.dto.ElementType
import groovy.util.logging.Log4j

class MyPackmanClient extends PacManClient {

    MyPackmanClient(String host, String username, String password) {
        super(host, username, password)
    }


    @Override
    Direction onRequest(ClientMessage message) {

       return Direction.DOWN

    }
}

class TestSdkApplication {

    static void main(String[] args) {

        new MyPackmanClient('3.122.9.136', 'qwe', 'qwe')

    }
}


