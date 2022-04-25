package GameFunctionality;

import GUI.Board;

public abstract class Player{
    private int count;
    public String[] hitstatusarr;
    public String[] hitShipTypeArr;
    public int[][] movarr= new int[40][2];
    private  Board getBoard;
    public int getTotalTriesLeft;
    public  int getTotalpoints;

}