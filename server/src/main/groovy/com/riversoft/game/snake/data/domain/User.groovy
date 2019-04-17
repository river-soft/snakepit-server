package com.riversoft.game.snake.data.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'users')
class User {

    @Id
    String id

    String username

    String passwordHash
}
