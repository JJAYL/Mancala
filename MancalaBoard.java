import java.util.*;

import javax.swing.event.*;
/**
 * 
 * @author Lerman
 * @author Le
 * The playing board
 */
public class MancalaBoard
{
	public static final int PLAYER_A_MANCALA = 6;
	public static final int PLAYER_B_MANCALA = 13;
	private boolean player = true; // true == a, false == b
	private Pit[] mancalaBoard;
	private int undoLimit = 0;
	private ArrayList<ChangeListener> listeners;
	private ArrayList<String> chatHistory;
	Scanner in = new Scanner(System.in);

	/**
	 * constructs the mancala board
	 * 
	 * @param initialStones
	 *            stones that the board starts out with
	 */
	public MancalaBoard(int initialStones)
	{
		chatHistory = new ArrayList<String>();
		chatHistory.add("It is Player A's turn.");
		mancalaBoard = new Pit[14];// set pit 6 and 13 to mancalas
		for (int i = 0; i < PLAYER_A_MANCALA; i++)
		{
			mancalaBoard[i] = new Pit(initialStones, true);
		}
		for (int i = PLAYER_A_MANCALA; i < PLAYER_B_MANCALA; i++)
		{
			mancalaBoard[i] = new Pit(initialStones, false);
		}
		mancalaBoard[PLAYER_A_MANCALA] = new MancalaPit(0, true);
		mancalaBoard[PLAYER_B_MANCALA] = new MancalaPit(0, false);
		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * returns the mancala board
	 * 
	 * @return mancala board
	 */
	public Pit[] getBoard()
	{
		return mancalaBoard;
	}

	/**
	 * returns the current player
	 * @return player playing
	 */
	public boolean getPlayer()
	{
		return player;
	}

	/**
	 * takes character inputs and converts them to mancala coordinates for console version of the game
	 * @param input character input to be converted
	 * @return mancalaBoard coordinate of a corresponding pit
	 */
	public int inputs(char input)
	{
		char player = 'B';
		if (getPlayer())
		{
			player = 'A';
		} else
			player = 'B';
		if (input <= 'a' || input >= 'f')
		{
			System.out.print("invalid input");
		}
		int x = (int) input;
		x -= 97;
		if (getPlayer())
		{
			return x;
		} else
			return 12 - x;
	}

	/**
	 * prints out the mancala board in the console
	 */
	public void printBoard()
	{
		System.out.print("B:");
		for (int i = 12; i > 6; i--)
		{
			System.out.print(Integer.toString(mancalaBoard[i].getStones())
					+ " ");
		}
		System.out.println();
		System.out.println(mancalaBoard[PLAYER_B_MANCALA].getStones()
				+ " a b c d e f " + mancalaBoard[PLAYER_A_MANCALA].getStones());
		System.out.print("A:");
		for (int i = 0; i < 6; i++)
		{
			System.out.print(mancalaBoard[i].getStones() + " ");
		}
	}

	/**
	 * picks a pit and plays a move
	 * 
	 * @param mancalaPitIndex pit address in the board
	 */
	public void move(int mancalaPitIndex)
	{
		int stonesInHand = mancalaBoard[mancalaPitIndex].getStones();
		int mancalaPitIndex2 = 0;
		if (stonesInHand == 0 || mancalaPitIndex == PLAYER_A_MANCALA
				|| mancalaPitIndex == PLAYER_B_MANCALA||mancalaBoard[mancalaPitIndex].whichPlayer()!=player)
		{
			chatHistory.add("Invalid move, try again");
			for (ChangeListener c : listeners)
			{
				c.stateChanged(new ChangeEvent(this));
			}
			return; //wont change the boolean value at the end so the player will go again and wont do anything in the for loop
		}
		// makes sure to only undo the very last move
		for (int i = 0; i < mancalaBoard.length; i++)
		{
			mancalaBoard[i].setPlayedOn(false);
		}
		mancalaBoard[mancalaPitIndex].setStones(0); // removes the stone from the pit you start with
		for (int i = stonesInHand; i > 0; i--)
		{

			if ((mancalaPitIndex + 1) == 14) // makes the manacala board wrap around itself
			{
				mancalaBoard[0].setPlayedOn(true);			
				mancalaPitIndex = -1; // so in the next iteration the mancala+= 1 == 0
			}
			else
			{
				mancalaBoard[mancalaPitIndex].setPlayedOn(true);
			}
			mancalaBoard[mancalaPitIndex += 1].addStone();
			
			mancalaBoard[mancalaPitIndex].setPlayedOn(true);			
			if ((PLAYER_A_MANCALA == mancalaPitIndex)
					&& (player != mancalaBoard[PLAYER_A_MANCALA].whichPlayer())) // skips the opponents mancala by remove the extra stone added to the enemy players mancala
			{ 
				mancalaBoard[mancalaPitIndex].setStones(mancalaBoard[mancalaPitIndex].getStones() - 1);
				i++;
			}
			if ((PLAYER_B_MANCALA == mancalaPitIndex)
					&& (player != mancalaBoard[PLAYER_B_MANCALA].whichPlayer())) // skips the opponents mancala by remove the extra stone added to the enemy players mancala
			{ 
				mancalaBoard[mancalaPitIndex].setStones(mancalaBoard[mancalaPitIndex].getStones() - 1);
				i++;
			}

			if (i == 1) // if it is the last stone
			{
				if ((PLAYER_A_MANCALA == mancalaPitIndex) && (player == true) && (!gameOver())) // go again if last stone lands in your mancala
				{
					chatHistory.add("Play again ");
					for (ChangeListener c : listeners)
					{
						c.stateChanged(new ChangeEvent(this));
					}
					return;

				}
				if ((PLAYER_B_MANCALA == mancalaPitIndex) && (player == false) && (!gameOver())) // go again if you last stone lands in your mancala
				{
					chatHistory.add("Play again ");
					for (ChangeListener c : listeners)
					{
						c.stateChanged(new ChangeEvent(this));
					}

					
					return;
				}
			}
		}
		if ((mancalaBoard[mancalaPitIndex].getStones() == 1)) // you land on an empty spot on your side and it is not a mancala
		{
			if ((player) && (12 - mancalaPitIndex  != PLAYER_A_MANCALA)
					&& (mancalaBoard[mancalaPitIndex].whichPlayer() == player))
			{
				mancalaBoard[PLAYER_A_MANCALA]
						.addStones(mancalaBoard[12 - mancalaPitIndex].getStones());
				mancalaBoard[12 - mancalaPitIndex].clearPit();
				mancalaBoard[12 - mancalaPitIndex].setPlayedOn(true);
			}
			if ((!player) && (12 - mancalaPitIndex != PLAYER_B_MANCALA)
					&& (mancalaBoard[mancalaPitIndex].whichPlayer() == player))
			{
				mancalaBoard[PLAYER_B_MANCALA].addStones(mancalaBoard[12 - mancalaPitIndex].getStones());
				mancalaBoard[PLAYER_B_MANCALA].setPlayedOn(true);	
				mancalaBoard[12 - mancalaPitIndex].clearPit();
				mancalaBoard[12 - mancalaPitIndex].setPlayedOn(true);
			}
		}
		// changes the players turn
		player = !player;
		gameOver();

		for (ChangeListener c : listeners)
		{
			c.stateChanged(new ChangeEvent(this));
		}
		if (gameOver())
		{
			return;
		}
		if (player == true)
		{
			chatHistory.add("Player A");
			for (ChangeListener c : listeners)
			{
				c.stateChanged(new ChangeEvent(this));
			}
			return;
		} else
			chatHistory.add("Player B");
		for (ChangeListener c : listeners)
		{
			c.stateChanged(new ChangeEvent(this));
		}
		return;
	}

	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}

