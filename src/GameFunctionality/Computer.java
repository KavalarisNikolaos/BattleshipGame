package GameFunctionality;

import GUI.Board;
import java.util.Random;
import GUI.Controller;

public class Computer extends Player {

    private int count = 0; //number of players shots that hit enemy ship
    private int index=-1;
    private int index1=-1;
    private boolean prevhit; //bool that indicates if previous shot was successful or not
    private int totalpoints = 0;
    private int totalTriesLeft = 40;
    private final Board computerboard = new Board(true);
    public String[] hitstatusarr= new String[40]; //array that contains the result of each shot
    public String[] hitShipTypeArr =new String[40]; //array that contains the enemy ship type in case of hit , "" otherwise

    public int getTotalpoints() {
        return totalpoints;
    }

    public int getTotalTriesLeft() {
        return totalTriesLeft;
    }

    public int getCount() {
        return count;
    }

    public Board getBoard() {
        return computerboard;
    }

    public void attack(HumanPlayer opponent) {
        Random rand = new Random();
        if (!prevhit) {
            int xc = rand.nextInt(10);
            int yc = rand.nextInt(10);
            if (!Controller.isMember(movarr, xc, yc)) {
                movarr[40 - getTotalTriesLeft()][0] = xc;
                movarr[40 - getTotalTriesLeft()][1] = yc;
                hitstatusarr[40 - getTotalTriesLeft()]="MISSED";
                hitShipTypeArr[40 - getTotalTriesLeft()]=" ";
                Board.Cell attackedCell = opponent.getBoard().getCell(yc, xc);
                attackedCell.shoot();
                totalTriesLeft--;
                if (attackedCell.getShip() != null) {
                    count++;
                    hitstatusarr[40 - getTotalTriesLeft()-1]="HIT";
                    hitShipTypeArr[40 - getTotalTriesLeft()-1]=attackedCell.getShip().getName();
                    prevhit = true;
                    index = 40 - getTotalTriesLeft() -1;
                    index1 = 40 - getTotalTriesLeft() - 1;
                    if (attackedCell.getShip().getState() == ShipState.WOUNDED) {
                        totalpoints += attackedCell.getShip().getHitpoints();
                    } else {
                        totalpoints += (attackedCell.getShip().getSunkpoints() + attackedCell.getShip().getHitpoints());
                    }
                }

            } else {
                attack(opponent);
            }
        } else {
            if (40 - getTotalTriesLeft()  - index1 == 1) {
                int xc = movarr[index][0] + 1;
                int yc = movarr[index][1];
                if (!Controller.isMember(movarr, xc, yc) & opponent.getBoard().isValidPoint(yc,xc)) {
                    movarr[40 - getTotalTriesLeft()][0] = xc;
                    movarr[40 - getTotalTriesLeft()][1] = yc;
                    hitstatusarr[40 - getTotalTriesLeft()]="MISSED";
                    hitShipTypeArr[40 - getTotalTriesLeft()]=" ";
                    Board.Cell attackedCell = opponent.getBoard().getCell(yc, xc);
                    attackedCell.shoot();
                    totalTriesLeft--;
                    if (attackedCell.getShip() != null) {
                        count++;
                        hitstatusarr[40 - getTotalTriesLeft()-1]="HIT";
                        hitShipTypeArr[40 - getTotalTriesLeft()-1]=attackedCell.getShip().getName();
                        index = 40 - getTotalTriesLeft() - 1;
                        index1++;
                        if (attackedCell.getShip().getState() == ShipState.WOUNDED) {
                            totalpoints += attackedCell.getShip().getHitpoints();
                        } else {
                            totalpoints += (attackedCell.getShip().getSunkpoints() + attackedCell.getShip().getHitpoints());
                        }
                    }


                }
                else{
                    index1--;
                    attack(opponent);
                }
            }
           else if (40 - getTotalTriesLeft()  - index1 == 2) {
                int xc = movarr[index][0] ;
                int yc = movarr[index][1]+1;
                if (!Controller.isMember(movarr, xc, yc) & opponent.getBoard().isValidPoint(yc,xc)) {
                    movarr[40 - getTotalTriesLeft()][0] = xc;
                    movarr[40 - getTotalTriesLeft()][1] = yc;
                    hitstatusarr[40 - getTotalTriesLeft()]="MISSED";
                    hitShipTypeArr[40 - getTotalTriesLeft()]=" ";
                    Board.Cell attackedCell = opponent.getBoard().getCell(yc, xc);
                    attackedCell.shoot();
                    totalTriesLeft--;
                    if (attackedCell.getShip() != null) {
                        count++;
                        hitstatusarr[40 - getTotalTriesLeft()-1]="HIT";
                        hitShipTypeArr[40 - getTotalTriesLeft()-1]=attackedCell.getShip().getName();
                        index = 40 - getTotalTriesLeft() - 1;
                        index1++;
                        if (attackedCell.getShip().getState() == ShipState.WOUNDED) {
                            totalpoints += attackedCell.getShip().getHitpoints();
                        } else {
                            totalpoints += (attackedCell.getShip().getSunkpoints() + attackedCell.getShip().getHitpoints());
                        }
                    }

                }
                else{
                    index1--;
                    attack(opponent);
                }
            }
            else if (40 - getTotalTriesLeft()  - index1 == 3) {
                int xc = movarr[index][0] - 1;
                int yc = movarr[index][1];
                if (!Controller.isMember(movarr, xc, yc) & opponent.getBoard().isValidPoint(yc,xc)) {
                    movarr[40 - getTotalTriesLeft()][0] = xc;
                    movarr[40 - getTotalTriesLeft()][1] = yc;
                    hitstatusarr[40 - getTotalTriesLeft()]="MISSED";
                    hitShipTypeArr[40 - getTotalTriesLeft()]=" ";
                    Board.Cell attackedCell = opponent.getBoard().getCell(yc, xc);
                    attackedCell.shoot();
                    totalTriesLeft--;
                    if (attackedCell.getShip() != null) {
                        count++;
                        hitstatusarr[40 - getTotalTriesLeft()-1]="HIT";
                        hitShipTypeArr[40 - getTotalTriesLeft()-1]=attackedCell.getShip().getName();
                        index = 40 - getTotalTriesLeft() - 1;
                        index1++;
                        if (attackedCell.getShip().getState() == ShipState.WOUNDED) {
                            totalpoints += attackedCell.getShip().getHitpoints();
                        } else {
                            totalpoints += (attackedCell.getShip().getSunkpoints() + attackedCell.getShip().getHitpoints());
                        }
                    }

                }
                else{
                    index1--;
                    attack(opponent);
                }
            }
            else if (40 - getTotalTriesLeft()  - index1 == 4) {
                int xc = movarr[index][0];
                int yc = movarr[index][1]-1;
                if (!Controller.isMember(movarr, xc, yc) & opponent.getBoard().isValidPoint(yc,xc)) {
                    movarr[40 - getTotalTriesLeft()][0] = xc;
                    movarr[40 - getTotalTriesLeft()][1] = yc;
                    hitstatusarr[40 - getTotalTriesLeft()]="MISSED";
                    hitShipTypeArr[40 - getTotalTriesLeft()]=" ";
                    Board.Cell attackedCell = opponent.getBoard().getCell(yc, xc);
                    attackedCell.shoot();
                    totalTriesLeft--;
                    if (attackedCell.getShip() != null) {
                        count++;
                        hitstatusarr[40 - getTotalTriesLeft()-1]="HIT";
                        hitShipTypeArr[40 - getTotalTriesLeft()-1]=attackedCell.getShip().getName();
                        index = 40 - getTotalTriesLeft() - 1;
                        index1++;
                        if (attackedCell.getShip().getState() == ShipState.WOUNDED) {
                            totalpoints += attackedCell.getShip().getHitpoints();
                        } else {
                            totalpoints += (attackedCell.getShip().getSunkpoints() + attackedCell.getShip().getHitpoints());
                        }
                    }


                }
                else{
                    index1--;
                    attack(opponent);
                }
            }
            else{
                prevhit=false;
                attack(opponent);
            }
        }

    }
}
