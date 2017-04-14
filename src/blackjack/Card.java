package blackjack;

import static blackjack.Hand.cardValue;

/**
 *
 * @author Robert Tanase
 */
public class Card {
    public static enum Suits {
        Spades, 
        Hearts, 
        Diamonds,
        Clubs;  
    };
    
    public static enum Ranks {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;
    };
    
    private Suits suit;
    private Ranks rank;

    public Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public String getRank(){
        String StringRank = rank.name();
        return StringRank;
    }
    
    @Override
    public String toString() {
        String result = "";
        if (this.rank.name() == "JACK") {
            result += "J";
        }
        if (this.rank.name() == "QUEEN") {
            result += "Q";
        }
        if (this.rank.name() == "KING") {
            result += "K";
        }
        if (this.rank.name() == "ACE") {
            result += "Ace";
        }
        if (result == "") {
            result += cardValue.get(this.getRank());
        }
        
        result += " ";
        result += this.suit.name();
        return result;
    }
}
