package com.riversoft.game.snake.controller

import com.riversoft.game.snake.data.domain.User
import com.riversoft.game.snake.data.repository.UserRepository
import com.riversoft.game.snake.model.UserDto
import com.riversoft.game.snake.service.Bcryptor
import com.riversoft.game.snake.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Slf4j
@RestController
@RequestMapping('/api/users')
class UserController {

    @Autowired
    UserService userService
    @Autowired
    Bcryptor    bcryptor
    @GetMapping
    String getRezult(){
        return userService.getRezult()
    }
    @PostMapping
    String userData(@RequestBody() User user){
        log.info(user.toString())

        try {
            return bcryptor.HashPassword(user)
        } catch(Exception e) {
            throw new RuntimeException('Что-то пошло не так', e)
        }

    }
}
