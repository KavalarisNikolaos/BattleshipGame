package GUI;

import java.util.ArrayList;
import java.util.List;
import Exceptions.OverlapTilesException;
import Exceptions.OversizeException;
import GameFunctionality.Ship;
import GameFunctionality.Direction;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Exceptions.AdjacentTilesException;

public class Board  extends GridPane  {

    private  int excno = -1; //exception number-type initiated to -1
    private final VBox rows = new VBox();
    private boolean enemy = false;
    private int ships = 5;

    public  Ship[] shiparr = new Ship[]{
            new Ship("Carrier",1,5,Direction.Horizontal,350,1000),
            new Ship("Battleship",2,4,Direction.Horizontal,250,500),
            new Ship("Cruiser",3,3,Direction.Horizontal,100,250),
            new Ship("Submarine",4,3,Direction.Horizontal,100,0),
            new Ship("Destroyer",5,2,Direction.Horizontal,50,0)

    };

    public boolean isEnemy(){
        return enemy;
    }

    public int getShips() {
        return ships;
    }

    public int getExcno() {
        return excno;
    }

    public void setExcno(int excno) {
        this.excno = excno;
    }

    public  Board(boolean enemy) {

        this.enemy = enemy;

       for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            HBox stilesnum = new HBox();
            for (int k=0;k<=9;k++) {
                Label stili =new Label(String.valueOf(k));
                stilesnum.getChildren().add(stili);
                stili.setPadding(new Insets(0,12,0,12));

            }
            for (int x = 0; x < 10; x++) {
                Cell c = new Cell(x, y, this);
                if(x==0){
                    row.getChildren().add(c);
                }
                else {
                    row.getChildren().add(c);
                }
            }
            if(y==0) {
                rows.getChildren().add(row);
            }
            else {
                rows.getChildren().add(row);
            }

        }

        getChildren().add(rows);
    }

    public boolean placeShip(Ship ship, int x, int y) throws AdjacentTilesException,OversizeException, OverlapTilesException {


        if(canPlaceShip(ship, x, y)) {
            int length = ship.getSize();

            if (ship.getDirection() ==Direction.Vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.WHITE);
                        cell.setStroke(Color.GREEN);
                    }
                }
            }

            return true;
        }
        else{
            if (excno==0) {
                OversizeException er = new OversizeException("Ship coords out of bounds!");
                er.printStackTrace();

            }
            else if(excno==1) {
                OverlapTilesException er = new OverlapTilesException("Ships on top of each other!");
                er.printStackTrace();
            }
            else if(excno==2) {
                AdjacentTilesException er = new AdjacentTilesException("Ships too close!Abort");
                er.printStackTrace();
            }
        }
        return false;
    }

    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    private Cell[] getNeighbors(int x, int y) {
        List<Cell> neighbors = new ArrayList<Cell>();

        Point2D[] points = new Point2D[]{
                    new Point2D(x - 1, y),
                    new Point2D(x + 1, y),
                    new Point2D(x, y - 1),
                    new Point2D(x, y + 1)
            };

            for (Point2D p : points) {
                if (isValidPoint(p)) {
                    neighbors.add(getCell((int) p.getX(), (int) p.getY()));
                }
            }
            return neighbors.toArray(new Cell[0]);
        }

    private boolean canPlaceShip(Ship ship, int x, int y)  {
        int length = ship.getSize();

        if (ship.getDirection()==Direction.Vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i)) {
                    excno=0;
                    return false;
                }

                Cell cell = getCell(x, i);
                if (cell.ship != null) {
                    excno=1;
                    return false;
                }

                    for (Cell neighbor : getNeighbors(x, i)) {
                        if (!isValidPoint(x, i))
                            return false;

                        if (neighbor.ship != null) {
                            excno=2;
                            return false;
                        }
                    }

            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y)) {
                    excno=0;
                    return false;
                }
                Cell cell = getCell(i, y);
                if (cell.ship != null) {
                    excno=1;
                    return false;
                }
                    for (Cell neighbor : getNeighbors(i, y)) {
                        if (!isValidPoint(i, y))
                          return false;

                        if (neighbor.ship != null) {
                            excno=2;
                            return false;
                        }
                    }
            }
        }
        return true;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    public boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10 ;
    }

    public class Cell extends Rectangle {
        public int x, y;

        public Ship getShip() {
            return ship;
        }

        private Ship ship = null;

        private boolean wasShot = false;

        private Board board;

        public Cell(int x, int y, Board board) {

            super(30, 30);
            this.x = x;
            this.y = y;
            this.board = board;
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
        }

        public boolean shoot() {
            wasShot = true;
            setFill(Color.BLACK);
            if (board.getCell(x,y).getShip() != null) {
                int i;
                if(board.getCell(x,y).getShip().getType()==1) {
                    board.shiparr[0].hit();
                    i =0 ;
                }
                else if(board.getCell(x,y).getShip().getType()==2) {
                    board.shiparr[1].hit();
                    i =1 ;
                }
                else if(board.getCell(x,y).getShip().getType()==3) {
                    board.shiparr[2].hit();
                    i =2 ;
                }
                else if(board.getCell(x,y).getShip().getType()==4) {
                    board.shiparr[3].hit();
                    i =3 ;
                }
                else{
                    board.shiparr[4].hit();
                    i=4;
                }
                setFill(Color.RED);
                if (!board.shiparr[i].isAlive()) {
                    ships--;
                }
                return true;
            }
                return false;
        }

    }
    public void clear(Board board){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                Cell cell = board.getCell(i,j);
                cell.setFill(Color.LIGHTGRAY);
                cell.setStroke(Color.BLACK);
                cell.ship=null;
            }
        }
    }
}


