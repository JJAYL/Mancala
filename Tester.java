import javax.swing.JFrame;


public class Tester {
	public static void main(String[] args)
	{
		MancalaBoard b = new MancalaBoard(3);
		MancalaView frame = new MancalaView(b);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
