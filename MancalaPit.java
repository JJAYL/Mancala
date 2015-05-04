class MancalaPit extends Pit
{
   private boolean whichPlayer; // determines access to mancala, true for A and false for B
   private int stones;
   int previousStones;
   public MancalaPit(int initialStones, boolean player)
   {       
       super(initialStones, player);
       stones = 0;
   }
   /*public void addStones(int stones)
   {
       this.previousStones = this.stones;
       this.stones += stones;
   }
   */
}
