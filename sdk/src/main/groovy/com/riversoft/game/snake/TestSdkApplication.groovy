package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage

class TestSdkApplication {

    static void main(String[] args) {
        def client = new PacManClient('localhost:8080/', 'user', '1234')

        client.onRequest = { ClientMessage m ->

            ['left', 'right', 'up', 'down'].get(new Random().nextInt(3))
        }
    }
}
