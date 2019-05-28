package com.riversoft.game.snake.service

class UserPackman {

    boolean dead

//const
    final BORDERS = 1
    final EMPTYSPACE = 0
    final COINS = 3

    // --------------------------------
    // --------------------------------

    String name
    int PACMAN = 2
    int x
    int y
    public int rating = 0
    public  int glrating = 0
    // --------------------------------
    // --------------------------------


    def onRating = {UserPackman pacman}

    private List<List> map

    UserPackman(List<List> map, String name, int x, y,rating) {
        this.rating = 0
        this.glrating += rating
        this.map = map
        this.name = name
        this.x = x
        this.y = y
        if(this.map[this.x][this.y] == EMPTYSPACE ){
            this.map[this.x][this.y] = PACMAN
        }else {
            while (this.map[this.x][this.y] != EMPTYSPACE) {
                this.y -= 1
                this.x -= 1
            }
//            this.map[this.x][this.y] = PACMAN
            while(this.map[this.x][this.y] == COINS | this.map[this.x][this.y] == PACMAN | this.map[this.x][this.y] == BORDERS){
                this.x+=1
            }
            this.map[this.x][this.y] = PACMAN
        }

    }


//      def checkCoins(int x, int y, int xNext, int yNext){
//
//               map[x][y] = EMPTYSPACE
//               map[xNext][yNext] = PACMAN
//
//            }
//
//    def moveLeft(){
//
//              checkCoins(x,y,x,(y - 1))
//
//            }


    def moveLeft() {
        if (map[x][y-1] == COINS) {
            map[x][y] = EMPTYSPACE
            y -=1
            map[x][y] = PACMAN
            rating++
            glrating ++
            onRating(this)
        }else if (map[x][y - 1] == BORDERS) {
            map[x][y] = 0
        }else if(map[x][y - 1] != PACMAN){
            map[x][y] = EMPTYSPACE
            y -= 1
            map[x][y] = PACMAN
        }else{
            map[x][y] = EMPTYSPACE
            y -= 2
            map[x][y] = PACMAN
        }
    }

    def moveRight() {
        if (map[x][y + 1] == COINS) {
            map[x][y] = EMPTYSPACE
            y +=1
            this.PACMAN = 21
            map[x][y] = PACMAN
            rating++
            glrating ++
            onRating(this)
        }else if (map[x][y + 1] == BORDERS) {
            this.PACMAN = 21
            map[x][y] = 0
        }else if(map[x][y + 1] != PACMAN){
            map[x][y] = EMPTYSPACE
            y += 1
            this.PACMAN = 21
            map[x][y] = PACMAN
        }else{
            map[x][y] = EMPTYSPACE
            y += 2
            map[x][y] = PACMAN
        }
    }

    def moveUp() {
        if (map[x - 1][y] == COINS) {
            map[x][y] = EMPTYSPACE
            x -=1
            map[x][y] = PACMAN
            rating++
            glrating ++
            onRating(this)
        }else if (map[x - 1][y] == BORDERS) {
            map[x][y] = 0
        }else if(map[x - 1][y] != PACMAN){
            map[x][y] = EMPTYSPACE
            x -= 1
            this.PACMAN = 21
            map[x][y] = PACMAN
        }else{
            map[x][y] = EMPTYSPACE
            x -= 2
            map[x][y] = PACMAN
        }

    }

    def moveDown() {
        if (map[x + 1][y] == COINS) {
            map[x][y] = EMPTYSPACE
            x +=1
            map[x][y] = PACMAN
            rating++
            glrating ++
            onRating(this)
        }else if (map[x + 1][y] == BORDERS) {
            map[x][y] = 0
        }else if(map[x + 1][y] != PACMAN){
            map[x][y] = EMPTYSPACE
            x += 1
            this.PACMAN = 21
            map[x][y] = PACMAN
        }else{
            map[x][y] = PACMAN
        }

    }

}
