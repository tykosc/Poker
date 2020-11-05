/* 
 * @Class Name: Player.java
 * 
 * This class simulates the player in the game of poker.  The player has a 
 * bankroll, which keeps track of their earnings, and a hand, 
 * consisting of five cards. This class has winnings for the player to 
 * add cards to their hand, make bets, calculate their winnings, 
 * and clear their hand. 
 * 
 * 
 * 
 * @Name: Talya Koschitzky
 * @UNI: tk2892
 * @Date: 06/25/2020
 * */

import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; //the player's cards
	private double bankroll;
    private double bet;
    private final int HANDSIZE = 5; 
    
	public Player() {		
	    //create a player here
        //the bankroll starts at 0.
        bankroll = 0; 
        bet = 1; //default bet is 1 
        hand = new ArrayList<Card>(); 
	}

	public void addCard(Card c) {
      //add the card c to the player's hand
        hand.add(c);
	}
    
    //This method is instead of remove card
    //replaces the card at a given index with a new card
    public void replaceCard(int index, Card c) {
        hand.set(index, c);
    }
		
    public void bets(double amt) {
        //player makes a bet
        bet = amt; 
        bankroll -= amt; 
    }
    
    //odds is the payout for the hand
    public void winnings(double odds) {
        //adjust bankroll if player wins
        double winnings = odds * bet; 
        bankroll += winnings; 
    }

    public double getBankroll() {
        //return current balance of bankroll
        return bankroll; 
    }

    //a helper method to get the hand
    public ArrayList<Card> getHand() {
        return hand; 
    }
    
    public double getBet() {
        return bet; 
    }
    
    //this method clears the players hand for
    //replaying purposes
    public void clearHand() {
        for (int i = 0; i < HANDSIZE; i++) {
            hand.remove(0);
        }
    }
}


