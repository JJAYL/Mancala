import java.util.*;
public class MancalaTester
{
private static MancalaBoard tester;
private static Scanner scanner = new Scanner(System.in);
private static String input;
public static void main(String[] args)
{
	System.out.println("Enter number of initial stones (3 or 4) : ");
	input = scanner.nextLine();
	tester = new MancalaBoard(Integer.parseInt(input));
	tester.printBoard();
	System.out.println("\nMake your move  A");
	while(scanner.hasNext())
	{
		tester.printBoard();
		char player = 'B';
		if(!tester.getPlayer()){player = 'A';}
		else player = 'B';
		System.out.println("\nMake your move " + player);
		char c = scanner.next().charAt(0);//Get a letter, a - f. Will add guard condition
		if(c >= 'a' && c <= 'f')
		{
			int x = (int)c;
			x -= 97;
			if(tester.getPlayer())//Should enable gameplay from console. I'll update it after work.
			{
				tester.move(x);
			}
			else tester.move(12-x);
		}
		tester.printBoard();
}
	tester.move(3);
}
/*
Prints the board like this
B6 B5 B4 B3 B2 B1
A a b c d e f B
A1 A2 A3 A4 A5 A6
*/
/*
*/
}
