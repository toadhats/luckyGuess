import java.util.*;
/**
 * This is the main class for the project, including the game loop 
 * and the various methods used for game logic
 * 
 * @author (Jonathan Warner) 
 * @version (11/09/2014)
 */
public class Game
{
    // instance variables
    private boolean alreadyPlaying;
    Scanner console;
    public static boolean debug;
    private int guesses;
    private static int MAX_GUESSES = 3;
    private boolean inMenu;
    private boolean inGame;
    Player player;
    
    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();       
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
        Game.debug = false;
    }

    /**
     * Compares a guess to a lucky number and returns a clue for the player.
     * Did it this way because I'm trying to force my brain into the 'don't repeat yourself' 
     * OO paradigm or w/e
     *
     * @param  playerGuess   the player's guess (int)
     * @param  luckyNumber   the lucky number (int)
     * @return     a string containing the clue
     */
    public String giveClue(int playerGuess, int luckyNumber)
    {
        if (playerGuess > luckyNumber)
            return "You need to aim a little lower.";
        else
            return "You need to think bigger.";
    }

    
    /**
     * This is the menu loop. Each option exits this loop completely, it's 
     * re-initialised from scratch or whatever each time. I thought this way
     * would be less headache inducing than a bunch of nested loops.
     * 
     */
    public void menuLoop()
    {
        // Everything else should already have been initialised when the instance was created.
        inMenu = true;
        int choice = 0;

        while (inMenu)
        {
            int errors = 0;

            console = new Scanner(System.in); 
            /**it's important to put this inside the loop. If the scanner is instantiated outside 
             * of the loop it outlives it, which means if there's an input mismatch (which causes the buffer
             * to remain uncleared) we don't get a chance to go again with new input, and the existing input
             * causes a total freakout
             */

            System.out.println("=================");
            System.out.println("Main Menu");
            System.out.println("=================");
            System.out.println("1. Choose player");
            System.out.println("2. Play a round");
            System.out.println("3. Show player stats");
            System.out.println("4. Display game help");
            System.out.println("5. Exit game.");
            System.out.println("Select an option and press Enter:");
            System.out.print("> ");
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
                if (errors > 3) 
                    System.out.println("Please stop trying to break me ;-;");
                continue;
            }
        }
        if (Game.debug) 
            System.out.println("\nDebug: Exiting menu loop. You entered " + choice);

        //We have the player selection. Now we enter the switch.

        switch (choice)
        {
            case 1:     //Choose Player
                        inMenu = false;
                        setupPlayer();
                        return; //best to use return instead of break here, since I am done with the whole method
            case 2:     //Play a round.        
                        inMenu = false;
                        playGame();
                        return;
            case 3:     //Show player stats
                        inMenu = false;
                        showStats();
                        return;
            case 4:     //Display game help
                        inMenu = false;
                        showHelp();
                        return;
            case 5:     //Exit game.
                        inMenu = false;
                        System.out.println("\nThanks for playing. Goodbye!");
                        return;
            case 420:   //Secret debug option!
                        if (Game.debug)
                        {
                            Game.debug = false; 
                            System.out.println("\nDebug mode disabled.");
                        }
                        else 
                        {
                            Game.debug = true;
                            System.out.println("\nDebug mode enabled.");
                        }
                        menuLoop();
                        break;
            default:    //Any other numeric input
                        System.out.println("Please enter a number between 1 and 5.");
                        //menuLoop(); not sure why I don't need this here but do just above
        }
    }

    /**
     * The actual game goes here. The loop will run for as long
     * as the player wants to keep playing. I've tried a few different ways
     * of doing the same thing. They all seem to work, so let me know
     * which is the best to stick with in future.
     *
     */
    public void playGame()
    {
        if (!alreadyPlaying)
        {
        System.out.println("\nYou haven't created a player yet!");
        System.out.println("\nPress Enter to return to the menu\n");
        pressEnter(false);
        menuLoop();
        return; //still don't know if this works the way I think it does.
        }
        
        inGame = true;
        guesses = MAX_GUESSES;        
        int luckyNumber = Generator.generateLucky();
        int playerGuess = 0;
        boolean playAgain = false;

        System.out.println("\n\n_________________________");
        System.out.println("I'm thinking of a number between 1 - " + Generator.MAX_NUMBER + ".");
        System.out.println("Can you guess what it is? You have " + MAX_GUESSES + " tries.");
        gameLoop:
        while (inGame) //I could also make this loop run until guesses = 0 but i want to do it the other way.
        {
            if (guesses > 0)
            {
                if (guesses < MAX_GUESSES)
                    System.out.println(guesses +" tries remaining.");
                
                System.out.println("\nEnter a number between 1 - " + Generator.MAX_NUMBER + ":");
                System.out.print("> ");

                console = new Scanner(System.in);
                //First we make sure we actually get a number.
                try 
                {
                    playerGuess = console.nextInt();
                    console.nextLine();
                }
                catch (java.util.InputMismatchException wrongInput)
                {
                    System.out.println("\nYou entered something dumb. That counts as a try btw.");
                    guesses--;
                    continue;
                }
                //Ok they at least entered a number. Now make sure number is in the correct range.
                if (playerGuess < 1 || playerGuess > 100)
                {
                    guesses--;
                    System.out.println("\nThat's not a number between 1 - " + Generator.MAX_NUMBER + ". It is a guess though.");
                    continue;
                }
                //Now to see how they did.
                if (playerGuess == luckyNumber)
                {
                    player.incrementWins();
                    player.addWinnings(10);

                    System.out.println();
                    System.out.println(".-*-.-*-.-*-.-*-.-*-.-*-.");
                    System.out.println("         CORRECT!");
                    System.out.println(".-*-.-*-.-*-.-*-.-*-.-*-.");
                    System.out.println("\nYou win '$10'.");
                    if (player.getWinnings() > 10)
                        System.out.println("That makes for a grand total of " + player.getWinnings() + " imaginary dollars.");
                    player.reportWins();
                    System.out.println();
                    System.out.println("Would you like to play again?");
                    //really wish I did not have to wrap this in a loop just to deal with bad input.
                    //make that a loop AND a try
                    boolean stillChoosing = false;
                    do 
                    {
                        System.out.print("> ");
                        String choice = console.nextLine();
                        choice = choice.toLowerCase();
                        try
                        {                            
                            switch(choice.charAt(0)) //crashes if I just press enter. (fixed)
                            {
                                case 'n':   System.out.println("Ok, returning to the main menu.");
                                            inGame = false;
                                            menuLoop();
                                            return; //exits the whole method - we're not playing anymore
                                case 'y':   System.out.println("Ok!");
                                            inGame = false;
                                            playAgain = true;
                                            break gameLoop;  //skip ahead to the very end of the method where that conditional will fire. 
                                                    //Just experimenting with this tbh
                                default:    System.out.println("Please enter either 'yes' or 'no'");
                                            stillChoosing = true;
    
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Please enter either 'yes' or 'no'");
                                            stillChoosing = true;
                        }
                    } while (stillChoosing);
                }
                else if (playerGuess < (luckyNumber + 5) && (playerGuess > (luckyNumber - 5)))
                //They didnt guess it this time, but they were close. 
                {
                    guesses--;
                    System.out.print("\nClose, but not close enough. ");
                    //even though it is only used twice, I put 'giveClue' in its own method. 
                    //Experimenting with using return instead of a println in the other method. Which is better?
                    System.out.println(giveClue(playerGuess, luckyNumber));
                    continue;
                }

                //Not even close.

                else
                {
                    guesses--;
                    System.out.print("\nNope. ");
                    System.out.println(giveClue(playerGuess, luckyNumber));
                    continue;
                }
            }
            else //player ends up here when they are out of guesses
            {
                player.incrementLosses();
                System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println("        YOU LOSE");
                System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println("\n\n The lucky number was " + luckyNumber + ".");
                if (playerGuess < (luckyNumber + 5) && (playerGuess > (luckyNumber - 5)))
                {
                    System.out.println("You were so close! You win the $5 minor prize.");
                    player.addWinnings(5);
                    System.out.println("\nYou've won $" + player.getWinnings() + " so far.");
                }
                
                player.reportLosses();
                
                System.out.println("Would you like to play again?");
                //really wish I did not have to wrap this in a loop just to deal with bad input.
                //Could I have wrapped the whole thing below in its own method? Yes
                //There are a LOT of things I wrote earlier on that I would refactor/rewrite from scratch.
                boolean stillChoosing = false;
                do 
                {
                    System.out.print("> ");
                    String choice = console.nextLine(); //can I do choice = nextline OR 0? That would prevent the crash too.
                    choice = choice.toLowerCase();

                    try
                    {
                        switch(choice.charAt(0))//how do i get around the bug where this breaks if user just presses enter? (fixed I think)
                        {
                            case 'n':   System.out.println("Ok, returning to the main menu.");
                                        stillChoosing = false;
                                        inGame = false;
                                        menuLoop();
                                        return; //exits the whole method - we're not playing anymore
                            case 'y':   System.out.println("Ok!");
                                        stillChoosing = false;
                                        inGame = false;
                                        playAgain = true;
                            break; //breaks out of this while loop, so skip ahead to the very end of the method. Just experimenting with this tbh
                            default:    System.out.println("Please enter either 'yes' or 'no'");
                                        stillChoosing = true;
    
                        }
                    }
                    catch (Exception somethingDumb)
                    {
                        System.out.println("Please enter either 'yes' or 'no'");//Lol created a bug where you can press enter to win
                        stillChoosing = true;
                    }
                } while (stillChoosing);

            }
        }
        if (playAgain)
            playGame(); //I ended up doing it this way to avoid some 'goto-like' behaviour
    }
    

    /**
     * Wait for the user to press Enter. Worked this out after failing
     * to figure out how to get java to accept raw keyboard input for 
     * the old "press any key".
     * Takes a parameter to determine whether to include the prompt,
     * or gag it when I want to print some other message.
     * 
     * @param echo   Pass true to prompt, false to just wait for input.
     * 
     */
    public void pressEnter(boolean echo)
    {
        if (echo)
            System.out.println("\nPress Enter to continue.");
        try {
            System.in.read();}
        catch (Exception e) {} //not sure why it won't let me compile without handling this exception.
    }

    /**
     * Prints the game intro banner. Just makes the code cleaner
     * up there where it matters really. I should have done more
     * like this.
     */
    public void printIntro()
    {
        System.out.println("-----------------------");
        System.out.println("***********************");
        System.out.println("**** ~Lucky Guess~ ****");
        System.out.println("***********************");
        System.out.println("-----------------------");        
        System.out.println();
    }

    /**
     * A menu to change the game's parameters.
     *
     */
    public void settingsMenu()
    {
        // put your code here
        System.out.println("\n_________________________");
        System.out.println("Game Settings");
        //FINISH THIS SOME OTHER TIME
    }

    
    /**
     * Set up the player (initialise stats and get a name)
     */
    public void setupPlayer()
    {
        // put your code here
        inMenu = false;
        boolean settingUpPlayer = true;
        //If user has a game in progress, confirm that they want to start over with a new name/stats
        while (settingUpPlayer) 
        {
            if (alreadyPlaying)
            {
                System.out.println("Are you sure you want to start a new game?\n"
                    + "You'll lose your imaginary money and meaningless stats.\n"
                    + "Enter Y to confirm, N to go back.");
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
            }
            //If we make it to here the user should have confirmed that they want to start over

            if (debug)
                System.out.println("Debug: instantiating a new player object");

            player = new Player(); //Making a new instance initialises all variables via the constructor method.

            System.out.println("What is your name?");
            console = new Scanner(System.in);
            String nameEntry = console.nextLine();

            /** The if statement below only assigns the name if it passes validation.
             *The first expression ("\\d+") tests if there is a number anywhere in the string.
             *The second ("[\\W&&[^\\s\\-]}") tests if there is a 'non word' char other than a space or a '-' 
             *in some implementations of regex this would be enough, but the one java uses treats numbers as word chars
             *The (.*)s are there because it was allowing invalid characters if they appeared with valid characters, ie "Name." or "n6ame".
             *This wouldn't be necessary if I used the "Matcher" class instead of String.matches, but that requires a bit more code.
             *If I had to validate any other string input like this I'd make a seperate validator method, but I don't, so I won't.
             */
            if (nameEntry.matches("(.*)\\d+|[\\W&&[^\\s\\-]]+(.*)") )
            {
                System.out.println("That doesn't seem like a real name.");
                continue; 
                //putting this here in an attempt to fix that bug where invalid names keep
                //the loop alive when returning to the menu
            }
            else                                                            
            {                                                           
                player.setName(nameEntry);
                if (debug)
                    System.out.println("Successfully set up the player, returning to the menu");
                System.out.println("Hello, " + player.getName() + ". Ready to play?");
                alreadyPlaying = true;
                settingUpPlayer = false;
                pressEnter(true);
                menuLoop();
            }

        }
    }

    /**
     * Displays the game help. Pretty basic stuff, nothing to see here
     *
     */
    public void showHelp()
    {
        System.out.println("═════════════");
        System.out.println(" How to play:");
        System.out.println("═════════════");

        System.out.println("The aim of the game is to pick the number the computer is thinking of.\n"
            + "The number can be 1 - " + Generator.MAX_NUMBER + ", and you get three guesses.\n"
            + "After each incorrect guess, you'll get a clue - higher or lower.\n"
            + "If you guess correctly, you win ten imaginary dollars,\n" 
            + "and your win is recorded. Temporarily. If your last guess\n"
            + "was close to the mark, you get five imaginary dollars.\n" 
            + "Enjoy, or whatever.\n");
        pressEnter(true);
        menuLoop();
    }

    /**
     * Displays stats for the current player. 
     * Will handle someone choosing this option without creating a player.
     * 
     */
    public void showStats()
    {
        double winPercentage;
        //where the int declaration used to be. Oops.
        if (alreadyPlaying)
        {
            int totalGames = player.getWins() + player.getLosses();//Lol i submitted with this outside the block, causing a crash and rendering this if statement useless. Oops.. Oops.
            winPercentage = 100 * ( (double) player.getWins() / (double) totalGames);
            System.out.println("\n_________________________");
            System.out.println("Player stats:");
            System.out.println("\nWins: " + player.getWins() 
                             + "\nLosses: " + player.getLosses()
                             + "\nWin percentage: " + winPercentage + "%"
                             + "\nWinnings: " + player.getWinnings());
            System.out.println("\nPress Enter to return to the menu\n");
            pressEnter(false);
            menuLoop();
        }
        else
        {
            System.out.println("\nYou haven't created a player yet!");
            System.out.println("Press Enter to return to the menu\n");
            pressEnter(false);
            menuLoop();
        }
    }

    
    /**
     * Starts the game. I guess this could be slightly superfluous but whatever man I do what I want
     *
     */
    public void start()
    {
        // Prints the game intro banner
        printIntro();
        menuLoop();
    }

}

