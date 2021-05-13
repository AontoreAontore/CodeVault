
import java.util.*;

/**
 * A program to run the Deck class thru its paces.
 *
 * @author Mark Young (A00000000)
 */
public class Game {

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        
        Deck d;
        int numPlayers, numCards, cardsNeeded;

        // introduce this program
        System.out.print("\n\n"
            + "Simple Card Game\n"
            + "================\n\n"
            + "This game just deals cards.\n\n");

        // Announce the first game
        System.out.print("First game\n"
                       + "----------\n\n");

        // get a new deck of cards
        d = new Deck();
        // activate next line after you've got the constructor working properly
         d.shuffle();

        // get game input
        System.out.print("How many players are in the game? ");
        numPlayers = kbd.nextInt(); 
        kbd.nextLine();
        System.out.print("How many cards does each player get? ");
        numCards = kbd.nextInt();   
        kbd.nextLine();
        cardsNeeded = numPlayers * numCards;
        System.out.println();

        // play the game -- if there are enuf cards!
        if (cardsNeeded >= d.cardsLeft()) {
            System.out.println("Not enuf cards to play the first game!");
        }
        else {
            for (int p = 1; p <= numPlayers; p++) {
                List<Card> hand = d.deal(numCards);
                System.out.println("Player " + p + " gets " + hand);
                 
            }
            System.out.println();
            
            System.out.println("Top card from deck: " + d.takeTopCard());
        }
        System.out.println("\n");

        // announce second game
        System.out.print("Second game\n"
                       + "-----------\n\n");

        // get a new deck of cards, and combine it with a second deck
      d = new Deck();
        d.shuffleIn(new Deck());

        // get game input
        System.out.print("How many players are in the game? ");
        numPlayers = kbd.nextInt(); 
        kbd.nextLine();
        System.out.print("How many cards does each player get? ");
        numCards = kbd.nextInt();   
        kbd.nextLine();
        cardsNeeded = numCards;
        System.out.println();

        // play the game -- if there are enuf cards
        if (cardsNeeded > d.cardsLeft()) {
            System.out.println("Not enuf cards to play the second game!");
        }
        else {
            for (int p = 1; p <= numPlayers; p++) {
                List<Card> hand = d.deal(numCards);
                System.out.println("Player " + p + " gets " + hand);
                d.returnCards(hand);
                
            }
            
        }
        System.out.println("\n");
    }
}
