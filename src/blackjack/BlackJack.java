package blackjack;

import java.util.Scanner;

/**
 *
 * @author Robert Tanase
 */
public class BlackJack {
    
       public static void main(String[] args) {
           
          int money  = 500;         
          int bet;           
          boolean userWins;  
          Scanner sc = new Scanner(System.in);
          
          System.out.println("Welcome to the game of Blackjack. Let's have some fun!");
          System.out.println();
       
          while (true) {
              System.out.print("Well, you have " + money + " Euro. ");
              do {
                 System.out.print("How many Euro do you want to bet? (Or enter 0 to exit the game) ");
                 bet = sc.nextInt();
                 if (bet < 0 || bet > money)
                     System.out.println("The bet must be between 0 and " + money + '.');
              } while (bet < 0 || bet > money);
              
              if (bet == 0)
                 break;
              
              if (money >= 2000){
                  System.out.println("You are really good! You should go and try your luck in a casino, seriously.");
              }
              userWins = Play();
              
              if (userWins)
                 money = money + bet;
              else
                 money = money - bet;
              
              System.out.println();
              
              if (money == 0) {
                 System.out.println("Unfortunately, you lost all the money. The game stops here.");
                 break;
              }
              
          }
          
          System.out.println();
          System.out.println("You have €" + money + '.');
       
       } 
       
       
       static boolean Play() {
           
          Scanner sc = new Scanner(System.in);
          DeckOfCards deck;  // A new deck for each game.
          Hand dealerHand;   
          Hand userHand;   
          deck = new DeckOfCards();
          dealerHand = new Hand();
          userHand = new Hand();
          dealerHand.setAsDealer();
    
          System.out.println();
          System.out.println("Initial Draw:");
          deck.Shuffle();
          dealerHand.addCard( deck.dealCard() );
          dealerHand.addCard( deck.dealCard() );
          userHand.addCard( deck.dealCard() , deck.dealSpecialCard());
          userHand.addCard( deck.dealCard() );
          
          if (dealerHand.getHandValue() == 21) {
               System.out.println("Dealer has the " + dealerHand.getCard(0) + " and the " + dealerHand.getCard(1) + ".");
               System.out.println("You have the " + userHand.getCard(0) + " and the " + userHand.getCard(1) + ".\n");
               System.out.println("Dealer has Blackjack.  Dealer WINS!!!");
               return false;
          }
          
          if (userHand.getHandValue() == 21) {
               System.out.println("Dealer has the " + dealerHand.getCard(0) + " and the " + dealerHand.getCard(1) + ".");
               System.out.println("You have the " + userHand.getCard(0) + " and the " + userHand.getCard(1) + ".\n");
               System.out.println("You have Blackjack.  You WIN!!!");
               return true;
          }
          
          while (true) {
               System.out.print("Your hand:      ");
               for ( int i = 0; i < userHand.getCardCount(); i++ ) {
                  System.out.print(userHand.getCard(i));
                  if (i < userHand.getCardCount()-1) {
                      System.out.print(", ");
                  }
               }
               
               System.out.println();
               System.out.print("Dealer's hand:  " + dealerHand.getCard(0) + ", Hidden");
               System.out.println();
               System.out.println("Your total is " + userHand.getHandValue() + ".");
               if (userHand.getHandValue() == 21) {
                   break;
               }
               System.out.print("Do you want to draw another card? (Y/N) ");
               char answer;
               
               do {
                  answer = Character.toUpperCase( sc.next().charAt(0));
                  if (answer != 'Y' && answer != 'N'){
                     System.out.print("Answer with Y or N:  ");
                  }
               } while (answer != 'Y' && answer != 'N');
    
    
               if ( answer == 'N' ) {
                   break;
               } else {  
                   Card newCard = deck.dealCard();
                   System.out.println();
                   userHand.addCard(newCard);
                   System.out.println("Your new card is the " + newCard + ".\n");
                   if (userHand.getHandValue() > 21) {
                       System.out.println();
                       System.out.println("You busted by going over 21.  Dealer WINS!!!");
                       System.out.println("Dealer's other card was the " + dealerHand.getCard(1));
                       return false;  
                   }
               }
          } 
    
          System.out.println();
          System.out.println("Dealer Hidden card was: " + dealerHand.getCard(1));
          
          while (dealerHand.getHandValue() <= 16) {
             Card newCard = deck.dealCard();
             System.out.println("Dealer hits and gets the " + newCard);
             dealerHand.addCard(newCard);
             if (dealerHand.getHandValue() > 21) {
                System.out.println();
                System.out.println("Dealer busted by going over 21.  You WIN!!!");
                return true;
             }
          }
          System.out.println("Dealer's total is " + dealerHand.getHandValue());
          
          System.out.println();
          if (dealerHand.getHandValue() == userHand.getHandValue()) {
             System.out.println("Dealer wins.  You lose.");
             return false;
          }
          else if (dealerHand.getHandValue() > userHand.getHandValue()) {
             System.out.println("Dealer WINS!!! " + dealerHand.getHandValue() + " points against to " + userHand.getHandValue() + "points");
             return false;
          }
          else {
             System.out.println("You WIN!!! " + userHand.getHandValue() + " points against to " + dealerHand.getHandValue() + " points");
             return true;
          }
    
       } 
   
}
