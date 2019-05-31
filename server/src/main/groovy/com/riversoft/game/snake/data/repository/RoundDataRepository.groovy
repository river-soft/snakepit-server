package com.riversoft.game.snake.data.repository
import com.riversoft.game.snake.data.domain.Round
import org.springframework.data.mongodb.repository.MongoRepository

interface RoundDataRepository extends MongoRepository<Round, String> {

}

