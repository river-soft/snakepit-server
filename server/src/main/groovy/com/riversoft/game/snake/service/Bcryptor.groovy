package com.riversoft.game.snake.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class Bcryptor {
    @Autowired
    PasswordEncoder passwordEncoder

    @Autowired
    UserService userService

    com.riversoft.game.snake.data.domain.User HashPassword(com.riversoft.game.snake.data.domain.User user) {
        def HashPassword = passwordEncoder.encode(user.getPasswordHash())
        user.setPasswordHash(HashPassword)
        return  userService.addNewUser(user)
    }
}
