import java.util.List;
import java.util.Arrays;

/**
 * A class representing a single playing card
 *
 * @author Mark Young (A00000000)
 */
public class Card implements Comparable<Card> {

    public final static int SPADES = 0,    // Codes for the 4 suits.
                            HEARTS = 1,
                            DIAMONDS = 2,
                            CLUBS = 3;
                            
    public final static int ACE = 1,    // Codes for the non-numeric cards.
                            JACK = 11,  //   Cards 2 through 10 have their 
                            QUEEN = 12, //   numerical values for their codes.
                            KING = 13;

    /** a list of all the suits */
    public final static List<Integer> SUITS = 
        Arrays.asList(SPADES, HEARTS, DIAMONDS, CLUBS);

    /** a list of all the ranks */
    public final static List<Integer> RANKS =
        Arrays.asList(ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10, JACK, QUEEN, KING);
                            
    private final int suit;   // The suit of this card, one of the constants
                              //    SPADES, HEARTS, DIAMONDS, CLUBS.
                              
    private final int rank;  // The rank of this card, from 1 to 13.
                             
    /**
     * Create a single playing card.
     *
     * @param theRank   the rank (1 to 13) of this playing card
     * @param theSuit   the suit (0 to 3) of this playing card
     */
    public Card(int theRank, int theSuit) {
            // Construct a card with the specified rank and suit.
            // Rank must be between 1 and 13.  Suit must be between
            // 0 and 3.  If the parameters are outside these ranges,
            // the constructed card object will be invalid.
        mustBeRank(theRank);
        mustBeSuit(theSuit);
        rank = theRank;
        suit = theSuit;
    }
        
    /**
     * The suit of this card.
     *
     * @return the suit (0 to 3) of this card 
     */
    public int getSuit() {
            // Return the int that codes for this card's suit.
        return suit;
    }
    
    /**
     * The rank of this card.
     *
     * @return the rank (1 TO 13) of this card 
     */
    public int getRank() {
            // Return the int that codes for this card's rank.
        return rank;
    }
    
    /**
     * The name of this card's suit.
     *
     * @return the English name of the suit of this card 
     */
    public String getSuitAsString() {
        switch ( suit ) {
           case SPADES:   return "Spades";
           case HEARTS:   return "Hearts";
           case DIAMONDS: return "Diamonds";
           case CLUBS:    return "Clubs";
           // THE DEFAULT CASE SHOULD NEVER HAPPEN!
           default:       throw new IllegalStateException();
        }
    }
    
    /**
     * The first letter of this card's suit name.
     *
     * @return the first letter of the English name of this card's suit 
     */
    public String getSuitAbbreviation() {
        return getSuitAsString().substring(0, 1);
    }

    /**
     * Return the name of this card's rank.
     * 
     * @return a String representing the rank of this card 
     */
    public String getRankAsString() {
        switch ( rank ) {
           case 1:   return "Ace";
           case 2:   return "2";
           case 3:   return "3";
           case 4:   return "4";
           case 5:   return "5";
           case 6:   return "6";
           case 7:   return "7";
           case 8:   return "8";
           case 9:   return "9";
           case 10:  return "10";
           case 11:  return "Jack";
           case 12:  return "Queen";
           case 13:  return "King";
           // THE DEFAULT CASE SHOULD NEVER HAPPEN!
           default:  throw new IllegalStateException();
        }
    }

    /**
     * Return the rank of this card abbreviated to one character (or two
     * characters, for the 10).
     *
     * @return the one or two character abbreviation of this card's rank 
     */
    public String getRankAbbreviation() {
        String rankString = getRankAsString();
        // only the named ranks have length greater than two
        if (rankString.length() > 2) {
            // in which case we return their first lettter (A, K, Q, J)
            return rankString.substring(0, 1);
        }
        return rankString;
    }
    
    /**
     * Return the short version of this Card's identity -- its rank and suit
     * abbreviated to one letter each (or two letters, for the 10.
     *
     * @return a String representing this card (abbreviated) 
     */
    public String toString() {
        return getRankAbbreviation() + getSuitAbbreviation();
    }

    /** 
     * compare this card to another card
     *
     * @param otherCard the card to compare to
     * @return a negative/zero/positive number depending on whether this card
     *         is before/equal/after the other
     */
    public int compareTo(Card otherCard) {
        int myValue = getValue();
        int itsValue = otherCard.getValue();
        return myValue - itsValue;
    }

    /**
     * Return a number representing this card's position in a sorted deck.
     * (Used, of course, in the compareTo method.)
     *
     * @return a single number (1 to 52) representing this card 
     */
    private int getValue() {
        // There are two ways to sort cards
        //  -- by suit:  all cards in the same suit are put together
        //               and then sorted within the suit from low to high
        //  -- by rank:  all cards of the same rank are put together
        //               and by an arbitrary suit order within rank
        // We should have a static method or class variables to tell us
        // which way to sort the cards.  For now, we'll just do suit order.

        // Suit ordered
        return suit * RANKS.size() + rank;

        // Rank-ordered
        // return rank * SUITS.size() + suit;
    }

    /**
     * Throw an exception if the argument isn't a rank.
     *
     * @param value the value to check
     */
    private static void mustBeRank(int value) {
        if (!Card.RANKS.contains(value)) {
            throw new IllegalArgumentException("Not a rank: " + value);
        }
    }


    /**
     * Throw an exception if the argument isn't a suit.
     *
     * @param value the value to check
     */
    private static void mustBeSuit(int value) {
        if (!Card.SUITS.contains(value)) {
            throw new IllegalArgumentException("Not a suit: " + value);
        }
    }



} // end class Card