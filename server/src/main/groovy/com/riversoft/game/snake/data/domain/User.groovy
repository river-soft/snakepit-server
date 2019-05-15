package com.riversoft.game.snake.data.domain

import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'users')
@ToString(includeNames = true )
class User {

    @Id
    String id

    @Indexed(unique = true)
    String username

    String passwordHash
    int rating
}
