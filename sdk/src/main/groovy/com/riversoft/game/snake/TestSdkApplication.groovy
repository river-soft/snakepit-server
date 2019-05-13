package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage

class TestSdkApplication {

    static void main(String[] args) {
        def client = new PacManClient('localhost:8080', 'diana', 'edinorog')
        client.onRequest = { ClientMessage m ->
            ['right', 'left', 'up', 'down'].get(new Random().nextInt(3))
        }
    }
}

