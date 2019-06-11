package com.riversoft.game.snake.controller

import com.riversoft.game.snake.data.domain.User
import com.riversoft.game.snake.service.GameService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping('/api/finalResults')
class finalUsersController {

    @Autowired private GameService gameService

    @GetMapping
    List<User>  getRounds(){
        gameService.getFinalUsers()
    }

}
