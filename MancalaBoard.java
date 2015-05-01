import java.util.*;

import javax.swing.event.*;

public class MancalaBoard
{
	public static final int PLAYER_A_MANCALA = 6;
	public static final int PLAYER_B_MANCALA = 13;
	private boolean player = true; // true == a, false == b
	private Pit[] mancalaBoard;
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
	 * 
	 * @return player playing
	 */
	public boolean getPlayer()
	{
		return player;
	}

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
		if (getPlayer()) // Should enable gameplay from console. I'll update it after work.
		{
			return x;
		} else
			return 12 - x;
	}

	/**
	 * prints out the mancala board
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
	 * @param mancalaPitIndex
	 *            pit address in the board
	 */
	// TODO include case where all of A's pits are 0 so he can not move
	public void move(int mancalaPitIndex)
	{
		// printBoard(); System.out.print("\n");
		int stonesInHand = mancalaBoard[mancalaPitIndex].getStones();
		int mancalaPitIndex2 = 0;
		if (stonesInHand == 0 || mancalaPitIndex == PLAYER_A_MANCALA
				|| mancalaPitIndex == PLAYER_B_MANCALA||mancalaBoard[mancalaPitIndex].whichPlayer()!=player)
		{
			chatHistory.add("Invalid move, try again");
			return;
			// return; //wont change the boolean value at the end so the player will go again and wont do anything in the for loop
		}
		// makes sure to only undo the very last move
		for (int i = 0; i < mancalaBoard.length; i++)
		{
			mancalaBoard[i].setPlayedOn(false);
		}
		mancalaBoard[mancalaPitIndex].setStones(0); // removes the stone from the pit you start with
		for (int i = stonesInHand; i > 0; i--)
		{

			if ((mancalaPitIndex + 1) == 14) // makes the manacala board wrap around itself hopefully
			{
				mancalaBoard[0].setPlayedOn(true);			
				mancalaPitIndex = -1; // so in the next iteration the mancala+= 1 == 0
			}
			

			mancalaBoard[mancalaPitIndex += 1].addStone();
			
			mancalaBoard[mancalaPitIndex].setPlayedOn(true);			

			if ((PLAYER_A_MANCALA == mancalaPitIndex)
					&& (player != mancalaBoard[PLAYER_A_MANCALA].whichPlayer())) // skips the opponents mancala
			{ // remove the extra stone added to the enemy players mancala
				mancalaBoard[mancalaPitIndex]
						.setStones(mancalaBoard[mancalaPitIndex].getStones() - 1);
				i++;
			}
			if ((PLAYER_B_MANCALA == mancalaPitIndex)
					&& (player != mancalaBoard[PLAYER_B_MANCALA].whichPlayer())) // skips the opponents mancala
			{ // remove the extra stone added to the enemy players mancala
				mancalaBoard[mancalaPitIndex]
						.setStones(mancalaBoard[mancalaPitIndex].getStones() - 1);
				i++;
			}

			if (i == 1) // if it is the last stone
			{
				if ((PLAYER_A_MANCALA == mancalaPitIndex) && (player == true)) // go again if you last stone lands in your mancala
				{
					for (ChangeListener c : listeners)
					{
						c.stateChanged(new ChangeEvent(this));
					}

					// char input = in.next().charAt(0);
					// mancalaPitIndex2 = in.nextInt();
					// move(mancalaPitIndex2); //play again
					chatHistory.add("Play again ");
					return;
					// move(inputs(input));
					// player= !player; //nullify the player = !player in the move
				}
				if ((PLAYER_B_MANCALA == mancalaPitIndex) && (player == false)) // go again if you last stone lands in your mancala
				{
					for (ChangeListener c : listeners)
					{
						c.stateChanged(new ChangeEvent(this));
					}

					chatHistory.add("Play again ");
					return;
					// mancalaPitIndex2 = in.nextInt();
					// move(mancalaPitIndex2); //play again
					// player= !player; //nullify the player = !player in the move(index2)
				}
			}
		}
		//TODO clear opposite pit method
		if ((mancalaBoard[mancalaPitIndex].getStones() == 1)) // you land on an empty spot on your side and it is not a mancala
		{
			if ((player) && (12 - mancalaPitIndex  != PLAYER_A_MANCALA)
					&& (mancalaBoard[mancalaPitIndex].whichPlayer() == player))
			{
				mancalaBoard[PLAYER_A_MANCALA]
						.addStones(mancalaBoard[12 - mancalaPitIndex].getStones());
				mancalaBoard[12 - mancalaPitIndex].clearPit();
			}
			if ((!player) && (12 - mancalaPitIndex != PLAYER_B_MANCALA)
					&& (mancalaBoard[mancalaPitIndex].whichPlayer() == player))
			{
				mancalaBoard[PLAYER_B_MANCALA]
						.addStones(mancalaBoard[12 - mancalaPitIndex]
								.getStones());// take the opposite +6 to get accross the board if player A. use -6 if player B
				mancalaBoard[12 - mancalaPitIndex].clearPit();// TODO set these to be playedon ==true
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
			return;
		} else
			chatHistory.add("Player B");
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
	public void winnerIs() // you need to change this, we're not straight up printing stuff in the final
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

	public void undoBoard()
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
		return;
	}

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
				mancalaBoard[PLAYER_B_MANCALA]
						.setStones(mancalaBoard[PLAYER_B_MANCALA].getStones()
								+ mancalaBoard[j].getStones());
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
				mancalaBoard[PLAYER_A_MANCALA]
						.setStones(mancalaBoard[PLAYER_A_MANCALA].getStones()+ mancalaBoard[j].getStones());
			}
			winnerIs();
			return true;
		}
		return false;
	}
	//public void clearoppositePit(int mancalaPitIndex)
	{
		
	}
}
