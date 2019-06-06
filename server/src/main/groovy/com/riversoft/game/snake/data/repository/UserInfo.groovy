package com.riversoft.game.snake.data.repository

import com.riversoft.game.snake.data.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
interface UserInfo extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username)
}
