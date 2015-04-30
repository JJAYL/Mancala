public class Pit
{
    private int stones;
    private boolean whichPlayer;//true for A, false for B
    private int previousStones;
    public Pit(int initialStones, boolean player)
    {
        stones = initialStones;
        whichPlayer = player;
        previousStones = initialStones;
    }
    public void setStones(int stones)
    { 
        this.stones = stones;
    }
    public void clearPit()
    {
        previousStones = stones;
        stones = 0;
    }
    public int getStones()
    {
        return stones;
    }     
    public void addStone()
    {
        previousStones = stones;
        stones++;
    }
    
    public void addStones(int newStones) //CHANGE THIS SHITTY NAME
    {
        previousStones = stones;
        this.stones+=newStones;
    }
    public void reset()
    {
        stones = previousStones;
    }
    public boolean whichPlayer()
    {
    return whichPlayer;
    }
    
}
