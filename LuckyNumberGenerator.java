
/**
 * Generates the "lucky number" for the Lucky Guess game
 * Based on code sample provided in the lab class.
 * 
 * @author (Jonathan Warner) 
 * @version (20/08/2014)
 */
public class LuckyNumberGenerator
{
    // instance variables - replace the example below with your own
    private int luckyNumber;  //storing this now in case I think of something to do with it later
    private int maxNumber; //used for abstraction purposes, and to allow modification of the game

    /**
     * Constructor for objects of class LuckyNumberGenerator - pretty basic obviously
     * (Consider adding an 'overloaded' method to construct a generator with a different range?)
     */
    public LuckyNumberGenerator()
    {
        // initialise instance variables
        luckyNumber = 0;
        maxNumber = 100;
    }

    /**
     * Generate a "lucky number"
     * 
     * 
     */
    public int generateLucky()
    {
        // put your code here
        return 1 + (int)(Math.random() * maxNumber);
    }
}
