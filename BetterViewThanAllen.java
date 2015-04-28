import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BetterViewThanAllen
{
	private MancalaBoard board = new MancalaBoard(3);
    protected JFrame frame;
    protected JPanel mancalaA = new JPanel();
    protected JPanel mancalaB = new JPanel();
    protected JPanel center = new JPanel();
    protected JPanel[] wells = new JPanel[13];

	public BetterViewThanAllen()
	{
		frame = new JFrame("I dont know anything about JPanels");                                    
        frame.getContentPane().setLayout(new BorderLayout());                                          
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        frame.setSize(750,500);        
        frame.setVisible(true);
        frame.add(mancalaA, BorderLayout.EAST);
        frame.add(mancalaB, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);
        mancalaA.add(new JButton("A"));
        //mancalaA.add(new Rock(10,20,30));
        
        mancalaB.add(new JButton("B"));
        //mancalaB.add(new Rock(10,20,30));
        center.setLayout(new GridLayout(2,6));
        for(int i=0;i<13;i++)
		{
        	wells[i] = new JPanel();
    		wells[i].setBounds(new Rectangle(5,5));
    		wells[i].setLayout(new FlowLayout());
        	//TODO add rectangles around these mofos
        	for(int j=0;j<board.getBoard()[i].getStones();j++)
        	{
        		wells[i].add(new Rock(10,20,30));  
        	}
        		center.add(wells[i]);
		}
        //frame.validate();
	}
}
