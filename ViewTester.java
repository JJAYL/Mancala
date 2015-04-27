import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ViewTester {
	public static void main(String[] args)
	{
		JFrame select = new JFrame();
		select.setLayout(new BorderLayout());
		JButton three = new JButton("3");
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
				frame.setSize(400, 400);
				frame.setTitle("Mancala Game");
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		JButton four = new JButton("4");
		four.addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent a)
			{
				select.dispatchEvent(new WindowEvent(select, WindowEvent.WINDOW_CLOSING));
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());
				MancalaBoard b = new MancalaBoard(4);
				MancalaView v = new MancalaView(0, 0, b);
				b.attach(v);
				frame.add(v, BorderLayout.CENTER);
				frame.setSize(400, 400);
				frame.setTitle("Mancala Game");
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		select.add(three, BorderLayout.WEST);
		select.add(four, BorderLayout.EAST);
		select.pack();
		select.setVisible(true);
	}
}
