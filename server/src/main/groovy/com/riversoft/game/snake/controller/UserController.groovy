package com.riversoft.game.snake.controller

import com.riversoft.game.snake.model.UserDto
import com.riversoft.game.snake.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping('/api/users')
class UserController {

    @Autowired
    UserService userService

    @GetMapping
    String getAllUsers(){
        userService.getAllUsers()
    }
}