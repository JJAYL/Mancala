import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Alan Huynh
 * MancalaView: Draw the Mancala Board and all related buttons
 * Right now, it draws the basic board with rectangles.
 * To do:
 * Implement buttons
 * Add other functionality - the undo button, a way to draw how many stones are in each pit
 * Add mouse listeners so we can make moves
 */

public class MancalaView extends JFrame implements ChangeListener{
	private MancalaBoard board;
	/**
	 * @author Alan Huynh
	 * Initialize a MancalaView with a given MancalaBoard.
	 * @param b the MancalaBoard we are using to initialize the view
	 */
	public MancalaView(MancalaBoard b)
	{
		board = b;
		setSize(400, 400);
		setLayout(new BorderLayout());
		setVisible(true);
		GameBoard gameBoard = new GameBoard(0, 0);
		add(gameBoard);
		setTitle("Mancala Game");
		gameBoard.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
/**
 * @author Alan Huynh
 * The panel that displays the board.
 */
class GameBoard extends JPanel 
{
	private int x = 0;
	private int y = 0;
	/**
	 * @param offsetX how far to the right does it go
	 * @param offsetY how far down does it go
	 */
	public GameBoard(int offsetX, int offsetY)
	{
		x = offsetX;
		y = offsetY;
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
		double boardWidth = 50;
		double boardHeight = 50;
		Rectangle2D.Double[] boardArray = new Rectangle2D.Double[14]; //Our array of represented pits
		boardArray[13] = new Rectangle2D.Double(x, y, boardWidth, 2*boardHeight);
		for(int i = 1; i < 7; i++)
		{//The pits are added in order based on the Pit array in the google doc 
			boardArray[13-i] = new Rectangle2D.Double(x+(50*i), y, boardWidth, boardHeight);
			boardArray[i-1] = new Rectangle2D.Double(x+(50*i), y+50, boardWidth, boardHeight);
		}
		boardArray[6] = new Rectangle2D.Double(x+350, y, boardWidth, 2*boardHeight);
		for(int i = 0; i < boardArray.length; i++)
		{//Draw all of the pits. Todo: Have them display the stones
			g2.draw(boardArray[i]);
		}
	}
}
