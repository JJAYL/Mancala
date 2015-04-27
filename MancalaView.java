import javax.swing.*;
import javax.swing.event.*;

import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author Alan Huynh
 * The panel that displays the board.
 */
public class MancalaView extends JPanel implements ChangeListener
{
	private int x;
	private int y;
	private Rectangle2D.Double[] boardArray;
	private Point mousePoint;
	private ArrayList<Ellipse2D.Double> balls;
	private MancalaBoard board;
	private JTextField text;
    private String result;
	/**
	 * @param offsetX how far to the right does it go
	 * @param offsetY how far down does it go
	 */
	public MancalaView(int offsetX, int offsetY, MancalaBoard b)
	{
		board = b;
		x = offsetX;
		y = offsetY;
		result = "It is Player A's turn.";
		double boardWidth = 50;
		double boardHeight = 50;
		setLayout(new BorderLayout());
		boardArray = new Rectangle2D.Double[14]; //Our array of represented pits
		balls = new ArrayList<Ellipse2D.Double>();
		for(int i = 1; i < 7; i++)
		{//The pits are added in order based on the Pit array in the google doc 
			boardArray[i+6] = new Rectangle2D.Double(x+(50*i), y, boardWidth, boardHeight);
			boardArray[i-1] = new Rectangle2D.Double(x+(50*i), y+50, boardWidth, boardHeight);
		}
		boardArray[6] = new Rectangle2D.Double(x+350, y, boardWidth, 2*boardHeight);
		boardArray[13] = new Rectangle2D.Double(x, y, boardWidth, 2*boardHeight);
		text = new JTextField();
		text.setText(result);
		add(text, BorderLayout.SOUTH);
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//board.undo();
			}
		});
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mousePoint = arg0.getPoint();
				for(int i = 0; i < 14; i++)
				{
					Rectangle2D.Double d = boardArray[i];
					if(d.contains(mousePoint)){result = board.move(i);}//todo
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < boardArray.length; i++)
		{//Draw all of the pits. Todo: Have them display the stones
			g2.draw(boardArray[i]);
		}
		Pit[] pits = board.getBoard();
		for(int i = 0; i < 14; i++)
		{
			int xCorner = (int) boardArray[i].getX() + 10;
			int yCorner = (int) boardArray[i].getY() + 10;
			int stones = pits[i].getStones();
			for(int j = 0; j < 3; j++)
			{
				for(int k = 0; k < 2; k++)
				{
					if(stones > 0)
					{
						balls.add(new Ellipse2D.Double(xCorner, yCorner, 10, 10));
						stones--;
					}
					xCorner += 10;
				}
				xCorner = (int) boardArray[i].getX() + 10;
				yCorner += 10;
			}
		}
		for(Ellipse2D.Double d: balls)
		{
			g2.setColor(Color.RED);
			g2.fill(d);
		}
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		text.setText(result);
		balls.clear();
	}
}
