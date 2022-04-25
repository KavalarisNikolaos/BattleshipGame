package GUI;

import Exceptions.AdjacentTilesException;
import Exceptions.InvalidCountException;
import Exceptions.OverlapTilesException;
import Exceptions.OversizeException;
import GameFunctionality.Computer;
import GameFunctionality.Direction;
import GameFunctionality.HumanPlayer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.*;
import java.util.Random;


public class Controller {

    /* simple function that throws InvalidCountException when a negative number is passed as argument
        used to make sure that there is only one ship of each type for each player
     */
    public static void simpleChecker(int testint) throws InvalidCountException {
        if(testint>=0){ }
        else{
            throw new InvalidCountException("");
        }

    }
    public static boolean isMember(int[][] arr,int x,int y){
        boolean answer =false;
        for(int i=0;i<40;i++){
            if(arr[i][0]==x & arr[i][1]==y ){
                answer=true;
                break;
            }
            else answer=false;
        }
        return answer;
    }

    public static void readFilee(String filepath,Board board){

        int counter=1; //just wanting to make sure that even if many exceptions will be thrown , just one pop-up window will appear

        try {
            int checker1 = 1; //number of ships of type 1 , that are available to be placed
            int checker2 = 1; // number of ships of type 2 , that are available to be placed etc
            int checker3 = 1;
            int checker4 = 1;
            int checker5 = 1;
            int result[]=new int[2];
            BufferedReader reader=new BufferedReader(new FileReader(filepath));
            String line=reader.readLine();
            while (line!=null) {
                String[] array = new String[4];
                array = line.split(",", -2);
                result[0] = Integer.parseInt(array[1]);
                result[1] = Integer.parseInt(array[2]);
                try {
                    simpleChecker(checker1);
                    simpleChecker(checker2);
                    simpleChecker(checker3);
                    simpleChecker(checker4);
                    simpleChecker(checker5);
                    if (Integer.parseInt(array[0]) == 1) {
                        checker1 -= 1;
                        if (Integer.parseInt(array[3]) == 2) {
                            board.shiparr[0].setDirection(Direction.Vertical);
                            board.placeShip(board.shiparr[0], result[1], result[0]);

                        } else {
                            board.placeShip(board.shiparr[0], result[1], result[0]);
                        }
                    } else if (Integer.parseInt(array[0]) == 2) {
                        checker2 -= 1;
                        if (Integer.parseInt(array[3]) == 2) {
                            board.shiparr[1].setDirection(Direction.Vertical);
                            board.placeShip(board.shiparr[1], result[1], result[0]);
                        } else {
                            board.placeShip(board.shiparr[1], result[1], result[0]);
                        }
                    } else if (Integer.parseInt(array[0]) == 3) {
                        checker3 -= 1;
                        if (Integer.parseInt(array[3]) == 2) {
                            board.shiparr[2].setDirection(Direction.Vertical);
                            board.placeShip(board.shiparr[2], result[1], result[0]);
                        } else {
                            board.placeShip(board.shiparr[2], result[1], result[0]);
                        }
                    } else if (Integer.parseInt(array[0]) == 4) {
                        checker4 -= 1;
                        if (Integer.parseInt(array[3]) == 2) {
                            board.shiparr[3].setDirection(Direction.Vertical);
                            board.placeShip(board.shiparr[3], result[1], result[0]);
                        } else {
                            board.placeShip(board.shiparr[3], result[1], result[0]);
                        }
                    } else if (Integer.parseInt(array[0]) == 5) {
                        checker5 -= 1;
                        if (Integer.parseInt(array[3]) == 2) {
                            board.shiparr[4].setDirection(Direction.Vertical);
                            board.placeShip(board.shiparr[4], result[1], result[0]);
                        } else {
                            board.placeShip(board.shiparr[4], result[1], result[0]);
                        }

                    }
                } catch (Exception e) {
                    if (board.getExcno() == 0) {
                        OversizeException er = new OversizeException("Ship coords out of bounds!");
                        er.printStackTrace();
                    } else if (board.getExcno() == 1) {
                        OverlapTilesException er = new OverlapTilesException("Ships on top of each other!");
                        er.printStackTrace();
                    } else if (board.getExcno() == 2) {
                        AdjacentTilesException er = new AdjacentTilesException("Ships too close!Abort");
                        er.printStackTrace();
                    } else if (checker1 < 0 || checker2 < 0 || checker3 < 0 || checker4 < 0 || checker5 < 0) {
                        InvalidCountException er = new InvalidCountException("Too many same ships");
                        er.printStackTrace();
                    }
                }
                if(counter==1) {
                    if (board.getExcno() == 0) {
                        counter--;
                        exceptionPopup("Wrong Scenario: Ship coordinates out of board! ");
                    } else if (board.getExcno() == 1) {
                        counter--;
                        exceptionPopup("Wrong Scenario: Multiple Ships on the same sea-cell!");
                    } else if (board.getExcno() == 2) {
                        counter--;
                        exceptionPopup("Wrong Scenario: Ships too close to each other. ABORT THE SHIP!");
                    } else if (checker1 < 0 || checker2 < 0 || checker3 < 0 || checker4 < 0 || checker5 < 0) {
                        counter--;
                        exceptionPopup("Wrong Scenario: Each player can have exactly one ship of each type!");
                    }
                }
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException ioException) {
            board.setExcno(-2);
            ioException.printStackTrace();
        }

        if (board.getExcno() == -2 & !board.isEnemy()) {
            exceptionPopup("There is no Scenario with this id.Try again!");
        }

    }

    //handler when load button is pressed
    public static void handle() {
        Stage loadpopup=new Stage();
        loadpopup.initModality(Modality.APPLICATION_MODAL);
        loadpopup.setTitle("Load desired Scenario");
        VBox loadlayout = new VBox();
        Button loadbutton = new Button("Submit");
        TextField loadtxtfield=new TextField();

        loadbutton.setOnAction(event->{
            Main.board1.clear(Main.board1);
            Main.board2.clear(Main.board2);
            closeButton(Main.stage);
            Main main = new Main();
            main.start(new Stage());
            String x = loadtxtfield.getText();
            readFilee("MediaLab/player_SCENARIO_"+x+".txt",Main.board1);
            readFilee("MediaLab/enemy_SCENARIO_"+x+".txt",Main.board2);
            Main.isLoaded=true;
            Main.loadedNum=Integer.parseInt(x);
            Main.randStart=new Random().nextInt(2);
            if (Main.randStart == 1) {
                Main.LabelFirst2Play.setText("Computer plays First!");
            } else {
                Main.LabelFirst2Play.setText("You play First!");
            }
            Main.startGame();
            loadpopup.close();
        });

        loadlayout.getChildren().addAll(loadtxtfield,new Separator(),new Separator(),loadbutton);
        loadlayout.setAlignment(Pos.CENTER);
        Scene loadscene = new Scene(loadlayout,300,250);
        loadpopup.setScene(loadscene);

        loadpopup.show();
    }

    //handler when <Enemy Ships> button from Details Menu is pressed
    public static void showEnemyShips(){
        Stage loadpopup=new Stage();
        loadpopup.initModality(Modality.APPLICATION_MODAL);
        loadpopup.setTitle("Check enemy ships!");
        VBox vBox =new VBox();
        HBox hBox1 = new HBox();
        Label label11 = new Label(Main.com.getBoard().shiparr[0].getName());
        Label label12 = new Label(String.valueOf(Main.com.getBoard().shiparr[0].getState()));
        hBox1.getChildren().add(label11);
        hBox1.getChildren().add(label12);
        HBox hBox2 = new HBox();
        Label label21 = new Label(Main.com.getBoard().shiparr[1].getName());
        Label label22 = new Label(String.valueOf(Main.com.getBoard().shiparr[1].getState()));
        hBox2.getChildren().add(label21);
        hBox2.getChildren().add(label22);
        HBox hBox3 = new HBox();
        Label label31 = new Label(Main.com.getBoard().shiparr[2].getName());
        Label label32 = new Label(String.valueOf(Main.com.getBoard().shiparr[2].getState()));
        hBox3.getChildren().add(label31);
        hBox3.getChildren().add(label32);
        HBox hBox4 = new HBox();
        Label label41 = new Label(Main.com.getBoard().shiparr[3].getName());
        Label label42 = new Label(String.valueOf(Main.com.getBoard().shiparr[3].getState()));
        hBox4.getChildren().add(label41);
        hBox4.getChildren().add(label42);
        HBox hBox5 = new HBox();
        Label label51 = new Label(Main.com.getBoard().shiparr[4].getName());
        Label label52 = new Label(String.valueOf(Main.com.getBoard().shiparr[4].getState()));
        hBox5.getChildren().add(label51);
        hBox5.getChildren().add(label52);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(20);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(20);
        hBox3.setAlignment(Pos.CENTER);
        hBox3.setSpacing(20);
        hBox4.setAlignment(Pos.CENTER);
        hBox4.setSpacing(20);
        hBox5.setAlignment(Pos.CENTER);
        hBox5.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Scene shipScene = new Scene(vBox,300,250);
        loadpopup.setScene(shipScene);
        loadpopup.show();
    }

    //handler when <Player Shots> button from Details Menu is pressed
    public static void showPlayerShots(HumanPlayer p) {
        Stage loadpopup = new Stage();
        loadpopup.initModality(Modality.APPLICATION_MODAL);
        loadpopup.setTitle("Check last 5 Player shots!");
        VBox vBox = new VBox();
        for (int i=0;i<5;i++) {
            if (40 - p.getTotalTriesLeft() - 1 - i >= 0) {
                HBox hBox = new HBox();
                Label label11 = new Label(String.valueOf(p.movarr[40 - p.getTotalTriesLeft() - 1-i][0]));
                Label label12 = new Label(String.valueOf(p.movarr[40 - p.getTotalTriesLeft() - 1-i][1]));
                Label label13 = new Label(p.hitstatusarr[40 - p.getTotalTriesLeft() - 1-i]);
                Label label14 = new Label(p.hitShipTypeArr[40 - p.getTotalTriesLeft() - 1-i]);
                hBox.getChildren().add(label11);
                hBox.getChildren().add(label12);
                hBox.getChildren().add(label13);
                hBox.getChildren().add(label14);
                vBox.getChildren().add(hBox);
                hBox.setSpacing(20);
                hBox.setAlignment(Pos.CENTER);
            }
            else{
                break;
            }
        }
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Scene pShotScene = new Scene(vBox, 300, 250);
        loadpopup.setScene(pShotScene);
        loadpopup.show();

    }

    //handler when <Enemy Shots> button from Details Menu is pressed
    public static void showEnemyShots(Computer com) {
        Stage loadpopup = new Stage();
        loadpopup.initModality(Modality.APPLICATION_MODAL);
        loadpopup.setTitle("Check last 5 Computer shots!");
        VBox vBox = new VBox();
        for (int i=0;i<5;i++) {
            if (40 - com.getTotalTriesLeft() - 1 - i >= 0) {
                HBox hBox = new HBox();
                Label label11 = new Label(String.valueOf(com.movarr[40 - com.getTotalTriesLeft() - 1-i][0]));
                Label label12 = new Label(String.valueOf(com.movarr[40 - com.getTotalTriesLeft() - 1-i][1]));
                Label label13 = new Label(com.hitstatusarr[40 - com.getTotalTriesLeft() - 1-i]);
                Label label14 = new Label(com.hitShipTypeArr[40 - com.getTotalTriesLeft() - 1-i]);
                hBox.getChildren().add(label11);
                hBox.getChildren().add(label12);
                hBox.getChildren().add(label13);
                hBox.getChildren().add(label14);
                vBox.getChildren().add(hBox);
                hBox.setSpacing(20);
                hBox.setAlignment(Pos.CENTER);
            }
            else{
                break;
            }
        }
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            Scene comShotScene = new Scene(vBox, 300, 250);
            loadpopup.setScene(comShotScene);
            loadpopup.show();

    }

    public static void showWinner(String winner){
        Stage winnerscreen =new Stage();
        winnerscreen.initModality(Modality.APPLICATION_MODAL);
        winnerscreen.setTitle("Winner Screen");
        Label winnerlabel = new Label();
        winnerlabel.setText(winner + " " + "Won!");
        winnerlabel.setAlignment(Pos.CENTER);
        Scene winnerscene = new Scene(winnerlabel,300,250);
        winnerscreen.setScene(winnerscene);
        winnerscreen.show();
    }

    public static void exceptionPopup(String text) {
        Stage excScreen = new Stage();
        excScreen.initModality(Modality.APPLICATION_MODAL);
        excScreen.setTitle("Exception Occured");
        Label excLabel = new Label();
        excLabel.setText(text);
        excLabel.setAlignment(Pos.CENTER);
        excLabel.setTextFill(Color.RED);
        excLabel.setStyle("-fx-font: 22 arial;");
        Main.board1.clear(Main.board1);
        Main.board2.clear(Main.board2);
        closeButton(Main.stage);
        Main main = new Main();
        main.start(new Stage());
        Scene excScene = new Scene(excLabel,700,600);
        excScreen.setScene(excScene);
        excScreen.show();
    }

    public static void changeLabel(HumanPlayer p, Computer com ,Label label1,Label label2,Label label3,Label label4,Label label5,Label label6){
        label1.setText("Player Moves" + " " + String.valueOf(40-p.getTotalTriesLeft()));
        label2.setText("Player Points" + " " + String.valueOf(p.getTotalpoints()));
        label3.setText("Player % Success" + " " + (double) Math.round(100 * ((p.getCount() * 1.0) / (40 - p.getTotalTriesLeft())))+ " %");
        label4.setText("Com Moves" + " " + String.valueOf(40-com.getTotalTriesLeft()));
        label5.setText("Com Points" + " " + String.valueOf(com.getTotalpoints()));
        label6.setText("Com % Success" + " " + (double) Math.round(100 * ((com.getCount() * 1.0) / (40 - com.getTotalTriesLeft())))+ " %");
    }

    //handler when Exit button is pressed
    public static void closeButton(Stage stage){
        stage.close();
    }

    //handler when Start button is pressed
    public static void startButton(){
        if(Main.isLoaded) {
            closeButton(Main.stage);
            Main main = new Main();
            main.start(new Stage());
            readFilee("C:\\Users\\User.DESKTOP-BQTIL39\\Documents\\DevBattleship\\MediaLab/player_SCENARIO_"+Main.loadedNum+".txt",Main.board1);
            readFilee("C:\\Users\\User.DESKTOP-BQTIL39\\Documents\\DevBattleship\\MediaLab/enemy_SCENARIO_"+Main.loadedNum+".txt",Main.board2);
            if(Main.randStart==1){
                Main.LabelFirst2Play.setText("Computer plays First!");
            }
            else {
                Main.LabelFirst2Play.setText("You play First!");
            }
            Main.startGame();
        }
    }

}
