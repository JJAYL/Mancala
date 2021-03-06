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
public class AltMancalaView extends JPanel implements ChangeListener
{
	private int x;
	private int y;
	private Ellipse2D.Double[] boardArray;
	private Point mousePoint;
	private ArrayList<Rectangle2D.Double> balls;
	private MancalaBoard board;
	private JTextArea text, AMancala, BMancala;
    private String result;
    private Font f;
	/**
	 * @param offsetX how far to the right does it go
	 * @param offsetY how far down does it go
	 */
	public AltMancalaView(int offsetX, int offsetY, MancalaBoard b)
	{
		board = b;
		x = offsetX;
		y = offsetY;
		result = "";
		f = new Font("Helvetica", Font.PLAIN, 14);
		double boardWidth = 100;
		double boardHeight = 100;
		setLayout(null);
		boardArray = new Ellipse2D.Double[14]; //Our array of represented pits
		balls = new ArrayList<Rectangle2D.Double>();
		for(int i = 1; i < 7; i++)
		{//The pits are added in order based on the Pit array in the google doc 
			boardArray[13-i] = new Ellipse2D.Double(x+(boardWidth*i), y, boardWidth, boardHeight);
			boardArray[i-1] = new Ellipse2D.Double(x+(boardWidth*i), y+boardHeight, boardWidth, boardHeight);
		}
		boardArray[6] = new Ellipse2D.Double(x+700, y, boardWidth, 2*boardHeight);
		boardArray[13] = new Ellipse2D.Double(x, y, boardWidth, 2*boardHeight);
		text = new JTextArea();
		text.setText(result);
		AMancala = new JTextArea();
		BMancala = new JTextArea();
		AMancala.setEditable(false);
		BMancala.setEditable(false);
		AMancala.setBounds(710, 70, 80, 15);
		BMancala.setBounds(10, 70, 80, 15);
		JScrollPane p = new JScrollPane(text);
		p.setBounds(100, 200, 700, 100);
		add(p);
		add(AMancala);
		add(BMancala);
		JButton undo = new JButton("Undo");
		undo.setBounds(0, 200, 100, 100);
		undo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				board.undoBoard();
				repaint();
			}
		});
		add(undo);
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				mousePoint = arg0.getPoint();
				for(int i = 1; i < 7; i++)
				{
					Ellipse2D.Double d = boardArray[i-1];
					if(d.contains(mousePoint)){board.move(i-1);}
					Ellipse2D.Double d2 = boardArray[13-i];
					if(d2.contains(mousePoint)){board.move(13-i);}
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
		for(int i = 0; i < boardArray.length/2; i++)
		{//Draw all of the pits.
			g2.setColor(Color.BLUE);
			g2.fill(boardArray[i]);
			g2.setColor(Color.GREEN);
			g2.fill(boardArray[13-i]);
			g2.setColor(Color.BLACK);
			g2.draw(boardArray[i]);
			g2.draw(boardArray[13-i]);
		}
		Pit[] pits = board.getBoard();
		AMancala.setText(pits[6].getStones()+"");
		BMancala.setText(pits[13].getStones()+"");
		for(int i = 0; i < 14; i++)
		{
			int xCorner = (int) boardArray[i].getX() + 15;
			int yCorner = (int) boardArray[i].getY() + 30;
			int stones = pits[i].getStones();
			for(int j = 0; j < 3; j++)
			{
				for(int k = 0; k < 5; k++)
				{
					if(stones > 0)
					{
						balls.add(new Rectangle2D.Double(xCorner, yCorner, 10, 10));
						stones--;
					}
					xCorner += 12;
				}
				xCorner = (int) boardArray[i].getX() + 15;
				yCorner += 12;
			}
		}
		for(Rectangle2D.Double d: balls)
		{
			g2.setColor(Color.CYAN);
			g2.fill(d);
		}
		g2.setColor(Color.BLACK);
		g2.setFont(f);
		g2.drawString("Player A is blue, Player B is green", 0, 320);
	}
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		result = board.print();
		AMancala.repaint();
		BMancala.repaint();
		text.setText(result);
		repaint();
		balls.clear();
	}
}
