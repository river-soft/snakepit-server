package com.riversoft.game.snake

import com.riversoft.game.snake.dto.ClientMessage
import com.riversoft.game.snake.dto.Direction

class MyPackmanClient extends PacManClient {

    MyPackmanClient(String host, String username, String password) {
        super(host, username, password)
    }

//    static def checkBorders (List<List> map, int x, int y) {
//      switch ( map[x][y] ) {
//              case 1 :
//              return false
//                 break
//              case 0 :
//              return true
//                 break
//              case 3:
//              return true
//
//      }
//    }



    @Override
    Direction onRequest(ClientMessage message) {
        def countXLeft = 0
        def countXRight = 0
        def countYUp = 0
        def countYDown = 0

        while (message.map[me.posX + 1][me.posY] != 3) {
            countXRight++
        }
        while (message.map[me.posX - 1][me.posY] != 3) {
            countXLeft++
        }
        while (message.map[me.posX][me.posY + 1] != 3) {
            countYDown++
        }
        while (message.map[me.posX][me.posY - 1] != 3) {
            countYUp++
        }



        println('в правую сторону  =   ' + countXRight.toString())
        println('в левую сторону  =   ' + countXLeft.toString())
        println('в верхнюю сторону  =   ' + countYUp.toString())
        println('в нижнюю сторону  =   ' + countYDown.toString())

        if (countXLeft < countXRight) {
            if  ( message.map[me.posX - 1][me.posY] != 1) {
                return Direction.LEFT
            } else {
                return Direction.RIGHT
            }
        } else if (countXLeft > countXRight) {
            if (  message.map[me.posX + 1][me.posY] != 1) {
                return Direction.RIGHT
            } else {
                return Direction.LEFT
            }
        } else {
           if (  message.map[me.posX][me.posY - 1] != 1 ) {
               return Direction.UP
           } else {
               return Direction.DOWN
           }
        }
    }
}

class TestSdkApplication {

    static void main(String[] args) {

        new MyPackmanClient('localhost:8080', 'огурчик', 'огурчик')

    }

}


