package GameFunctionality;

/**
 * This class implements a ship of Battleship game
 * @author Nikolaos Kavalaris
 */

 public  class  Ship {
     private final int type;
     private final String name;
     private final int size;
     private int lives;
     private final int hitpoints;
     private final int sunkpoints;
     private Direction direction;

    /**
     * This method constructs a ship with specified name,type,size,direction,hitpoints and sunkpoints
     * @param name  the name of the ship
     * @param type  the type of the ship
     * @param size the size of the ship
     * @param direction the direction of the ship , vertical or horizontal
     * @param hitpoints the points that the opponent earns when the particular ship type is hit
     * @param sunkpoints the bonus points the opponent earns when the particular ship type is hit and destroyed
     */

     public Ship(String name, int type, int size, Direction direction, int hitpoints, int sunkpoints){
        this.type=type;
        this.name=name;
        this.size=size;
        this.lives=size;
        this.hitpoints=hitpoints;
        this.sunkpoints=sunkpoints;
        this.direction=direction;
    }

    /**
     * This method returns the ship's type
     * @return this ship's type
     */
    public int getType(){return type;}

    /**
     * This method returns the ship's size
     * @return this ship's size
     */
    public int getSize() {
        return size;
    }


    /**
     * This method returns the ship's direction
     * @return this ship's direction,horizontal or vertical
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * This method sets this ship's direction to the specified direction
     * @param direction the ship's direction to be set
     */
    public void setDirection(Direction direction) {
        this.direction=direction;
    }


    /**
     * This method returns the ship's name
     * @return this ship's name
     */
    public String getName(){
        return name;
    }

    /**
     * This method returns the ship's hit points
     * @return this ship's hit points
     */
    public int getHitpoints() {
        return hitpoints;
    }

    /**
     * This method returns the ship's bonus points when it is sunk
     * @return this ship's sunk points
     */
    public int getSunkpoints() {
        return sunkpoints;
    }

    /**
     * This method returns the ship's state,no_hit or wounded or sunk
     * @return this ship's current state
     */
    public ShipState getState(){
        if(lives == 0) {
            return ShipState.SUNK;
        } else if(lives < size) {
            return ShipState.WOUNDED;
        } else {
            return ShipState.NO_HIT;
        }
    }

    /**
     * This method reduces the lives of a ship by one
     * each time this ship gets hit . Lives are initialised
     * equal to the ship's size
     */
    public void hit() {
        lives--;
    }

    /**
     * This method checks whether this ship is or is not destroyed
     * @return true if this ship is NOT sunk and false if this ship is destroyed
     */
    public boolean isAlive() {
        return lives > 0;
    }
 }
