package blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Robert Tanase
 */
public class Hand {
    public static Map cardValue = new HashMap(); 
    private final List<Card> Cards = new ArrayList<Card>();
    private int totalValue;
    private boolean isDealer;
    
    public Hand(){
        totalValue = 0;
        isDealer = false;
        cardValue.put("TWO", 2);
        cardValue.put("THREE", 3);
        cardValue.put("FOUR", 4);
        cardValue.put("FIVE", 5);
        cardValue.put("SIX", 6);
        cardValue.put("SEVEN", 7);
        cardValue.put("EIGHT", 8);
        cardValue.put("NINE", 9);
        cardValue.put("TEN", 10);
        cardValue.put("JACK", 10);
        cardValue.put("QUEEN", 10);
        cardValue.put("KING", 10);
        cardValue.put("ACE", 11);
    }
    
    public void setAsDealer() {
        isDealer = true;
    }
    
    public void addCard(Card c) {
        Cards.add(c);
        if ((int)cardValue.get(c.getRank()) == 11) {
            if (isDealer) {
                    if (totalValue + 11 <= 21 && totalValue >= 6) {
                        totalValue += 11;
                    } else {
                        totalValue += 1;
                    }
            } else {
                if (totalValue + 11 > 21) {
                    totalValue += 1;
                } else {
                    if (totalValue + 11 == 21) {
                        totalValue += 11;
                    } else {
                        this.getAnAce(totalValue);
                    }
                }
            }
        } else {
            totalValue += (int)cardValue.get(c.getRank());
        }
    }
    
    public void addCard(Card c1, Card c2) {
        Cards.add(c1);
        if ((int)cardValue.get(c1.getRank()) == 11) {
            if (totalValue + 11 > 21) {
                totalValue += 1;
            } else {
                if ((int)cardValue.get(c2.getRank())+ 11 == 21) {
                    totalValue += 11;
                } else {
                    int valSec = (int)cardValue.get(c2.getRank());
                    this.getAnAce(valSec);
                }
            }
        } else {
            totalValue += (int)cardValue.get(c1.getRank());
        }
    }
    
    public int getHandValue() {
        return totalValue;
    }
    
    public Card getCard(int index) {
        return Cards.get(index);
    }
    
    public int getCardCount() {
        return Cards.size();
    }
    
    private void getAnAce(int valSecondCard) {
        Scanner sc = new Scanner(System.in);
        if (Cards.size() <= 2)
        System.out.println("You have an Ace and a "+ valSecondCard);
        System.out.println("Do you want it to be considered as 1 or as 11?    (1/11)");
        int value = sc.nextInt();
        if (value == 1) {
            totalValue += 1;
        } else {
            totalValue += 11;
        }
    }
}
