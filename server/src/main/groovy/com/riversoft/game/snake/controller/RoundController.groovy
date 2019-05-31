package com.riversoft.game.snake.controller

import com.riversoft.game.snake.data.domain.User
import com.riversoft.game.snake.model.BattleState
import com.riversoft.game.snake.model.GameRezultModel
import com.riversoft.game.snake.service.GameService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Slf4j
@RestController
@RequestMapping('/api/game/rounds')
class RoundController {

    @Autowired private GameService gameService

    @GetMapping
    String  getRounds(){
        gameService.getRounds()
    }

}
