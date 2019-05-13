package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage

class TestSdkApplication {

    static void main(String[] args) {
        def client = new PacManClient('localhost:7000', 'Николай', '123')
        client.onRequest = { ClientMessage m ->
            ['right', 'left', 'up', 'down'].get(new Random().nextInt(0))
        }
    }
}
