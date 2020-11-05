/* 
 * @Class Name: Card.java
 * 
 * This class simulates a card object.  The card can be constructed with a string
 * input, for command line testing purposes, or by inputting the suit and rank 
 * of the card as the explicit parameters. The Card class implements the comparable 
 * interface, and has a toString method that allows for easy printing
 * 
 * 
 * @Name: Talya Koschitzky
 * @UNI: tk2892
 * @Date: 06/25/2020
 * */

public class Card implements Comparable<Card>{
	
	private int suit; //use integers 1-4 to encode the suit
	private int rank; //use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//make a card with suit s and value v
        suit = s; 
        rank = r;
	}
   
	
    //Card constructor that takes in a string
    //for command line testing purposes 
    public Card(String str){
        String suitName = str.substring(0,1);
        if (suitName.equals("c")) {
            suit = 1;
        }
        else if (suitName.equals("d")) {
            suit = 2;
        }
        else if (suitName.equals("h")) {
            suit = 3;
        }
        else if (suitName.equals("s")) {
            suit = 4; 
        }
        String rankNum = str.substring(1); 
        rank = Integer.parseInt(rankNum); 
    }
    
    
    //Compares cards by rank, and if
    //rank is the same, compares by suit
    //Suits are ranked in alphabetical order
	public int compareTo(Card c){
         
        int answer; 
        if (this.rank > c.rank) {
            answer = 1; 
        } 
        else if (c.rank > this.rank) {
            answer = -1;
        }
        else {
            if (this.suit > c.suit) {
                answer = 1; 
            } 
            else if (c.suit > this.suit) {
               answer = -1; 
            }
            else {
                answer = 0;
            }
        }
        return answer; 
	}
	
    //makes Card objects easily printable 
	public String toString(){
        String card = "";
        if (rank == 1 || rank == 11 || rank == 12 || rank == 13) {
            card += rankName() + " of ";
        }
        else {
            card += rank + " of ";
        }
        card += suitName();
        return card; 
	}
    
    //accessor method for suit
    public int getSuit() {
        return suit; 
    }
    
    //accessor method for rank
    public int getRank() {
        return rank; 
    }
    
    //converts suit number to name for readability
    private String suitName() {
        if (suit == 1) {
            return "Clubs";
        }
        else if (suit == 2) {
            return "Diamonds";
        }
        else if (suit == 3) {
            return "Hearts";
        }
        else if (suit == 4) {
            return "Diamonds";
        }
        else return "Error";
    }
        
    //converts special ranks to name for readablity
    private String rankName() {
        if (rank == 1) {
            return "Ace";
        }
        else if (rank == 11) {
            return "Jack";
        }
        else if (rank == 12) {
            return "Queen";
        }
        else if (rank == 13) {
            return "King";
        }
        else return "Error";
    }     
}
    
    


