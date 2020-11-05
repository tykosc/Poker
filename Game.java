/* 
 * @Class Name: Game.java
 * 
 * This class simulates a poker game. The game contains a player and a 
 * deck of cards.  There are two constructors for Game, one that takes 
 * in string arguments to be used for the command line, and another 
 * that takes in no parameters to play an actual game.  The play() method
 * plays the game, in which the user makes a bet, choses to replace certain
 * cards, and then sees the results. 
 * 
 * 
 * @Name: Talya Koschitzky
 * @UNI: tk2892
 * @Date: 06/25/2020
 * */

import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Collections; 

public class Game {
	
	private Player p;
	private Deck cards;
    private boolean isTest; 
    private double payout; 
    private boolean keepPlaying; 
		
    //constructor for command line test version
	public Game(String[] testHand){
        p = new Player();
        cards = new Deck(); 
        isTest = true; 
        keepPlaying = true; 
  
        //fill the players hand from the String[]
        for (int i = 0; i < testHand.length; i++) {
            Card c = new Card(testHand[i]);
            p.addCard(c);    
        }      
	}
	
    // This no-argument constructor is to play a normal game
	public Game(){
        p = new Player();
        cards = new Deck();
        isTest = false; 
        keepPlaying = true; 
	}
	
    //plays the game 
	public void play(){
        if (isTest) {
           playTest(); 
        } 
        else {
            while (keepPlaying) {
                
               //make a bet 
                makeBet(); 
                  
                //shuffle the cards 
                cards.shuffle();
            
                //computer deals and prints the hand 
                dealHand();
                
                //user chooses to replace or keep cards
                replaceHand();
                   
                //check the hand and print it 
                String handType = checkHand(p.getHand()); 
                System.out.print(handType + ": ");
                
                //assign payout based on hand 
                assignPayout(handType); 
            
                //update players bankroll 
                p.winnings(payout); 
            
                //print results bankaccount information
                printResults(); 
                
                //play a new game if the user wants
                newGame(); 
            }
        }
	
	}
    
    //play a test game 
    private void playTest() {
        //check hand 
        String handType = checkHand(p.getHand()); 
        System.out.println(handType);
                
        //assign payout based on hand 
        assignPayout(handType); 
        System.out.println("Payout: $" + payout);
    }
    
    //make a bet 
    private void makeBet() {
        Scanner input = new Scanner(System.in); 
        System.out.println("how many tokens would you like to bet (1-5)?");
        double bet = input.nextDouble(); 
        p.bets(bet);
    }
    
    //deal a hand and print it 
    private void dealHand() {
         System.out.println("\n");
         for (int i = 1; i < 6; i++) {
             Card c = cards.deal();
             p.addCard(c);
             System.out.println(c + "\n");
         }
    }       
    
