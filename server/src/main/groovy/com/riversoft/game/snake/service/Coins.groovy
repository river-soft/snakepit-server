package com.riversoft.game.snake.service


import com.riversoft.game.snake.data.repository.UserRepository
import com.riversoft.game.snake.model.BattleState
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import groovy.util.logging.Slf4j
@Slf4j
class Coins {

    final BORDERS = 1

    int coinsX
    int coinsY
    int countCoins = 0

    public List<List> map
    Coins(List<List> map,coinsX,coinsY) {
        this.map = map
        this.coinsX = coinsX
        this.coinsY = coinsY
    }

    def generateCoins() {
            if(map[coinsX][coinsY] != BORDERS ) {
                map[coinsX][coinsY] = 3
            }
          }
    def addCoins(){
        def result  = 30 - map.count(3)
        1.times{
            map[new Random().nextInt(64)][new Random().nextInt(64)] = 3
        }
    }

    def checkCoins(){
        if(map.count(3)< 30){
                addCoins()
        }
    }
 }
