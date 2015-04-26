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
public class MancalaView extends JComponent 
{
	private int x = 0;
	private int y = 0;
	private Rectangle2D.Double[] boardArray;
	private Point mousePoint;
	private ArrayList<Ellipse2D.Double> balls;
	private MancalaBoard board;
	/**
	 * @param offsetX how far to the right does it go
	 * @param offsetY how far down does it go
	 */
	public MancalaView(int offsetX, int offsetY, MancalaBoard b)
	{
		board = b;
		x = offsetX;
		y = offsetY;
		double boardWidth = 50;
		double boardHeight = 50;
		boardArray = new Rectangle2D.Double[14]; //Our array of represented pits
		balls = new ArrayList<Ellipse2D.Double>();
		boardArray[13] = new Rectangle2D.Double(x, y, boardWidth, 2*boardHeight);
		for(int i = 1; i < 7; i++)
		{//The pits are added in order based on the Pit array in the google doc 
			boardArray[13-i] = new Rectangle2D.Double(x+(50*i), y, boardWidth, boardHeight);
			boardArray[i-1] = new Rectangle2D.Double(x+(50*i), y+50, boardWidth, boardHeight);
		}
		boardArray[6] = new Rectangle2D.Double(x+350, y, boardWidth, 2*boardHeight);
		addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mousePoint = arg0.getPoint();
				for(Rectangle2D.Double d: boardArray)
				{
					if(d.contains(mousePoint)){board.move(0);}//todo
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
	}
}
