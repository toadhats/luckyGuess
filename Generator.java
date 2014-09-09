
/**
 * Generates the "lucky number" for the Lucky Guess game
 * Based on code sample provided in the lab class.
 * 
 * @author (Jonathan Warner) 
 * @version (20/08/2014)
 */
public class Generator
{
    // instance variables - replace the example below with your own
    private static int luckyNumber;  //storing this now in case I think of something to do with it later
    private static int maxNumber = 100; //used for abstraction purposes, and to allow modification of the game

    /**
     * Constructor for objects of class LuckyNumberGenerator - pretty basic obviously
     * (Consider adding an 'overloaded' method to construct a generator with a different range?)
     * (Nah that is probably overkill for this assignment)
     */
    public Generator()
    {
        // initialise instance variables
        luckyNumber = 0;
        maxNumber = 100;
    }
    
    /**
     * Generate a "lucky number"
     * 
     */
    public static int generateLucky()
    {
        // included a debug option to let us cheat while we're testing.
        luckyNumber = 1 + (int)(Math.random() * maxNumber);
        if (Game.debug) 
                System.out.println("Generator: our lucky number is " + luckyNumber);
        return luckyNumber;
    }

}
