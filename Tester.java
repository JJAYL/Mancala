import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Tester {
	private static int init;
	public static void main(String[] args)
	{
		JFrame select = new JFrame();
		select.setLayout(new BorderLayout());
		JButton three = new JButton("3");
		three.addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent a)
			{
				init = 3;
				select.dispatchEvent(new WindowEvent(select, WindowEvent.WINDOW_CLOSING));
			}
		});
		JButton four = new JButton("4");
		four.addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent a)
			{
				init = 4;
				select.dispatchEvent(new WindowEvent(select, WindowEvent.WINDOW_CLOSING));
			}
		});
		select.add(three, BorderLayout.WEST);
		select.add(four, BorderLayout.EAST);
		select.pack();
		select.setVisible(true);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		MancalaBoard b = new MancalaBoard(init);
		MancalaView v = new MancalaView(0, 0, b);
		frame.add(v, BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setTitle("Mancala Game");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
