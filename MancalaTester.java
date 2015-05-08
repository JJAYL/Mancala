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