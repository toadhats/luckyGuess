This isn't interesting.







what each menu item must do:

  1. Choose player
    - Needs to initialise a new instance of Player (how?)
    - Ask player to input their name, pass to method in Player to validate/store
    - Say hi.
  
  2. Play one round
    - This is where the game itself happens
    - First set 'tries' to 0, then generate a lucky number
    - Ask the player to guess
    - If guess is correct player wins (add 10 to winnings, increment wins)
    - If guess is incorrect, subtract 1 from tries and give player a clue.
    - If player is out of guesses, tell them they lost. Increment losses. Tell them what the number was.
    - If their last guess was close, give them the consolation prize (5)
    - Ask them if they would like to play again or return to the menu.
    - **Need to know if 'close' constitutes a loss. Probably.**
    
  3. Display player statistics
    - Just a series of println()s giving wins, losses, win percentage, winnings.
    - Formula for win percentage = wins / (wins + losses)
    
  4. Display help
    - Just explain the rules of the game.
    
  5. Quit
    - Self explanatory, already done.
    
