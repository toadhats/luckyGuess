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
    private boolean debug;
    private boolean inMenu;
    //private boolean inGame;
    Scanner console;    //I feel like I should declare this with the other instance variables,
                        //but I could be wrong?

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        // initialise instance variables
        guesses = 0;
        inMenu = false;
        //inGame = false; //not using this yet idk
        debug = false; //set to FALSE before submitting I guess. Or make that secret option to toggle it.
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
            //i tried putting 'choice' here but I moved it. Let's just see how that works out for us.
            console = new Scanner(System.in); 
            /**it's important to put this inside the loop. If the scanner is instantiated outside 
             * of the loop it outlives it, which means if there's an input mismatch, 
             * we don't get a chance to go again with new input, and the existing input
             * causes a total freakout*/

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
                continue;
            }
        }
        if (debug) 
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
                        this.debug = true;
                        //Player.debug(); //These generate compile error "non-static method cannot  
                        //Generator.debug();//be referenced from static context." Why?
                        System.out.println("Debug mode enabled.");
                        menuLoop();
        }
    }

}