package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
class TestSdkApplication {

    static void main(String[] args) {
        def client = new PacManClient('snakepit.westeurope.cloudapp.azure.com/api/users', 'yvshvets', 'crjhjgthdjtfghtkz')
        client.onRequest = { ClientMessage m ->
            def myPacman = m.positions.find { x -> x.clientName == 'yvshvets' }
            def map = m.getMap()
            def count = 0
            println "my position ${myPacman.posY} x ${myPacman.posX}"
            if (map[myPacman.posX][myPacman.posY - 1] == 1) {
                return ['left', 'right', 'up', 'down'].get(2)
            } else {
                return ['left', 'right', 'up', 'down'].get(0)
            }
        }
    }
}
