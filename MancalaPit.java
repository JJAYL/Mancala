class MancalaPit extends Pit
{
   private boolean whichPlayer; // determines access to mancala, true for A and false for B
   private int stones;
   int previousStones;
   /**
    * contructs the MancalaPit
    * @param initialStones starting stones
    * @param player which players mancala it belongs to
    */
   public MancalaPit(int initialStones, boolean player)
   {       
       super(initialStones, player);
       stones = 0;
   }
}
