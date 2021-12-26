//@Landen Eric Messner
//@Indranil Basu
//Quiz 3
//Task: To create an interactive Tic-tac-toe game using Java

//extra credit work attempted
//(CPU Assist available)

import java.util.*;
import java.lang.*;

public class TicTacToe{

  public static void main(String[]args){  //main method
    Scanner input = new Scanner(System.in);
    //creating ArrayList to store the valid inputs
    ArrayList<Character>  validinput = new ArrayList<Character>();
    validinput.add('0');
    validinput.add('1');
    validinput.add('2');
    validinput.add('3');
    validinput.add('4');
    validinput.add('5');
    validinput.add('6');
    validinput.add('7');
    validinput.add('8');
    validinput.add('9');

    //creating a 2D Array to store the elements(tiles) of the board
    char [][] currentboard = {{'1','2','3'},{'4','5','6'},{'7','8','9'}};

    System.out.println("TIC-TAC-TOE");
    System.out.println("Select Game Mode:");
    //print statement for user input
    System.out.println("Enter (1) for 2 players mode \nEnter (2) for playing against the CPU \nEnter (0) to quit.");
    String gamemode = "";
    String move;
    char tile;
    int []badmoves = new int[2];  //tracks total invalid moves of player 1 in index[0] and player 2 in index[1]
    int [] consecbadmoves = new int[2]; //tracks consecutive invalid moves of plauer 1 in index[0] and player 2 in index[1]
    int [] cpuassists = new int[2]; //tracks number of cpuassists used by player 1 in index[0] and player 2 in index[1]
    int player = 1;
    int counter = 0;

    //while loop to return flow of control in case user enters incorrect input
    while(true){
    gamemode = input.next(); //fetches the user's choice from the menu
    int choice = ((int)gamemode.charAt(0)) - 48; 
    //validating user input
    while(choice > 2 || choice <0){
      System.out.println("Please enter a valid selection");
      gamemode = input.next(); //fetches the user's choice from the menu
      choice = ((int)gamemode.charAt(0)) - 48;
    }

    //switch statement for different cases of the different game modes
    switch (choice){

    case 0:
    //program is terminated
      System.out.println("Goodbye.");
      System.exit(0);
            break;

    case 1:
    //Two Player Mode Begins
    System.out.println("TWO PLAYER MODE");
    cpuassists[0] = 0;
    cpuassists[1] = 0;
    badmoves[0] = 0;
    badmoves[1] = 0;
    consecbadmoves[0] = 0;
    consecbadmoves[1] = 0;
    move = "";
    counter = 0; //keeps track of the current player
    displayBoard(currentboard); //displays the current board

    //while loop runs (the game is played) until one player wins
    while(!winCondition(currentboard)){  
      //for Player 1
      if (counter % 2 == 0){
      player = 1;
      System.out.println("Player 1 turn: ");
      //taking the tile user wants to play
      move = input.next();

      //error validation - If player 1 enters 0 at any stage of the game
      //player 1 forfeits the game to player 2
      if(move.charAt(0)=='0' && !move.equals("000")){
        System.out.println("Player 1 forfeits the game to Player 2");
        System.exit(0);
      }

      //extra credit code - user can enter "000" instead of the tile number 
      //the CPU makes the move for that player
      if (move.equals("000") && cpuassists[0]<2){
        cpuplay(currentboard, validinput, 1);
        cpuassists[0]+=1;
        counter++;
        displayBoard(currentboard);
        continue;
      }
      else if(move.equals("000") && cpuassists[0] == 2 ){ //player 1 can only use CPU assist twice
        System.out.println("Player 1 reaches max CPU assists, please try again");
        continue;
      }

     //checking if the value entered by the player 1 is valid
     while(!errorValid(move, validinput)){
     badmoves[0]+= 1;
     consecbadmoves[0]+= 1;
     if (badmoves[0] == 5 || consecbadmoves[0] == 3){
       System.out.println("Player 1 forfeit the game due to reaching maximum incorrect entries.");
       System.exit(0);
     }

     System.out.println("Incorrect entry, please try again: ");
     move = input.next();
     }
     tile = move.charAt(0);
     consecbadmoves[0] = 0;
      play(tile, currentboard, player, validinput);
      }

   //for Player 2
      if (counter % 2 !=0){
      System.out.println("Player 2 turn: ");
      move = input.next();
      player = 2;
      //error validation - If player 2 enters 0 at any stage of the game
      //player 2 forfeits the game to player 1
      if(move.charAt(0)=='0' && !move.equals("000")){
        System.out.println("Player 2 forfeits the game to Player 1");
        System.exit(0);
      }
      //CPU assist
      if (move.equals("000") && cpuassists[1] < 2){
        cpuplay(currentboard, validinput, 2);
        cpuassists[1]+=1;
        counter++;
        displayBoard(currentboard);
        continue;
      }
      //a player (here player 2) cannot use CPU assist more than twice
      else if(move.equals("000") && cpuassists[1] ==2){
        System.out.println("Player 2 reaches max CPU assists, please try again");
        continue;
      }
      //checking if the value entered by the user is valid
      while(!errorValid(move, validinput)){
      badmoves[1]+=1;
      consecbadmoves[1]+=1;
      if (badmoves[1] == 5 || consecbadmoves[1] == 3){
        System.out.println("Player 2 forfeit the game due to reaching maximum incorrect entries.");
        System.exit(0);
      }

      System.out.println("Incorrect entry, please try again");
      move = input.next();
      }
      tile = move.charAt(0);
      consecbadmoves[1] = 0;
      play(tile, currentboard, player, validinput);
      }
      //tie condition
      if (counter == 8 && winCondition(currentboard) == false){
        System.out.println("Game Over! It is a tie.");
        System.exit(0);
      }
      //displaying updated board on the console
      displayBoard(currentboard);
      counter ++;
    }
    //after win condition is achieved
    //depending on which player made the last move to win, that player wins
  if (counter % 2 == 1)
  System.out.println("Game Over! Player 1 Wins.");
  if (counter % 2 == 0)
  System.out.println("Game Over! Player 2 Wins.");
  System.exit(0);
            break;


    case 2:
    //One Player Mode Begins (User vs CPU)
    System.out.println("ONE PLAYER MODE");
    badmoves[0] = 0;
    badmoves[1] = 0;
    consecbadmoves[0] = 0;
    consecbadmoves[1] = 0;
    displayBoard(currentboard);   //displays current board
    counter = 0;
    while(!winCondition(currentboard)){ //plays until one player wins
      
      //for Player 1
      if (counter % 2 == 0){
      System.out.println("Player 1 turn: ");
      move = input.next();
      //if a player(here player 1) enters 0 at any stage of the game
      //the player forfeits the game to the CPU.
      if(move.charAt(0)=='0' && !move.equals("000")){
        System.out.println("Player 1 forfeits the game to CPU");
        System.exit(0);
      }
      //validates user input
      while(!errorValid(move, validinput)){
      badmoves[0]+= 1;
      consecbadmoves[0]+= 1;
      if (badmoves[0] == 5 || consecbadmoves[0] == 3){
        System.out.println("Player 1 forfeit the game due to reaching maximum incorrect entries.");
        System.exit(0);
      }

      System.out.println("Incorrect entry, please try again: ");
      move = input.next();
      }
      tile = move.charAt(0);
      consecbadmoves[0] = 0;
      player = 1;
      play(tile, currentboard, player, validinput);
    }


  //for CPU
    if (counter % 2 == 1){
      player = 3;
      //System.out.println("Trying to win the game...");
      cpuplay(currentboard, validinput,3);
    }
    //tie condition
    if (counter == 8 && winCondition(currentboard) == false){
      System.out.println("Game Over! It is a tie.");
      System.exit(0);
    }
    displayBoard(currentboard);
    counter++;
  }
  //after win condition is met displays who won depending upon which player went last
  if (counter % 2 == 1)
  System.out.println("Game Over! Player 1 Wins.");
  if (counter % 2 == 0)
  System.out.println("Game Over! CPU Wins.");
  System.exit(0);
      break;

    default:    //default case - if wrong game mode option is entered
    System.out.println("Wrong input! Try again \n Enter (1) for 2 players mode or enter (2) for playing against the CPU or enter (0) to quit.");
    continue;
  }
  }
}   //main() method ends



