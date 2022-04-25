# BattleshipGame
Battleship game in Java

SETUP

JDK 8 is recommended as JavaFX is a fully integrated feature of Java version 8.

EXECUTION

Run Main.java and a pop-up window containing the 2 boards will appear. Press Load, type the number of your desired scenario and click Submit. Scenarios can be found in MediaLab folder and contain ship coordinates. In the txt files each row consists of 4 numbers. The first one represents the ship type, the following two represent the first cell's coordinates and the last one represents the direction, 1 for horizontal and 2 for vertical. To be able to play the game vs a computer you have to type '1' in the scenario id. Scenarios 2-5 are for test reasons, since they contain invalid data, which trigger unwanted exceptions (ships out of board's borders, ships covering each other etc). 
