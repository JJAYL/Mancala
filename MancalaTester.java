/*import java.util.*;
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
//}
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MancalaTester
{
	public static void main(String[] args)
	{
		final JFrame select = new JFrame();
		select.setLayout(null);
		JButton three = new JButton("Three stones?");
		JTextArea text = new JTextArea("Mancala Game\nDeveloped by Joachim, Kory, and Alan");
		text.setBounds(0, 0, 400, 50);
		three.setBounds(0, 50, 200, 50);
		three.addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent a)
			{
				select.dispatchEvent(new WindowEvent(select, WindowEvent.WINDOW_CLOSING));
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());
				MancalaBoard b = new MancalaBoard(3);
				MancalaView v = new MancalaView(0, 0, b);
				b.attach(v);
				frame.add(v, BorderLayout.CENTER);
				frame.setSize(800, 400);
				frame.setTitle("Mancala Game");
				//frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		JButton four = new JButton(" Or four?");
		four.setBounds(200, 50, 200, 50);
		four.addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent a)
			{
				select.dispatchEvent(new WindowEvent(select, WindowEvent.WINDOW_CLOSING));
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());
				MancalaBoard b = new MancalaBoard(4);
				AltMancalaView v = new AltMancalaView(0, 0, b);
				b.attach(v);
				frame.add(v, BorderLayout.CENTER);
				frame.setSize(800, 400);
				frame.setTitle("Mancala Game");
				//frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		select.add(three);
		select.add(four);
		select.add(text);
		select.setSize(400, 150);
		select.setVisible(true);
		
	}
}