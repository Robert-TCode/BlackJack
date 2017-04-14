package blackjack;

import blackjack.Card.Ranks;
import blackjack.Card.Suits;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Robert Tanase
 */
public class DeckOfCards {
    private final List<Card> Cards = new ArrayList<Card>();
    private int index = -1;

    public DeckOfCards() {
        for (Suits s : Suits.values()) {
            for (Ranks r : Ranks.values()) {
                Card c = new Card(s,r);
                Cards.add(c);
            }  
        }
    }  
    public void Shuffle() {
        Collections.shuffle(this.Cards); 
    }
    
    public Card dealCard(){
        index++;
        return Cards.get(index);
    }
    
    public Card dealSpecialCard() {
        return Cards.get(index+1);
    }
}
