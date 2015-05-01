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
	private int count;
	private Rectangle2D.Double[] boardArray;
	private Point mousePoint;
	private ArrayList<Ellipse2D.Double> balls;
	private MancalaBoard board;
	private JTextArea text;
    private String result;
    private Font f;
	/**
	 * @param offsetX how far to the right does it go
	 * @param offsetY how far down does it go
	 */
	public MancalaView(int offsetX, int offsetY, MancalaBoard b)
	{
		board = b;
		x = offsetX;
		y = offsetY;
		count = 0;
		result = "";
		double boardWidth = 100;
		double boardHeight = 100;
		setLayout(null);
		boardArray = new Rectangle2D.Double[14]; //Our array of represented pits
		balls = new ArrayList<Ellipse2D.Double>();
		for(int i = 1; i < 7; i++)
		{//The pits are added in order based on the Pit array in the google doc 
			boardArray[13-i] = new Rectangle2D.Double(x+(boardWidth*i), y, boardWidth, boardHeight);
			boardArray[i-1] = new Rectangle2D.Double(x+(boardWidth*i), y+boardHeight, boardWidth, boardHeight);
		}
		boardArray[6] = new Rectangle2D.Double(x+700, y, boardWidth, 2*boardHeight);
		boardArray[13] = new Rectangle2D.Double(x, y, boardWidth, 2*boardHeight);
		text = new JTextArea();
		text.setText(result);
		f = new Font("SansSerif", Font.BOLD, 14);
		JScrollPane p = new JScrollPane(text);
		p.setBounds(100, 200, 700, 100);
		add(p);
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
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				mousePoint = arg0.getPoint();
				for(int i = 1; i < 7; i++)
				{
					Rectangle2D.Double d = boardArray[i-1];
					if(d.contains(mousePoint)){board.move(i-1);}//todo
					Rectangle2D.Double d2 = boardArray[13-i];
					if(d2.contains(mousePoint)){board.move(13-i);}//todo
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
			g2.setColor(Color.RED);
			g2.fill(boardArray[i]);
			g2.setColor(Color.YELLOW);
			g2.fill(boardArray[13-i]);
			g2.setColor(Color.BLACK);
			g2.draw(boardArray[i]);
			g2.draw(boardArray[13-i]);
		}
		Pit[] pits = board.getBoard();
		for(int i = 0; i < 14; i++)
		{
			int xCorner = (int) boardArray[i].getX() + 15;
			int yCorner = (int) boardArray[i].getY() + 15;
			int stones = pits[i].getStones();
			for(int j = 0; j < 3; j++)
			{
				for(int k = 0; k < 5; k++)
				{
					if(stones > 0)
					{
						balls.add(new Ellipse2D.Double(xCorner, yCorner, 15, 15));
						stones--;
					}
					xCorner += 15;
				}
				xCorner = (int) boardArray[i].getX() + 15;
				yCorner += 15;
			}
		}
		for(Ellipse2D.Double d: balls)
		{
			g2.setColor(Color.MAGENTA);
			g2.fill(d);
		}
		g2.setColor(Color.BLACK);
		g2.setFont(f);
		g2.drawString("Player A is red, Player B is yellow", 0, 320);
		
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		result = board.print();
		text.setText(result);
		repaint();
		balls.clear();
	}
}