	public String print()
	{
		String total = "";
		for (String s : chatHistory)
		{
			total += s;
			total += '\n';
		}
		printBoard();
		System.out.print("\n");
		return total;
	}

	/**
	 * declares the winner
	 */
	public void winnerIs()
	{
		String winner = "";
		if (mancalaBoard[PLAYER_A_MANCALA].getStones() > mancalaBoard[PLAYER_B_MANCALA]
				.getStones())
		{
			winner = "Player A wins";
			chatHistory.add(winner);
		} else if (mancalaBoard[PLAYER_A_MANCALA].getStones() < mancalaBoard[PLAYER_B_MANCALA]
				.getStones())
		{
			winner = "Player B wins";
			chatHistory.add(winner);
		} else
			winner = "Tie";
		chatHistory.add(winner);
		return;
	}
	
	/**
	 * puts all the mancala pits to their previous state
	 */
	public void undoBoard()
	{
		undoLimit++;
		if(undoLimit==3) undoLimit=0;
		if(undoLimit<2)
		{
			for (int i = 0; i < mancalaBoard.length; i++)
			{
				if (mancalaBoard[i].isPlayedOn() == true)
				{
					mancalaBoard[i].reset();
				}
			}
			player = !player;
			if (player)
			{
				chatHistory.add("Player A's turn");
			} else
				chatHistory.add("Player B's turn");
			for (ChangeListener c : listeners)
			{
				c.stateChanged(new ChangeEvent(this));
			}
		}
		else
		{
			chatHistory.add("Hit undo Limit");
			for (ChangeListener c : listeners)
			{
				c.stateChanged(new ChangeEvent(this));
			}
		}
		return;
	}

	/**
	 * checks if the game is over
	 * @return if the game has ended
	 */
	public boolean gameOver()
	{
		int sum = 0;
		for (int i = 0; i < PLAYER_A_MANCALA; i++)
		{
			sum += mancalaBoard[i].getStones();
		}
		if (sum == 0)
		{
			// adds all the stones from the pit of B to his mancala
			for (int j = 7; j < PLAYER_B_MANCALA; j++)
			{
				mancalaBoard[PLAYER_B_MANCALA].addStones(mancalaBoard[j].getStones());
				mancalaBoard[j].clearPit();
				for (ChangeListener c : listeners)
				{
					c.stateChanged(new ChangeEvent(this));
				}
				
			}
			winnerIs();
			return true;
		}
		sum = 0;
		for (int i = 7; i < PLAYER_B_MANCALA; i++)
		{
			sum += mancalaBoard[i].getStones();

		}
		if (sum == 0)
		{
			// adds all the stones from the pit of B to his mancala
			for (int j = 0; j < PLAYER_A_MANCALA; j++)
			{
				mancalaBoard[PLAYER_A_MANCALA].addStones(mancalaBoard[j].getStones());
				mancalaBoard[j].clearPit();
				for (ChangeListener c : listeners)
				{
					c.stateChanged(new ChangeEvent(this));
				}
			}
			winnerIs();
			return true;
		}
		return false;
	}
}
