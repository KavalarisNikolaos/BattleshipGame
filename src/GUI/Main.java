package GUI;

import GameFunctionality.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;


public class Main extends Application {

    static Random rand=new Random();
    static int randStart = rand.nextInt(2);
    static Board board1;
    static Board board2;
    static HumanPlayer p1;
    static Computer com;
    static Label label1;
    static Label label2;
    static Label label3;
    static Label label4;
    static Label label5;
    static Label label6;
    static Label LabelFirst2Play;
    static int loadedNum = -1;
    static boolean isLoaded = false;
    static Stage stage = null;
    private Clip clip;


    @Override
    public void start(Stage primaryStage) {

        //creating main window-primary stage
        primaryStage.setTitle("MediaLab BattleShip");
        p1 = new HumanPlayer();
        com = new Computer();
        board1 = p1.getBoard();
        board2 = com.getBoard();
        stage=primaryStage;
        LabelFirst2Play=new Label();
        LabelFirst2Play.setStyle("-fx-font: 30 arial;");

        SplitPane center = new SplitPane(board1, board2);
        center.setPrefSize(400, 400);
        center.setStyle("-fx-background-color: #3498DB; ");

        VBox centerVbox = new VBox();
        Label label1a = new Label("0");
        Label label2a = new Label("1");
        Label label3a = new Label("2");
        Label label4a = new Label("3");
        Label label5a = new Label("4");
        Label label6a = new Label("5");
        Label label7a = new Label("6");
        Label label8a = new Label("7");
        Label label9a = new Label("8");
        Label label10a = new Label("9");
        centerVbox.getChildren().addAll(label1a,label2a,label3a,label4a,label5a,label6a,label7a,label8a,label9a,label10a);
        centerVbox.setSpacing(14);
        centerVbox.setPadding(new Insets(10,0,0,0));

        HBox centerHbox = new HBox();
        Label label1b = new Label("0");
        Label label2b = new Label("1");
        Label label3b = new Label("2");
        Label label4b = new Label("3");
        Label label5b = new Label("4");
        Label label6b = new Label("5");
        Label label7b = new Label("6");
        Label label8b = new Label("7");
        Label label9b = new Label("8");
        Label label10b = new Label("9");
        Label label1b2 = new Label("                                        0");
        Label label2b2 = new Label("1");
        Label label3b2 = new Label("2");
        Label label4b2 = new Label("3");
        Label label5b2 = new Label("4");
        Label label6b2 = new Label("5");
        Label label7b2 = new Label("6");
        Label label8b2 = new Label("7");
        Label label9b2 = new Label("8");
        Label label10b2 = new Label("9");

        centerHbox.getChildren().addAll(label1b,label2b,label3b,label4b,label5b,label6b,label7b,label8b,label9b,label10b
        ,label1b2,label2b2,label3b2,label4b2,label5b2,label6b2,label7b2,label8b2,label9b2,label10b2);
        centerHbox.setSpacing(24);
        centerHbox.setPadding(new Insets(0,0,0,77));

        HBox testhbox = new HBox();
        testhbox.getChildren().addAll(centerVbox,center);

        VBox testvbox = new VBox();
        testvbox.getChildren().addAll(centerHbox,testhbox);

        MenuItem menuItem1 = new MenuItem("Start");
        menuItem1.setOnAction(e->{
            Controller.startButton();
        });
        MenuItem menuItem2 = new MenuItem("Load");
        menuItem2.setOnAction(e-> {
            Controller.handle();
        });
        MenuItem menuItem3 = new MenuItem("Exit");
        menuItem3.setOnAction(e->{
            Controller.closeButton(primaryStage);
                });

        MenuButton menuButton1 = new MenuButton("Application", null, menuItem1, menuItem2, menuItem3);

        MenuItem menuItem4 = new MenuItem("Enemy Ships" );
        menuItem4.setOnAction(e->{
            Controller.showEnemyShips();
        });
        MenuItem menuItem5 = new MenuItem("Player shots"  );
        menuItem5.setOnAction(e->{
            Controller.showPlayerShots(p1);
        });
        MenuItem menuItem6 = new MenuItem("Enemy Shots");
        menuItem6.setOnAction(e->{
            Controller.showEnemyShots(com);
        });

        MenuButton menuButton2 = new MenuButton("Details", null, menuItem4, menuItem5, menuItem6);

        VBox vBox = new VBox();
        vBox.getChildren().add(menuButton1);
        vBox.getChildren().add(new Separator());
        vBox.getChildren().add(menuButton2);

        VBox playerinfo = new VBox();
        label1 = new Label();
        label1.setText("Player Moves"+" "+ (40- p1.getTotalTriesLeft()));
        label2 = new Label();
        label2.setText("Player Points"+ " " + p1.getTotalpoints());
        label3 = new Label();
        label3.setText("Player % Success");
        playerinfo.getChildren().addAll(label1,label2,label3);
        playerinfo.setPadding(new Insets(20,150,20,150));

        VBox computerinfo = new VBox();
        label4 = new Label();
        label4.setText("Com Moves"+ " " + (40- com.getTotalTriesLeft()));
        label5 = new Label();
        label5.setText("Com Points" + " " + com.getTotalpoints());
        label6 = new Label();
        label6.setText("Com % Success");
        computerinfo.getChildren().addAll(label4,label5,label6);
        computerinfo.setPadding(new Insets(20,75,20,150));

        //down part
        VBox downpart = new VBox();
        downpart.setSpacing(20);
        HBox coordinates = new HBox();
        coordinates.setPadding(new Insets(20,200,20,200));
        coordinates.setSpacing(30);
        TextField xCoor = new TextField("X");
        TextField yCoor = new TextField("Y");
        coordinates.getChildren().add(xCoor);
        coordinates.getChildren().add(1,new Separator());
        coordinates.getChildren().add(yCoor);
        Button submit = new Button("Attack!");
        submit.setPrefSize(200,40);
        submit.setStyle("-fx-background-color: #FF4933; ");
        submit.setOnAction(e->{                        // handler when Attack button is pressed
            String xcoor=xCoor.getText();
            String ycoor=yCoor.getText();
            Board.Cell attackedcell= board2.getCell(Integer.parseInt(ycoor),Integer.parseInt(xcoor));
            attackedcell.shoot();
            p1.movarr[40-p1.getTotalTriesLeft()][0]=Integer.parseInt(xcoor);
            p1.movarr[40-p1.getTotalTriesLeft()][1]=Integer.parseInt(ycoor);
            p1.hitstatusarr[40-p1.getTotalTriesLeft()]="MISSED";
            p1.hitShipTypeArr[40-p1.getTotalTriesLeft()]=" ";
            p1.reduceTotalTries();

            if(attackedcell.getShip()!=null) {
                enableMusic("sounds/blast.wav");
                p1.count++;
                p1.hitstatusarr[40-p1.getTotalTriesLeft()-1]="HIT";
                p1.hitShipTypeArr[40-p1.getTotalTriesLeft()-1]=attackedcell.getShip().getName();

                if(attackedcell.getShip().getState()==ShipState.WOUNDED){
                   p1.totalpoints += attackedcell.getShip().getHitpoints();
                }
                else if(attackedcell.getShip().getState()==ShipState.SUNK){
                    p1.totalpoints += (attackedcell.getShip().getSunkpoints()+attackedcell.getShip().getHitpoints());
                }
            }
            else{
                enableMusic("sounds/quack.wav");
            }

            if(p1.getTotalTriesLeft()==0){
                if(randStart==1){
                    if(p1.getTotalpoints()> com.getTotalpoints()){
                        // System.out.println(p1.getTotalpoints());
                        // System.out.println(com.getTotalpoints());
                        //System.out.println("You won");
                        Controller.showWinner("You");
                    }
                    else if(p1.getTotalpoints() < com.getTotalpoints()){
                        Controller.showWinner("Computer");
                    }
                    else{
                        Controller.showWinner("No one");
                    }
                }
                else{
                    com.attack(p1);
                    if(p1.getTotalpoints() > com.getTotalpoints()){
                       // System.out.println(p1.getTotalpoints());
                       // System.out.println(com.getTotalpoints());
                        //System.out.println("You won");
                        Controller.showWinner("You");
                    }
                    if(p1.getTotalpoints() < com.getTotalpoints()){
                        Controller.showWinner("Computer");
                    }
                    else{
                        Controller.showWinner("No one");
                    }
                }
            }

            else if(p1.getBoard().getShips()==0 || com.getBoard().getShips()==0){
                if(p1.getBoard().getShips()==0){
                   // System.out.println("you lost");
                    Controller.showWinner("Computer");
                }
                if(com.getBoard().getShips()==0){
                    System.out.println(p1.getBoard().getShips());
                   // System.out.println("you won");
                    Controller.showWinner("You");
                }
            }
            else if(com.getTotalTriesLeft()>0){
                    com.attack(p1);
            }
            Controller.changeLabel(p1,com,label1,label2,label3,label4,label5,label6);
        });
        downpart.getChildren().addAll(coordinates,LabelFirst2Play,submit);
        downpart.setPadding(new Insets(0,100,0,100));

        //top part
        HBox topdata =new HBox();
        topdata.setSpacing(100);
        topdata.getChildren().addAll(playerinfo,computerinfo);

        playerinfo.setAlignment(Pos.CENTER);
        downpart.setAlignment(Pos.CENTER);


        BorderPane root = new BorderPane();
        root.setCenter(testvbox);
        root.setLeft(vBox);
        root.setTop(topdata);
        root.setBottom(downpart);


        LabelFirst2Play.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        topdata.setAlignment(Pos.CENTER);
        board1.setAlignment(Pos.CENTER);
        board2.setAlignment(Pos.CENTER);

        //binding
        center.prefWidthProperty().bind(root.widthProperty());
        center.prefHeightProperty().bind(root.heightProperty());
        board1.prefWidthProperty().bind(center.widthProperty());
        board2.prefWidthProperty().bind(center.heightProperty());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        root.setStyle("-fx-background-color: #D7DBDD;");
        primaryStage.show();
    }

    public void enableMusic(String path) {
        try {
            java.net.URL soundurl = getClass().getResource("/"+path);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundurl));

            if(clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(0);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startGame(){
        if (randStart == 1) {  //randstart variable gets a random value (0 or 1). If randstart equals 1 Computer plays first,otherwise human plays first
                com.attack(p1);
                Controller.changeLabel(p1, com, label1, label2, label3, label4, label5, label6);
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
