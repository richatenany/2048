

import java.util.Random;
import java.util.Scanner;

public class TwentyFortyEight {
    //This instance variable represents the board. Use this to make changes.
    private int[][] board;
    //This variable keeps track of the current score.
    private int score;
    public int boardWidth;
    public int currentScore;
    public int bestScore;

    //Constructor
    public TwentyFortyEight(int boardWidth){
        //make sure score is 0
        score = 0;
        this.boardWidth = boardWidth;
        board = new int[boardWidth][boardWidth];
        placeRandom();


        // TODO
    }

    //This function resets the board to its initial state
    public void reset() {
        //board = this.board;
        score = 0;
        //make sure new board is made and not just changing blanks to 0s
//        for(int i = 0; i < board.length; i++){
//            for(int j = 0; j < board.length; j++){
//                if(board[i][j] != 0){
//                    board[i][j] = 0;
//                }
//            }
//        }
        board = new int[boardWidth][boardWidth];
        placeRandom();

        // TODO
    }

    //This function returns the total number of blank spaces on the board
    public int numBlanks() {
        int count = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j <board.length; j++){
                if(board[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
        // TODO
    }

    //This function places a 2 at a random blank space on the board.
    //It does nothing if there are no blank spaces.
    public void placeRandom(){
        int row = (int)(Math.random() * boardWidth);
        int col = (int)(Math.random() * boardWidth);
        //Random random = new Random();
        //int [][] newArray = new int[row][col];
        boolean findSpot = true;

        //while(true){
            if(board[row][col] == 0){
                board[row][col] = 2;
                findSpot = false;
                return;
            }
            else{
                row = (int)(Math.random() *boardWidth);
                col = (int)(Math.random()*boardWidth);
            }

       // }
        // TODO
    }

    //This function attempts to move a cell at coordinates fromRow,fromCol to
    //the cell toRow,toCol. Refer to the handout for movement rules.


    public boolean moveTo(int fromRow, int fromCol, int toRow, int toCol) {

        int findCdiff = Math.abs(fromCol-toCol);
        int findRdiff = Math.abs(fromRow - toRow);


        //check to make sure its in bounds
        if(toRow >= boardWidth || toCol >= boardWidth || toRow < 0 || toCol < 0){
            return false;
        }
        //check to see if they are adjacent
        if ((findCdiff >1 || findRdiff >1) || (findCdiff ==1 && findRdiff ==1) || (findCdiff == 0 && findRdiff == 0)){
            return false;
        }
        int oldLocation = board[fromRow][fromCol];
        int newLocation = board[toRow][toCol];

        //check to see if tile is 0
        if (oldLocation == 0){
            return false;
        }
        //if current tile is not equal to new tile, return false
        if (oldLocation != newLocation){
            if (oldLocation !=0 && newLocation !=0){
                return false;
            }
        }
        //if "to" tile = 0 then make "from" tile equal "to" tile
        if (newLocation == 0){
            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = 0;
        }

        //if number is the same then multiply
//        for(int i =0; i < board.length; i++){
//            for(int j = 0; j < board.length; j++){
                //if(board[i][j] != 0){
                    if(oldLocation == newLocation){
                        board[toRow][toCol] = 2 * board[toRow][toCol];
                        board[fromRow][fromCol] = 0;
                    }
                //}
//            }
//        }
        if (board[fromRow][fromCol] != board[toRow][toCol] && board[fromRow][fromCol] > 0 && board[toRow][toCol] > 0) {
            return false;
        }
            return true;
    }


    //The following four functions move the board in a single direction.
    public boolean moveUp(){
        int counter = 0;
        //nested forloop to run thorough board
        for (int i = 0; i < board.length; i++) {
            for (int j =board.length-1; j >= 1; j--) {
               //if (board[j][i] != 0 && i != board.length) {
                    boolean t = moveTo(j,i,j-1,i);
                //to move up, row decrements
                    if (t == true) {
                        counter++;
                        //break;
                    }
           //     }
            }
            // TODO
        }

        for(int i = 0; i < board.length; i++){
            for(int j =0; j < board.length; j++){
                if(board[j][i] > score){
                    score = board[j][i];
                }
            }
        }
        if(counter == 0){
            return false;
        }

        return true;
    }

    public boolean moveDown() {
        int counter = 0;
        //nested forloop to run through board
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length-1; j >= 1; j--) {
              //  if (board[j][i] != 0 && i != board.length) {
                    boolean t = moveTo(j,i,j+1,i);
                //to move down, row increments
                    if (t == true) {
                        counter++;
                        //break;
                    }
                }
          //  }
            // TODO
        }

        for(int i = 0; i < board.length; i++){
            for(int j =0; j < board.length; j++){
                if(board[j][i] > score){
                    score = board[j][i];
                }
            }
        }
        if(counter == 0){
            return false;
        }
        return true;
    }

    public boolean moveRight() {
        int counter = 0;
        //nested for loop to run through board
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length-1; j >= 0 ; j--) {
                if (board[j][i] != 0 && i != board.length) {
                    boolean t = moveTo(j,i,j,i+1);
                    //to move right, column increments
                    if (t == true) {
                        counter++;
                        //break;
                    }
                }
            }
            // TODO
        }

        for(int i = 0; i < board.length; i++){
            for(int j =0; j < board.length; j++){
                if(board[j][i] > score){
                    score = board[j][i];
                }
            }
        }
        if(counter == 0){
            return false;
        }

        return true;
    }

    public boolean moveLeft() {
        int counter = 0;
        //nested forloop to run through board
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != 0 && j != board.length) {
                    boolean t = moveTo(j,i,j,i-1);
                    //to move left, column moves left and decrements
                    if (t == true) {
                        counter++;
                        //break;
                    }
                }
            }
            // TODO
        }

        for(int i = 0; i < board.length; i++){
            for(int j =0; j < board.length; j++){
                if(board[j][i] > score){
                    score = board[j][i];
                }
            }
        }
        if(counter == 0){
            return false;
        }

        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }


    /**
     * The main will allow you to play the game.
     *
     * @param args
     */
    public static void main(String args[]) {
        TwentyFortyEight tfe = new TwentyFortyEight(4);


        Scanner s = new Scanner(System.in);
        int bestScore = 0;
        boolean resetFlag = false;

        while(true) {
            System.out.println("Current score: " + tfe.getScore() + " | Best score: " + bestScore);
            tfe.showBoard();

            System.out.println("Enter up, down, left or right to move in that direction.");
            System.out.println("Enter reset to reset the game or quit to exit.");
            String line = s.nextLine();

            switch(line){
                case "up":
                    while(tfe.moveUp()){
                        // do nothing
                    }
                    break;
                case "down":
                    while(tfe.moveDown()){
                        // do nothing
                    }
                    break;
                case "left":
                    while(tfe.moveLeft()){
                        // do nothing
                    }
                    break;
                case "right":
                    while(tfe.moveRight()){
                        // do nothing
                    }
                    break;
                case "reset":
                    tfe.reset();
                    resetFlag = true;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Invalid move, Please try again!!!!\n");
                    continue;

            }

            bestScore = Math.max(bestScore, tfe.getScore());
            if(!resetFlag) {
                tfe.placeRandom();
                resetFlag = false;
            }
        }
    }



    public void showBoard() {
        for(int x = 0; x < boardWidth * 6 + 1; x++){
            System.out.print("-");
        }
        System.out.println();

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                String square = (board[x][y] == 0)? "":board[x][y] + "";
                System.out.printf("|%5s", square);
            }
            System.out.println("|");

            for(int y = 0; y < boardWidth * 6 + 1; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }


}
