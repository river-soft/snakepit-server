package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.Direction

class MyPackmanClient extends PacManClient {

    MyPackmanClient(String host, String username, String password) {
        super(host, username, password)
    }

    @Override
    Direction onRequest(ClientMessage message) {

        // Мое положение на карте
        def mePosition = this.me
        // Список обьектов на карте с их координатами (пекмены, монеты)
        def objects = getAllObjects(message.map)

        if (message.map[me.posX][me.posY + 1] == 1) {
            return Direction.UP
        } else if (message.map[me.posX][me.posY - 1] == 1){
            return Direction.DOWN
        }else if(message.map[me.posX - 1][me.posY] == 1){
            return Direction.RIGHT
        }

        return Direction.RIGHT
    }
}

class TestSdkApplication {

    static void main(String[] args) {
        new MyPackmanClient('localhost:8080', 'Николай', 'coly26341')
    }
}
