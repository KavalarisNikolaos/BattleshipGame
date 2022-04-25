package GameFunctionality;

import GUI.Board;

public class HumanPlayer extends Player {

    private int totalTriesLeft=40;
    private final Board playerboard=new Board(false);
    public int count=0; //number of players shots that hit enemy ship
    public int totalpoints=0;
    public int[][] movarr = new int[40][2]; // coords of each attack
    public String[] hitstatusarr =new String[40]; //array that contains the result of each shot
    public String[] hitShipTypeArr =new String[40]; //array that contains the enemy ship type in case of hit , "" otherwise

    public int getTotalpoints() {
        return totalpoints;
    }

    public int getCount() {
        return count;
    }

    public int getTotalTriesLeft() {
        return totalTriesLeft;
    }

    public void reduceTotalTries(){totalTriesLeft--;}

    public Board getBoard(){
        return playerboard;
    }

}