  public static void displayBoard(char [][] board){
    //method used to display the tic-tac-toe board with its current tiles
    System.out.println();
    for (int i = 0; i<3; i++){
    System.out.printf(" %c | %c | %c  \n",board[i][0],board[i][1],board[i][2]);
    if(i<2)
  System.out.printf("---+---+---\n");
   }
   System.out.println();
 }


  public static void play(char move, char [][]board, int player, ArrayList<Character> valid){
  //method is used to replace the user's chosen tile (if available) with their respective 'X' or 'O' symbol
    for (int i = 0;i<3;i++){    //nested for loop to run through the board(array) and check for available tiles
      for (int j = 0;j<3;j++){
        if (board[i][j] == move ){
            if (player == 1){
            valid.remove((Character)move);
            board[i][j] = 'X'; //replaces the tile with 'X' for Player 1
            return;   //returns control to calling method
            }
            if (player == 2){
            valid.remove((Character)move);
            board[i][j] = 'O'; //replaces the tile with 'O' for Player 2
            return;
            }
            if (player == 3){
            valid.remove((Character)move);
            board[i][j] = 'O'; //replaces the tile with 'O' for CPU
            return;
            }
          }
        else{
        continue;
      }
    }
  }
}



  public static boolean winCondition(char [][] board){
  //method to check whether a row of three same symbols gives a win condition
    boolean flag = false;
    //checking if there are three same symbols in a row
      for (int i = 0; i < 3;i++){
      if(board[i][0]== board[i][1] && board[i][0]==board[i][2]){
        flag = true;
        return flag;
      }
    }
    //checking if there are three same symbols in a column
      for(int i=0; i<3; i++){
        if(board[0][i]== board[1][i] && board[0][i]==board[2][i]){
          flag = true;
          return flag;
    }
  }
  //checking if there are three same symbols in one of the two diagonals
      if((board[0][0]== board[1][1] && board[0][0]==board[2][2])||
      (board[0][2]== board[1][1] && board[0][2]==board[2][0])){
        flag = true;
        return flag;
    }
    return flag;
}


public static void cpuplay(char[][] board, ArrayList<Character> valid, int player){
//updates the board(array) with the CPU's move
  char returnchar = 'a';
      if(checkBoard(board,'O') != '0'){
        returnchar = checkBoard(board,'O');
        play(returnchar,board,player, valid);
        return;
      }
      else if(checkBoard(board,'X') != '0') {
        returnchar = checkBoard(board,'X');
        play(returnchar,board,player,valid);
        return;
      }
    randomMove(board, valid, player);
    }


public static char checkBoard(char [][]board, char x){
//checks the board(array) if there is a chance for the current player to win
// aka predicts the best placement of the next symbol to win/block the win of the other player  
  char temp = '0';
  boolean flag = false;
  for(int i =0;i<3;i++){
    for(int j = 0;j<3;j++){
      if (board[i][j] == 'O' || board[i][j] == 'X')
      continue;
      temp = board[i][j];
      board[i][j] = x;
      if(winCondition(board) == true){
      board[i][j] = temp;
      return temp;
    }
    else{
      board[i][j] = temp;
      continue;
    }
    }
  }
    return '0';
}


public static void randomMove(char [][]board, ArrayList<Character> valid, int player){
//method used to make a random move by the CPU within the available tiles
  int rand = 0;
  boolean flag = true;
  char randchar = '0';
  while (flag){
  rand = (int)(Math.random()*9)+1;
  randchar = (char) (rand +48);
  for (int i = 0;i<3;i++){
    for (int j = 0;j<3;j++){
      //only adds an 'O' if the tile has a number on it (not X), otherwise skips
      if (board[i][j]==randchar){
      valid.remove((Character)randchar);
      if(player ==2|| player ==3)
      board[i][j]='O';
      if(player ==1)
      board[i][j]='X';
      flag = false;
    }
  }
}
}
}


public static boolean errorValid(String input, ArrayList<Character> valid){
//method used to validate whether the user's input is an available tile
  boolean flag = false;
  if(input.length()>1)
  return flag;
    if (valid.contains(input.charAt(0))){
      flag = true;
      return flag;
    }
    return flag;
  }
}
//end of code