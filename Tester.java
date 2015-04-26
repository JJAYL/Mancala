import javax.swing.JFrame;
import java.awt.*;

public class Tester {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		MancalaBoard b = new MancalaBoard(3);
		MancalaView v = new MancalaView(0, 0, b);
		frame.add(v, BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setTitle("Mancala Game");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
