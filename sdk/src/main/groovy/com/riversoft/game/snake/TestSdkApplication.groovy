package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
class TestSdkApplication {

    static void main(String[] args) {
        def client = new PacManClient('localhost:8080', 'Николай', 'coly26341')
        client.onRequest = { ClientMessage m ->
            def myPacman = m.positions.find { x -> x.clientName == 'Николай' }
            def map = m.getMap()
            def count = 0
                myPacman.posX++
                myPacman.posY++
               while(myPacman.posX != 1  && myPacman.posY != 1){
                   ['left','right','up','down'].get(1)
               }
            }
        }
    }
