/* 
 * @Class Name: Deck.java
 * 
 * This class simulates a deck of cards.  It contains an array of 
 * cards and a pointer to track the top of the deck.  It contains
 * methods to shuffle the deck, and to deal a card
 *  
 * 
 * @Name: Talya Koschitzky
 * @UNI: tk2892
 * @Date: 06/25/2020
 * */

public class Deck {
	
	private Card[] cards;
	private int top; //the index of the top of the deck 
    private final int HIGHESTINDEX = 51; //top of a full deck
   
	public Deck(){
        //There are 52 cards in a deck 
        cards = new Card[52];
 
        //populate the cards array  
        int index = 0; 
        //outer loop to populate the suit
        for (int i = 1; i < 5; i++) {
            //inner loop to populate the rank
            for (int j = 1; j < 14; j++) {
                cards[index] = new Card(i, j);
                index++; 
            }
        }
        //instantiate top 
        top = HIGHESTINDEX; 
    }
     
	public void shuffle(){
        //the deck is shuffled by swapping two cards 300 times
        for (int i = 0; i < 300; i++) {
            //generate two random numbers between 0 and 51
            int first = (int) (Math.random() * 52); 
            int second = (int) (Math.random() * 52);
            
            //swap the two cards at those indices 
            swap(first, second); 
        }  
	}
    
	public Card deal(){
		//deal the top card in the deck
        Card c = cards[top];
        top--; 
        return c; 
	}
    
    //this helper method swaps two cards given indices
    private void swap(int first, int second) {
        Card temp = cards[first]; 
        cards[first] = cards[second];
        cards[second] = temp; 
    }
}
