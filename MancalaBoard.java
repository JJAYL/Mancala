import java.util.*;

import javax.swing.event.*;
public class MancalaBoard
{
	public static final int PLAYER_A_MANCALA = 6;
	public static final int PLAYER_B_MANCALA = 13;
    private boolean player=true; //true == a, false == b 
    private Pit[] mancalaBoard;
    private ArrayList<ChangeListener> listeners;
    Scanner in = new Scanner(System.in);
    /**
     * constructs the mancala board
     * @param initialStones stones that the board starts out with
     */
    public MancalaBoard(int initialStones)
    {
         mancalaBoard = new Pit[14];//set pit 6 and 13 to mancalas
         for(int i = 0; i < PLAYER_A_MANCALA; i++)
         {
             mancalaBoard[i] = new Pit(initialStones, true);
         }
         for(int i = PLAYER_A_MANCALA; i < PLAYER_B_MANCALA; i++)
         {
             mancalaBoard[i] = new Pit(initialStones, false);
         }
         mancalaBoard[PLAYER_A_MANCALA] = new MancalaPit(0, true);
         mancalaBoard[PLAYER_B_MANCALA] = new MancalaPit(0, false);
         listeners = new ArrayList<ChangeListener>();
    }
    /**
     * returns the mancala board
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
    
    
    public int inputs(char input)
    {
			char player = 'B';
			if(getPlayer()){player = 'A';}
			else player = 'B';
			if(input <= 'a' || input >= 'f')
			{
				System.out.print("invalid input");
			}
			int x = (int)input;
			x -= 97;
			if(getPlayer()) //Should enable gameplay from console. I'll update it after work.
			{
				return x;
			}
			else return 12-x;
    }
    /**
     * prints out the mancala board
     */
    public void printBoard()
    {
    	System.out.print("B:");
    	for(int i = 12; i > 6; i--)
    	{
    		System.out.print(Integer.toString(mancalaBoard[i].getStones())+ " ");
    	}
    	System.out.println();
    	System.out.println(mancalaBoard[PLAYER_B_MANCALA].getStones() + " a b c d e f " + mancalaBoard[PLAYER_A_MANCALA].getStones());
    	System.out.print("A:");
    	for(int i = 0; i < 6; i++)
    	{
    		System.out.print(mancalaBoard[i].getStones() + " ");
    	}
    }
    /**
     * picks a pit and plays a move
     * @param mancalaPitIndex pit address in the board
     */
    //TODO include case where all of A's pits are 0 so he can not move
    public void move(int mancalaPitIndex)
    {
    	int stonesInHand = mancalaBoard[mancalaPitIndex].getStones(); 
    	int mancalaPitIndex2=0;
    	if(stonesInHand==0)
    	{
    		System.out.println("Invalid move, try again");
    		return; //wont change the boolean value at the end so the player will go again and wont do anything in the for loop
    	}
    	mancalaBoard[mancalaPitIndex].setStones(0); //removes the stone from the pit you start with 
    	for(int i = stonesInHand; i >0 ;i--)
    	{     		
    		if((mancalaPitIndex+1)==14) //makes the manacala board wrap around itself hopefully
    		{
    			mancalaPitIndex = -1; //so in the next iteration the mancala+= 1 == 0
    		}
    		
    		mancalaBoard[mancalaPitIndex+= 1].addStone(); // if it isnt an opponent's mancala well
    		
    		if((PLAYER_A_MANCALA==mancalaPitIndex) && (player!=mancalaBoard[PLAYER_A_MANCALA].whichPlayer())) //skips the opponents mancala
    	   	{   //remove the extra stone added to the enemy players mancala
    		   	mancalaBoard[mancalaPitIndex].setStones(mancalaBoard[mancalaPitIndex].getStones()-1); 
    		   	i++;
    	  	} 
    	   	if((PLAYER_B_MANCALA==mancalaPitIndex) && (player!=mancalaBoard[PLAYER_B_MANCALA].whichPlayer())) //skips the opponents mancala
    	   	{   //remove the extra stone added to the enemy players mancala
    		   	mancalaBoard[mancalaPitIndex].setStones(mancalaBoard[mancalaPitIndex].getStones()-1);
    		   	i++;
    	  	}
 
    	   	if(i==1) //if it is the last stone
    	   	{
    	   		if((PLAYER_A_MANCALA == mancalaPitIndex)&&(player==true)) //go again if you last stone lands in your mancala
    	   		{ 	
    	   			System.out.println("\n");
    	   			printBoard();
    	   			System.out.print("\n Play again ");
    	   			char input = in.next().charAt(0);
    	   			//mancalaPitIndex2 = in.nextInt();
        	   		//move(mancalaPitIndex2); //play again
        	   		move(inputs(input));
    	   			player= !player; //nullify the player = !player in the move
    	   		}
    	   		if((PLAYER_B_MANCALA == mancalaPitIndex)&&(player==false)) //go again if you last stone lands in your mancala
    	   		{
    	   			System.out.println("\n");
    	   			printBoard();
    	   			System.out.print("Play again ");
    	   			mancalaPitIndex2 = in.nextInt();
    	   			move(mancalaPitIndex2); //play again
        	   		player= !player; //nullify the player = !player in the move(index2)
    	   		}
    	   	}
    	}
       if((mancalaBoard[mancalaPitIndex].getStones()==1)) //you land on an empty spot on your side and it is not a mancala
       {
    	   if((player)&&(mancalaPitIndex!=PLAYER_A_MANCALA)&&(mancalaBoard[mancalaPitIndex].whichPlayer()==player)) //if it is player A and is not his mancala TODO may need to account for player A playing in player B mancala
    	   {	  
    		   mancalaBoard[PLAYER_A_MANCALA].addStones(mancalaBoard[mancalaPitIndex+6].getStones());//take the opposite +7 to get accross the board if player A. use -7 if player B
    		   mancalaBoard[mancalaPitIndex+6].clearPit();
    	   }
    		if((!player)&&(mancalaPitIndex!=PLAYER_B_MANCALA)&&(mancalaBoard[mancalaPitIndex].whichPlayer()==player))   
    	   {
    		   mancalaBoard[PLAYER_B_MANCALA].addStones(mancalaBoard[mancalaPitIndex-6].getStones());//take the opposite +6 to get accross the board if player A. use -6 if player B
    		   mancalaBoard[mancalaPitIndex-6].clearPit();
    	   }
       }
       //changes the players turn
       player=!player;  //this may be nullified if you play in the mancala
       for(ChangeListener c: listeners)
       {
    	   c.stateChanged(new ChangeEvent(this));
       }
   }
    public void attach(ChangeListener c)
    {
    	listeners.add(c);
    }
    /**
     * declares the winner
     */
   public String winnerIs() //you need to change this, we're not straight up printing stuff in the final
   {
	   String winner = "";
	   if(mancalaBoard[PLAYER_A_MANCALA].getStones()>mancalaBoard[PLAYER_B_MANCALA].getStones())
	   {
		   winner ="Player A wins";
	   }
	   else if(mancalaBoard[PLAYER_A_MANCALA].getStones()<mancalaBoard[PLAYER_B_MANCALA].getStones())
	   {
		   winner = "Player B wins";
	   }
	   else winner = "Tie";
	   return winner;
   }
}
