
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
        if (Game.debug) 
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
        if (Game.debug) 
                System.out.println("Player: Added " + prize + "to winnings, new total = " + winnings);
    }
        
         /**
     * Returns the player's losses.
     * 
     * @return int Number of losses
     */
    public int getLosses()
    {
        // nothing to see here.
        if (Game.debug) 
            System.out.println("Player: Method getLosses returned: losses = " + losses);
        return losses;
    }
    
    /**
     * Returns the player's name.
     * 
     * @return String Player's name.
     */
    public String getName()
    {
        // nothing to see here.
        if (Game.debug) 
            System.out.println("Player: Method getName returned: name = " + name);
        return name;
    }
    
     /**
     * Returns the player's wins.
     * 
     * @return int Number of wins.
     */
    public int getWins()
    {
        // nothing to see here.
        if (Game.debug) 
            System.out.println("Player: Method getWins returned: wins = " + wins);
        return wins;
    }
    
     /**
     * Returns the player's winnings.
     * 
     * @return int Amount won so far.
     */
    public int getWinnings()
    {
        // nothing to see here.
        if (Game.debug) 
            System.out.println("Player: Method getWinnings returned: winnings = " + winnings);
        return winnings;
    }
    
    /**
     * Increments losses by one.
     *
     */
    public void incrementLosses()
    {
        losses = losses + 1;
        if (Game.debug) 
                System.out.println("Player: Losses incremented by 1, new total = " + wins);
    }
    
    /**
     * Increments wins by one.
     *
     */
    public void incrementWins()
    {
        wins = wins + 1;
        if (Game.debug) 
                System.out.println("Player: Wins incremented by 1, new total = " + wins);
    }

     /**
     * Prints the number of losses to the console, accounting for a value of 1.
     *
     */
    public void reportLosses()
    {
        if (losses == 1)
            System.out.println("You've lost one round.");
        else
            System.out.println("You've lost " + losses + " rounds.");
    }
    
    /**
     * Prints the number of wins to the console, accounting for a value of 1.
     *
     */
    public void reportWins()
    {
        if (wins == 1)
            System.out.println("You've won one round.");
        else
            System.out.println("You've won " + wins + " rounds.");
    }

    /**
     * Sets the object's "name" field.
     * There's no validation in this method anymore, I moved it to the method in Game that 
     * actually prompts the user for input. Uses the titleCase() method below.
     * 
     * @param  newName  the string to be titleCase-ed and assigned to name
     */
    public void setName(String newName)
    {
       name = titleCase(newName);
       if (Game.debug) 
             System.out.println("Player: Name set as " + name);
    }
    
    /**
     * Converts a string to title case. Based on a StackOverflow answer (http://stackoverflow.com/a/15738441/3959735)
     * 
     * @param string the string to be converted to title case.
     * @return String The formatted string.
     */
    
    public String titleCase(String string)
    {
        final String DELIMITERS = " -"; //Just spaces and dashes as that's all we've allowed in the name.
        
        StringBuilder formatted = new StringBuilder();
        boolean capNext = true;
        
        for (char c : string.toCharArray())
        {
            c = (capNext)
                ? Character.toUpperCase(c)
                : Character.toLowerCase(c);
            formatted.append(c);
            capNext = (DELIMITERS.indexOf((int) c) >= 0);
        }
        return formatted.toString();  
    }
}
