package com.riversoft.game.snake.data.domain

import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'rounds')

class Round {

    @Id
    String id

    int roundId

    int localRating

    String pacmanNames

}
