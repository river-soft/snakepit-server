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
    final EMPTYSPACE = 0
    final COINS = 3
    final PACMAN = 2

    int coinsX
    int coinsY

    public List<List> map

    Coins(List<List> map, coinsX, coinsY) {
        this.map = map
        this.coinsX = coinsX
        this.coinsY = coinsY
        if(this.map[this.coinsX][this.coinsY] == EMPTYSPACE ){
            this.map[this.coinsX][this.coinsY] = COINS
        }else if(this.map[this.coinsX][this.coinsY] != EMPTYSPACE) {
            while (this.map[this.coinsX][this.coinsY] != EMPTYSPACE) {
                this.coinsY -= 1
                this.coinsX -= 1
            }

            this.map[this.coinsX][this.coinsY] = COINS

        }else{
            while (this.map[this.coinsX][this.coinsY] == COINS | this.map[this.coinsX][this.coinsY] == PACMAN | this.map[this.coinsX][this.coinsY] == BORDERS) {
                this.coinsX += 1
            }

            this.map[this.coinsX][this.coinsY] = COINS
        }

    }



//    def addCoins(){
//        def result  = 30 - map.count(3)
//        1.times{
//            map[new Random().nextInt(64)][new Random().nextInt(64)] = 3
//        }
//    }

//    def checkCoins(){
//        if(map.count(3)< 30){
//                addCoins()
//        }
//    }
 }