    //replace cards and print new hand 
    private void replaceHand() {
        //user can choose cards to replace 
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Would you like to replace card " + (i + 1) +
                               "?");
            System.out.println("Type 'yes' or 'no'");
            String answer = input.next();
            if (answer.equalsIgnoreCase("yes")) {
                p.replaceCard(i, cards.deal());
            }    
         }
        
         //Computer prints out the final hand 
         System.out.println("Your new hand is: " + "\n"); 
         for (Card element : p.getHand()) {
             System.out.println(element + "\n");
         }            
    }
     
    //print round results 
    private void printResults() {
        System.out.println("Payout = $" + payout );
        System.out.println("Bet = $" + p.getBet());
        System.out.println("Winnings this round : $" + (payout * p.getBet()));
        System.out.println("Updated bankroll : $" + p.getBankroll());
    }
    
    //play a new game or terminate 
    private void newGame() {
        Scanner input = new Scanner(System.in); 
        System.out.println("Would you like to play again?");
        System.out.println("Type 'yes' or 'no'");
        
        String again = input.next(); 
        if (again.equalsIgnoreCase("no")) {
            keepPlaying = false; 
            System.out.println("Good Game :)");
        }
        else {
            //reset the deck to play again 
            cards = new Deck();
            //clear the players hand 
            p.clearHand();             
       }
    }

    //assigns payout based on hand 
    private void assignPayout(String handType) {
        if (handType.equals("Royal Flush")) {
            payout = 250; 
        }
        else if (handType.equals("Straight Flush")) {
            payout = 50;
        }
        else if (handType.equals("Four of a Kind")) {
            payout = 25;
        }
        else if (handType.equals("Full House")) {
            payout = 6; 
        }
        else if (handType.equals("Flush")) {
            payout = 5;
        }
        else if (handType.equals("Straight")) {
            payout = 4; 
        }
        else if (handType.equals("Three of a Kind")) {
            payout = 3; 
        }
        else if (handType.equals("Two Pairs")) {
            payout = 2; 
        }
        else if (handType.equals("One Pair")) {
            payout = 1; 
        }
        else {
            payout = 0; 
        }
    }
   
    //determines the players hand 
	public String checkHand(ArrayList<Card> hand){
        //sort the hand 
        Collections.sort(hand); 
        
        //evaluate the hand 
        if (isRoyalFlush(hand)) { 
            return "Royal Flush";
        } 
        else if (isStraightFlush(hand)) { 
            return "Straight Flush";
        }
        else if (isFourOfKind(hand)) {
            return "Four of a Kind";
        }
        else if (isFullHouse(hand)) {
            return "Full House";
        }
        else if (isFlush(hand)) {
            return "Flush";
        } 
        else if (isStraight(hand)) {
            return "Straight";
        }
        else if (isThreeOfKind(hand)) {
            return "Three of a Kind";
        }
        else if (isTwoPair(hand)) { 
            return "Two Pairs";
        }
        else if (isOnePair(hand)) {
            return "One Pair";
        }
        else {
            return "Nothing. High Card is: " + getHighCard(hand);  
        }
	}
   
    //returns true if the hand is a royal flush 
    private boolean isRoyalFlush(ArrayList<Card> hand){
        return (isRoyal(hand) && isFlush(hand));   
    }
	
    //returns true if the hand is a straight flush 
     private boolean isStraightFlush(ArrayList<Card> hand) {
        return (isStraight(hand) && isFlush(hand));      
    }
    
    //returns true if the hand is a four of a kind 
    private boolean isFourOfKind(ArrayList<Card> hand) {
        return (isFirstFour(hand) || isLastFour(hand));
    }
    
    //returns true if hand is a full house 
    private boolean isFullHouse(ArrayList<Card> hand) {
        return ((hasBeginningTriple(hand) && hasEndPair(hand)) ||
                (hasEndTriple(hand) && hasBeginningPair(hand)));
    }
    
    //returns true if the hand is a flush  
    private boolean isFlush(ArrayList<Card> hand) {
            Card c = hand.get(0);
            int suit = c.getSuit();
        for (int i = 1; i < hand.size(); i++) {
            Card a = hand.get(i);
            if (suit != a.getSuit()) {
                return false; 
            }
        }
        return true; 
    }
    
    //returns true if the hand is a straight
    private boolean isStraight(ArrayList<Card> hand){
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() + 1 != hand.get(i+1).getRank()) {
                return (isRoyal(hand) || false); 
            }
        }
        return (isRoyal(hand) || true); 
    }
    
    //returns true if the hand is a three of a kind 
    private boolean isThreeOfKind(ArrayList<Card> hand) {
        return (hasBeginningTriple(hand) || hasEndTriple(hand) ||
                hasMiddleTriple(hand));
    }
    
    //returns true if the hand has a two pair
    private boolean isTwoPair(ArrayList<Card> hand) {
        return ((hasBeginningPair(hand) && hasSecondMidPair(hand)) ||
            (hasBeginningPair(hand) && hasEndPair(hand)) || 
            (hasFirstMidPair(hand) && hasEndPair(hand)));
    }
    
    //returns true if the hand has one pair 
    private boolean isOnePair(ArrayList<Card> hand) {
        return (hasBeginningPair(hand) || hasFirstMidPair(hand) || 
            hasSecondMidPair(hand) || hasEndPair(hand));
    }
   
    //returns the highest card in a sorted hand 
    private Card getHighCard(ArrayList<Card> hand) {
        //if the hand contains an Ace, return that 
        if (hand.get(0).getRank() == 1) {
            return hand.get(0);
        } 
        //otherwise, the highest card will be the last
        else {
            return hand.get(4); 
        }
    }
    
    //helper methods 
    
    //returns true for special case of royal ranks
    private boolean isRoyal(ArrayList<Card> hand) {
        int first = hand.get(0).getRank(); 
        int second = hand.get(1).getRank();
        int third = hand.get(2).getRank();
        int fourth = hand.get(3).getRank();
        int fifth = hand.get(4).getRank();
        
        return (first == 1 && second == 10 && third == 11 && fourth == 12
               && fifth == 13);
    }
    
    //returns true if first four cards have same rank
    private boolean isFirstFour(ArrayList<Card> hand) {
        for (int i = 0; i < 3; i++) {
            if (hand.get(i).getRank() != hand.get(i +1).getRank()) {
                return false; 
            }
        }
        return true; 
    }
    
    //returns true if last four cards have same rank 
    private boolean isLastFour(ArrayList<Card> hand) {
        for (int i = 1; i < 4; i++) {
           if (hand.get(i).getRank() != hand.get(i +1).getRank()) {
                return false; 
            }
        }
        return true; 
    }
    
    //returns true if first three cards have same rank
    private boolean hasBeginningTriple(ArrayList<Card> hand) {
        for (int i = 0; i < 2; i++) {
            if (hand.get(i).getRank() != hand.get(i + 1).getRank()) {
                return false; 
            }
        }
        return true; 
    }
    
    //returns true if last three cards have same rank 
    private boolean hasEndTriple(ArrayList<Card> hand) {
        for (int i = 2; i < hand.size() -1; i++) {
            if (hand.get(i).getRank() != hand.get(i + 1).getRank()) {
                return false; 
            }
        }
        return true; 
    }
    //returns true if middle three cards have same rank
    private boolean hasMiddleTriple(ArrayList<Card> hand) {
        for (int i = 1; i < 3; i++) {
            if (hand.get(i).getRank() != hand.get(i + 1).getRank()) {
                return false;
            }
        }
        return true;
    }

    //returns true if first two cards have same rank 
    private boolean hasBeginningPair(ArrayList<Card> hand) {
        return (hand.get(0).getRank() == hand.get(1).getRank());
    }
    
    //returns true if last two cards have same rank 
    private boolean hasEndPair(ArrayList<Card> hand) {
        return (hand.get(3).getRank() == hand.get(4).getRank());
    }
    
    //returns true if first middle pair has same rank
    private boolean hasFirstMidPair(ArrayList<Card> hand) {
        return (hand.get(1).getRank() == hand.get(2).getRank());
    }
    
    //returns true if second middle pair has same rank 
    private boolean hasSecondMidPair(ArrayList<Card> hand) {
        return (hand.get(2).getRank() == hand.get(3).getRank());
    }
    
}