
/**
 * Description of a Player object with fields for name, number of wins/losses, and total winnings.
 * 
 * @author (Jonathan Warner) 
 * @version (20/08/2014)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private int wins;
    private int losses;
    private int winnings;
    private boolean debug;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        name = "";
        wins = 0;
        losses = 0;
        winnings = 0;
        debug = false;
        if (debug) 
                System.out.println("Player: new player initialised.");
    }

    /**
     * Adds winnings to the total.
     *
     * @param     prize    The amount won this round
     */
    public void addWinnings(int prize)
    {
        winnings = winnings + prize;
        if (debug) 
                System.out.println("Player: Added " + prize + "to winnings, new total = " + winnings);
    }
    
    /**
     *Toggles debug mode
     */
    public void debug()
    {
       if (debug == true)
       {
           debug = false;
           System.out.println("Player: Debug mode disabled.");
       }
       else
       {
           debug = true;
           System.out.println("Player: Debug mode enabled.");
       }
    }
    
         /**
     * Returns the player's losses.
     */
    public int getLosses()
    {
        // nothing to see here.
        if (debug) //this is more or less how my half-baked 'debug' system works. A old habit that might not be necessary in BlueJ?
            System.out.println("Player: Method getLosses returned: losses = " + losses);
        return losses;
    }
    
    /**
     * Returns the player's name.
     */
    public String getName()
    {
        // nothing to see here.
        if (debug) //this is more or less how my half-baked 'debug' system works. A old habit that might not be necessary in BlueJ?
            System.out.println("Player: Method getName returned: name = " + name);
        return name;
    }
    
     /**
     * Returns the player's wins.
     */
    public int getWins()
    {
        // nothing to see here.
        if (debug) //this is more or less how my half-baked 'debug' system works. A old habit that might not be necessary in BlueJ?
            System.out.println("Player: Method getWins returned: wins = " + wins);
        return wins;
    }
    
     /**
     * Returns the player's winnings.
     */
    public int getWinnings()
    {
        // nothing to see here.
        if (debug) //this is more or less how my half-baked 'debug' system works. A old habit that might not be necessary in BlueJ?
            System.out.println("Player: Method getWinnigs returned: winnings = " + winnings);
        return winnings;
    }
    
    /**
     * Increments losses by one.
     *
     */
    public void incrementLosses()
    {
        losses = losses + 1;
        if (debug) 
                System.out.println("Player: Losses incremented by 1, new total = " + wins);
    }
    
    /**
     * Increments wins by one.
     *
     */
    public void incrementWins()
    {
        wins = wins + 1;
        if (debug) 
                System.out.println("Player: Wins incremented by 1, new total = " + wins);
    }

    /**
     * Reset player stats.
     *
     */
    public void resetStats()
    {
        wins = 0;
        losses = 0;
        winnings = 0;
        if (debug) 
                System.out.println("Player: Stats reset.");
    }

    
    /**
     * Sets the object's "name" field, validating input to ensure the user only typed characters from a-z
     * If the input is valid, converts it into proper title case for later use. 
     * !!!To do this I will need to learn how to include someone else's method: WordUtils.capitalizeFully() http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/text/WordUtils.html#capitalizeFully%28java.lang.String%29
     * 
     * @param  newName  the string to be validated and assign to name
     */
    public void setName(String newName)
    {
        // only assigns the name if it passes validation
        if (newName.matches("(.*)\\d+|[\\W&&[^\\s\\-]]+(.*)") )// The first expression ("\\d+") tests if there is a number anywhere in the string. 
            System.out.println("That doesn't seem like a real name.");  // The second ("[\\W&&[^\\s\\-]}") tests if there is a 'non word' char other than a space or a '-' - in some implementations of regex this would be enough.
                                                                        // The (.*)s are there because it was allowing invalid characters if they appeared with valid characters, ie "Name." or "n6ame".
        else                                                            // This wouldn't be necessary if I used the "Matcher" class instead of String.matches, but that requires a bit more code - i'll use it if I end up making a seperate validator
            {                                                           // The ONLY hole in this that I can find is that it is possible to enter just dashes or spaces for the name. Fix this, we're close!
            name = newName; 
            if (debug) 
                System.out.println("Player: Name set as " + name);
            }
    }
}
