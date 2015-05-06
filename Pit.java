public class Pit
{
    private int stones;
    private boolean whichPlayer;//true for A, false for B
    private int previousStones;
    private boolean playedOn;
    /**
     * makes a put
     * @param initialStones stones the pit starts with
     * @param player which pit belongs to which player
     */
    public Pit(int initialStones, boolean player)
    {
        stones = initialStones;
        whichPlayer = player;
        previousStones = initialStones;
        playedOn=false;
    }
    /**
     * sets the amount of stones in a pit
     * @param stones stones in a pit
     */
    public void setStones(int stones)
    { 
        this.stones = stones;
    }
    /**
     * removes all the stones from the pit
     */
    public void clearPit()
    {
        previousStones = stones;
        stones = 0;
    }
    /**
     * says how many stones are in the pit
     * @return how many stones are in the pit
     */
    public int getStones()
    {
        return stones;
    }
    
    /**
     *adds one stone to the pit and saves the previous amount of stones 
     */
    public void addStone()
    {
        previousStones = stones;
        stones++;
    }
    
    /**
     * adds  multiple stones to the pit and saves the previous amount of stones
     * @param newStones
     */
    public void addStones(int newStones) //CHANGE THIS SHITTY NAME
    {
        previousStones = stones;
        this.stones+=newStones;
    }
    
    /**
     * resets the pit to the previous amount of stones
     */
    public void reset()
    {
        stones = previousStones;
    }
    
    /**
     * says which player the pit belongs to
     * @return which player owns the pit
     */
    public boolean whichPlayer()
    {
    	return whichPlayer;
    }
    
    /**
     * says if the pit was played on during a turn
     * @return whether the pit was played on
     */
	public boolean isPlayedOn()
	{
		return playedOn;
	}
	
	/**
	 * changes whether the pit was played on
	 * @param playedOn sets if it is played on
	 */
	public void setPlayedOn(boolean playedOn)
	{
		this.playedOn = playedOn;
	}
    
}
