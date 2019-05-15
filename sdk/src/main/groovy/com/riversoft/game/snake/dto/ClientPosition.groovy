package com.riversoft.game.snake.dto

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class ClientPosition {

    String clientName

    Integer posX
    Integer posY
}
