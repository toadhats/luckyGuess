
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
    }

    /**
     * Sets the object's "name" field, validating input to ensure the user only typed characters from a-z
     * If the input is valid, converts it into proper title case for later use. 
     * !!!To do this I will need to learn how to include someone else's method: WordUtils.capitalizeFully() http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/text/WordUtils.html#capitalizeFully%28java.lang.String%29
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void setName(String newName)
    {
        // put your code here
        if (newName.matches("(.*)\\d+|[\\W&&[^\\s\\-]]+(.*)") )// The first expression ("\\d+") tests if there is a number anywhere in the string. 
            System.out.println("That doesn't seem like a real name.");  // The second ("[\\W&&[^\\s\\-]}") tests if there is a 'non word' char other than a space or a '-' - in some implementations of regex this would be enough.
                                                                        // The (.*)s are there because it was allowing invalid characters if they appeared with valid characters, ie "Name." or "n6ame".
        else                                                            // This wouldn't be necessary if I used the "Matcher" class instead of String.matches, but that requires a bunch more code - i'll use it if I end up making a seperate validator
            {                                                           // The ONLY hole in this that I can find is that it is possible to enter just dashes or spaces for the name. Fix this, we're close!
            name = newName; 
            System.out.println("Nice to meet you, " + name + ".");
            }
    }
}
