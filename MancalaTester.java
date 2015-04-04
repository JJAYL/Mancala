import java.util.*;
public class MancalaTester
{
   private static MancalaBoard tester;
   private static Scanner scanner =  new Scanner(System.in);
   private static String input;

   public static void main(String[] args)
   {
      System.out.println("Enter number of initial stones (3 or 4) : ");
      input = scanner.nextLine();
      tester = new MancalaBoard(Integer.parseInt(input));
      Pit[] test = tester.getBoard();
      printBoard(test);
      System.out.println("\nMake your move: ");
      while(scanner.hasNext())
      {/*
          Prints a board like this
             B6 B5 B4 B3 B2 B1 
          A  a  b  c  d  e  f  B
             A1 A2 A3 A4 A5 A6
          */
    	  //printBoard(test); 
    	  char player = 'A';
    	  if(!tester.player()){player = 'B';}
          System.out.println("\nMake your move:" + player);
          char c = scanner.next().charAt(0);//Get a letter, a - f. Will add guard condition
          if(c >= 'a' && c <= 'f'){
          int x = (int)c;
          x -= 97;
          if(tester.player())//Should enable gameplay from console. I'll update it after work.
          {
          tester.move(x);
          }
          else tester.move(12-x);}
          printBoard(test);
      }
      
      tester.move(3);
     /* for (int i = 0; i < test.length; i++)
      {
         if (i != 7)
         {
            System.out.print(Integer.toString(test[i].getStones()));
         }
         else
            System.out.print("\n" + Integer.toString(test[i].getStones()));
      }*/
   }
   /*
   Prints the board like this
       B6 B5 B4 B3 B2 B1 
    A  a  b  c  d  e  f  B
       A1 A2 A3 A4 A5 A6
   */
   public static void printBoard(Pit[] test)
   {
       System.out.print("   ");
       for(int i = 12; i > 6; i--)
       {
           System.out.print(Integer.toString(test[i].getStones())+ "  ");
       }
       System.out.println();
       System.out.println(test[13].getStones() + "  a  b  c  d  e  f  " + test[6].getStones());
       System.out.print("   ");
       for(int i = 0; i < 6; i++)
       {
           System.out.print(test[i].getStones() + "  ");
       }
   }
}
