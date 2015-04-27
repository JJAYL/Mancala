import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;


public class Rock extends JComponent
{
	private static final long serialVersionUID = 1L;
    public Rock() { }
    public Rock(int x, int y, int diameter)
    {
        super();
        this.setLocation(x, y);
        this.setSize(diameter, diameter);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(0, 0, 10, 10);
    }
    public Dimension getPreferredSize()
    {
		return getSize();
    	
    }
}
