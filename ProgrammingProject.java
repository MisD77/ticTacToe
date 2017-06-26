//Lab Project
//Programming I, Section 1, Spring 2017

import java.util.Scanner;
public class ProgrammingProject
{
   public static void main(String[] args)
   {
      char[][] gameArray = {{'1', '2', '3'}, {'4','5', '6'}, {'7', '8', '9'}};
      print2DArray(gameArray);
      
      //lets play the game
      gameStart(gameArray);
   }//main ends
   
   public static void gameStart(char[][] gameArray)
   {
      Scanner kb = new Scanner(System.in);
      int[] userInputArray = new int[9];
      int playerInput, index = 0, inValid;
      boolean occupiedBefore, gameOver = false;
      
	  for (int round = 1; round <= 9; round++)
      {
         inValid = 0;
		 
         do {
               if (inValid != 0)
                  System.out.println("Invalid input.");
                  
               if (round % 2 != 0)//odd rounds belong to X
                  System.out.print("X - Which square? [1-9]: ");
                  
               else
                  System.out.print("O - Which square? [1-9]: ");
                  
               playerInput = kb.nextInt();
               
               if (playerInput >= 10 || playerInput <= 0)//checks for valid input and increments inValid
               inValid++;
                       
            }while (playerInput <= 0 || playerInput >= 10);
         
         occupiedBefore = placeHolder(userInputArray, playerInput); //checks if number is previously entered, returns boolean
             
         if (occupiedBefore)//if space is occupied restart loop and user turn
         {
            System.out.println("Space occupied"); 
            round--;
         }
         else//save user input, swap entries, check if game was won
         {
            userInputArray[index] = playerInput;
            index++;
            insertToGrid(gameArray, playerInput, round);//save user input
            if (round >= 5)
            gameOver = winner(gameArray);// check for winner, returns true if there is a winner
            
            if (gameOver)
            {
               if (round % 2 != 0)
                  System.out.println("X wins!");
               else
                  System.out.println("O wins!");
               round = 10;
            }
         }//else ends        
      }//for ends
      
      if(!gameOver)
            System.out.println("Cat Game!");
    }//gameOver
    
    public static boolean placeHolder(int[] userInputArray, int input)
    {
      boolean previous = false;
	  
      for (int i = 0; i < userInputArray.length; i++)
      {
       if (userInputArray[i] == input)
            previous = true;
      }
      return previous;
    }//checks if number has been previously entered or not
    
    public static char[][] insertToGrid(char[][] gameArray, int input, int round)
    { 
	  //converting int to char
      String temp = "" + input;
      char inputInChar = temp.charAt(0);
	  //end conversion
	  
      for (int row = 0; row < gameArray.length; row++)
      {
         for (int column = 0; column < gameArray[row].length; column++)
         {
            if (gameArray[row][column] == inputInChar)//if user input matches element in array
            {
               if (round % 2 != 0)
                  gameArray[row][column] = 'X';
               else
                  gameArray[row][column] = 'O';
            }
         }
      }
      print2DArray(gameArray);
      return gameArray;
    }//Inserts X or O in the square and prints everytime
    
    public static boolean winner(char[][] gameArray)
    {
      boolean winner = false;
	  
	  //checks all winning outcomes against the gameArray
      if (gameArray[0][0] == gameArray[0][1] && gameArray[0][1] == gameArray[0][2]) //first row
         winner = true;
      else if (gameArray[1][0] == gameArray[1][1] && gameArray[1][1] == gameArray[1][2])//second row
         winner = true;
      else if (gameArray[2][0] == gameArray[2][1] && gameArray[2][1] == gameArray[2][2])//third row
         winner = true;
      else if (gameArray[0][0] == gameArray[1][0] && gameArray[1][0] == gameArray[2][0])//First column
         winner = true;
      else if (gameArray[0][1] == gameArray[1][1] && gameArray[1][1] == gameArray[2][1])//Second column
         winner = true;
      else if (gameArray[0][2] == gameArray[1][2] && gameArray[1][2] == gameArray[2][2])//third column
         winner = true;
      else if (gameArray[0][0] == gameArray[1][1] && gameArray[1][1] == gameArray[2][2])//diagonal
         winner = true;
      else if (gameArray[0][2] == gameArray[1][1] && gameArray[1][1] == gameArray[2][0])//diagonal 
         winner = true;
         
      return winner;
    }//winner ends    
          
   public static void print2DArray(char[][] array)
   {
      for (int row = 0; row < array.length; row++)
      {
         System.out.println("  |   |");
         for (int column = 0; column < array[row].length; column++)
         {
            if (column == 2)
            System.out.println(array[row][column]+ " ");
            else
            System.out.print(array[row][column]+ " | ");
         }
         System.out.println("  |   |");
         if (row < 2)
         System.out.println("----------");
      }
      System.out.println();
   }//print2DArray ends
}