**************************
README: Command Line Poker
**************************

Poker 
-----
TO PLAY
To play a game of poker, the game should be run from the main method of PlayGame.java. 
At the start of the round, the user prompted to bet 1 - 5 tokens, and then 
will be dealt five cards.  The user will then be prompted to type whether they want to
replace any of the five cards in their hand. The specified cards will be replaced, 
and the user will be shown their final hand, what it evaluates to, and the money they
gained from the round. Players can choose to play again, and they have a 
bankroll that is updated each round. The bankroll starts at $0.0.  Each 
token they bet costs the player $1, and at the end of the round the player
wins the payout of the hand * the number of tokens. The bankroll may be 
negative if the player loses money. The game can also be tested by setting the 
players hand and entering it as a String[] into the Game constructor.  
Cards should be entered as the first letter of the suit, followed by the rank number 
(1 is Ace, 11 - 13 is Jack - King). 
