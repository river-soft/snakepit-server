package com.riversoft.game.snake.service

import groovy.util.logging.Slf4j
@Slf4j
class Coins {

    final EMPTYSPACE = 0
    final COINS = 3
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

        }

    }
 }
