package com.riversoft.game.snake.service

import com.riversoft.game.snake.data.repository.UserRepository
import com.riversoft.game.snake.model.BattleState
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Slf4j
@Service
class UserPackman {
    private List map
    UserPackman(List map) {
        this.map = map

    }
    def moveLeft () {

    }
    def moveRight () {

    }
    def moveUp () {

    }
    def moveDown () {

    }
}