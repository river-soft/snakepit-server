package com.riversoft.game.snake.data.domain

import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@ToString(includePackage = false, includeNames = true)
@Document(collection = 'rounds')
class Round {

    @Id
    String id

    int roundId

    Date startRoundDate

    int startPacmanCount
    int endPackmanCount
    int endCoinsCount

    List<UserRoundInformation> userRoundInformations = []
}

@ToString(includeNames = true, includePackage = false)
class UserRoundInformation {
    String name
    int localRating
    int globalRating
    boolean dead
    int lifeTime
    int efficiency
}