
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A deck class which represents a standard 52 card pile using Lists
 *
 * @author Md Ishfaq Alam
 */
public class Deck {

    public final List<Card> deck = new ArrayList<>();

    /**
     * A constructor which create a standard deck of cards
     */
    public Deck() {
      
        for (int r = 1; r < 14; ++r) {
            for (int s = 0; s < 4; ++s) {
                Card cards = new Card(r, s);
                deck.add(cards);
            }

        }

    }

    /**
     * a method to return cards left
     *
     * @return the number of cards left in the deck
     */
    public int cardsLeft() {
        return deck.size();
    }

    /**
     *
     * @return the top card of the deck (which is removed from the deck)
     */
    public Card takeTopCard() {
        Card top = deck.get(0);
        deck.remove(top);
        return top;

    }

    /**
     * shuffle the deck
     */
    public void shuffle() {
        System.out.println(deck.size());
        Collections.shuffle(deck);
    }

    /**
     * deal the given number of cards from the deck
     *
     * @param numCards the number of cards to be dealt
     * @return a sorted list of cards (which are removed from the deck)
     */
    public List<Card> deal(int numCards) {

        List<Card> cardsRemovedFromDeck = new ArrayList<>();
        for (int i = 0; i < numCards; ++i) {

            cardsRemovedFromDeck.add(takeTopCard());

        }
        System.out.println(deck.size());

        Collections.sort(cardsRemovedFromDeck);
        return cardsRemovedFromDeck;

    }

    /**
     * add cards back into the deck (at the bottom)
     *
     * @param cards a list of the cards to be returned to the deck
     */
    public void returnCards(List<Card> cards) {

        for (int i = 0; i < cards.size(); ++i) {
            deck.add(deck.size(), cards.get(i));

        }

    }

    /**
     * add cards back into the deck and shuffle the resulting deck
     *
     * @param cards a list of the cards to be returned to the deck
     */
    public void shuffleIn(List<Card> cards) {
        
        returnCards(cards);
        Collections.shuffle(deck);

    }

    /**
     * add another deck to this one and shuffle the resulting deck
     *
     * @param otherDeck the deck to be added to this one
     */
    public void shuffleIn(Deck otherDeck) {
        //System.out.println(deck.size());
        
        deck.addAll(otherDeck.deck);

        Collections.shuffle(deck);

    }

}
