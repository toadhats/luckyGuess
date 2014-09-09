import java.util.*;
/**
 * This is the main class for the project, including the game loop and the various methods used for game logic
 * 
 * @author (Jonathan Warner) 
 * @version (a version number or a date)
 */
public class Game
{
    // instance variables
    private int guesses;
    public static boolean debug;
    private boolean alreadyPlaying;
    private boolean inMenu;
    //private boolean inGame;
    Scanner console;    //I feel like I should declare this with the other instance variables,
    //but I could be wrong?
    
    
    /**
     * Set up the player (initialise stats and get a name)
     */
    public void setupPlayer()
    {
        // put your code here
        inMenu = false;
        boolean settingUpPlayer = true;
        //Instantiate a Player?
        while (settingUpPlayer) 
        {
            System.out.println("Are you sure you want to start a new game? Enter Y to confirm, N to go back.");
            console = new Scanner(System.in);
            String choice = console.nextLine();
            choice = choice.toLowerCase();
            choice = choice;
            switch(choice.charAt(0))
            {
                case 'n':   System.out.println("Ok, returning to the main menu.");
                            settingUpPlayer = false;
                            menuLoop();
                            break;
                case 'y':   System.out.println("Ok, let's continue");
                            alreadyPlaying = false;
                            break;
                default:    System.out.println("Please enter either 'yes' or 'no'");
                            continue;
            }
            
            
        
        System.out.println("What is your name?");
        
        console = new Scanner(System.in);
        String nameEntry = console.nextLine();
        
        // only assigns the name if it passes validation
        if (nameEntry.matches("(.*)\\d+|[\\W&&[^\\s\\-]]+(.*)") )// The first expression ("\\d+") tests if there is a number anywhere in the string. 
            System.out.println("That doesn't seem like a real name.");  // The second ("[\\W&&[^\\s\\-]}") tests if there is a 'non word' char other than a space or a '-' - in some implementations of regex this would be enough.
                                                                        // The (.*)s are there because it was allowing invalid characters if they appeared with valid characters, ie "Name." or "n6ame".
        else                                                            // This wouldn't be necessary if I used the "Matcher" class instead of String.matches, but that requires a bit more code - i'll use it if I end up making a seperate validator
            {                                                           // The ONLY hole in this that I can find is that it is possible to enter just dashes or spaces for the name. Fix this, we're close!
            name = nameEntry; 
            if (Game.debug) 
                System.out.println("Player: Name set as " + name);
            }
        
        }
}

    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        // initialise instance variables
        guesses = 0;
        inMenu = false;
        alreadyPlaying = false;
        //inGame = false; //not using this yet idk
        Game.debug = false; //set to FALSE before submitting I guess. Or make that secret option to toggle it.
    }

    /**
     * This is the menu loop. Not sure whether to do seperate loops/blocks for the various elements
     * of the game, so I'm keeping my options open.
     * 
     */
    public void menuLoop()
    {
        // Everything should already have been initialised when the instance was created.
        inMenu = true;
        int choice = 0;

        System.out.println("-----------------");
        System.out.println("*****************");
        System.out.println("**~Lucky Guess~**");
        System.out.println("*****************");
        System.out.println("-----------------");        
        System.out.println();

        while (inMenu)
        {
            int errors = 0;

            console = new Scanner(System.in); 
            /**it's important to put this inside the loop. If the scanner is instantiated outside 
             * of the loop it outlives it, which means if there's an input mismatch (which causes the buffer
             * to remain uncleared) we don't get a chance to go again with new input, and the existing input
             * causes a total freakout
               */

            System.out.println("Main Menu");
            System.out.println("=================");
            System.out.println("1. Choose player");
            System.out.println("2. Play a round");
            System.out.println("3. Show player stats");
            System.out.println("4. Display game help");
            System.out.println("5. Exit game.");
            System.out.println("Select an option and press Enter:");
            try 
            {
                choice = console.nextInt();
                break;
            }
            catch (java.util.InputMismatchException wrongInput)
            {
                choice = 0;
                System.out.println("Invalid input. Please enter a number from 1 - 5.");
                errors++;
                if (errors > 3) {
                    System.out.println("Please stop trying to break me ;-;");}
                //todo: implement emotions.
                continue;
            }
        }
        if (Game.debug) 
            System.out.println("Debug: Exiting menu loop. You entered " + choice);

        switch (choice)
        {
            case 1:     //Choose Player
            System.out.println("Debug: 'Choose player' selected, does nothing currently.");
            break;
            case 2:     //Play a round.        
            System.out.println("Debug: 'Play a round' selected, does nothing currently.");
            break;
            case 3:     //Show player stats
            System.out.println("Debug: 'Show player stats' selected, does nothing currently.");
            break;
            case 4:     //Display game help
            System.out.println("Debug: 'Display game help' selected, does nothing currently.");
            break;
            case 5:     //Exit game.
            inMenu = false;
            System.out.println("Thanks for playing. Goodbye!");
            break;
            case 420:   //Secret debug option!
            Game.debug = true; //keeping the debug var in Game only now, it's static so the others should be able to access it?
            System.out.println("Debug mode enabled.");
            menuLoop();
            default:    //Any other numeric input
            System.out.println("Please enter a number between 1 and 5.");
            menuLoop();
        }
    }

}