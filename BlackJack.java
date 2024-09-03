import java.util.Random;
import java.util.Scanner;


public class BlackJack {
  /*private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs","Spades" };
  private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9",
      "10", "Jack", "Queen", "King","Ace" };
  private static final int[] DECK = new int[52];*/

  private static Card[] cards = new Card[52];

  private static int currentCardIndex = 0;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean turn = true;
    String playerDecision = "" ;
    //added a for loop so the game can be played multiple times
    //for (int i = 0; i < turn; i++) {
    while(turn) {
      initializeDeck();
      //shuffleDeck();
      int playerTotal = 0;
      int dealerTotal = 0;
      playerTotal = dealInitialPlayerCards();

      //fix dealInitialDealerCards
      dealerTotal = dealInitialDealerCards();

      //fix playerTurn
      playerTotal = playerTurn(scanner, playerTotal);
      if (playerTotal > 21) {
        System.out.println("You busted! Dealer wins.");
        return;
      }

      //fix dealerTurn
      dealerTotal = dealerTurn(dealerTotal);
      // detirmines winner
      //fix determine winner
      determineWinner(playerTotal, dealerTotal);
      //added
      //asks player if they want to play again
      System.out.println("Would you like to play another hand?");
      //added
      //player input
      playerDecision = scanner.nextLine().toLowerCase();
      //added
      //check for either a yes or no answer
      //for (int k = 0 ; k < 3 ; k++) {
      while(!(playerDecision.equals("no") || (playerDecision.equals("yes")) )){
        /*if (PlayerDecision.equals("no")) {
          turn = 0;
          i = 10;
          k = 10;
        }else if (PlayerDecision.equals("yes")) {
          k = 10;
          turn++;
        } else {*/
        System.out.println("Invalid action. Please type 'hit' or 'stand'.");
        //k = 0;
        playerDecision = scanner.nextLine().toLowerCase();
      }
      if (playerDecision.equals("no"))
          turn = false;

    }
    System.out.println("Thanks for playing!");
  }



  // algorithm to create deck
  private static void initializeDeck() {
    //for (int i = 0; i < DECK.length; i++) {
    String[] SUITS = { "Hearts", "Diamonds", "Clubs","Spades" };
    String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9","10", "Jack", "Queen", "King","Ace" };
    int suitsIndex = 0, rankIndex = 0;
    for (int i = 0; i < cards.length; i++) {
      //DECK[i] = i;
      //public Card(int value, String suit, String rank) {
      cards[i] = new Card( Integer.parsInt(RANKS[rankIndex]), SUITS[suitIndex], RANKS[rankIndex]);
      suitIndex++;
      if (suitIndex == 4) {
        suitIndex = 0;
        rankIndex++;
      }
    }
  }
  // algorithm to shuffle deck
  private static void shuffleDeck() {
    Random random = new Random();
    for (int i = 0; i < DECK.length; i++) {
      int index = random.nextInt(DECK.length);
      int temp = DECK[i];
      DECK[i] = DECK[index];
      DECK[index] = temp;
    }
  }
  // algorithm to deal initial player cards
  private static int dealInitialPlayerCards() {
    /*int card1 = dealCard();
    int card2 = dealCard();*/
    Card card1 = dealCard();
    Card card2 = dealCard();
    
    //System.out.println("Your cards: " + RANKS[card1] + " of " + SUITS[card1 / 13] + " and " + RANKS[card2] + " of " + SUITS[card2 / 13]);
    System.out.println("Your cards: " + card1.getRank() + " of " + card1.getSuit() + " and " + card2.getRank() + " of " + card2.getSuit();
        
        
    //return cardValue(card1) + cardValue(card2);
    return card1.getValue() + card2.getValue();
  }
  // alogrithm to deal initial dealer cards
  private static int dealInitialDealerCards() {
    int card1 = dealCard();
    System.out.println("Dealer's card: " + RANKS[card1] + " of " + SUITS[card1 /
                                                                         13]);
    return cardValue(card1);
  }
  private static int playerTurn(Scanner scanner, int playerTotal) {
    while (true) {
      System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
      String action = scanner.nextLine().toLowerCase();
      if (action.equals("hit")) {
        int newCard = dealCard();
        playerTotal += cardValue(newCard);
        System.out.println("You drew a " + RANKS[newCard] + " of " +
            SUITS[newCard / 13]);
        if (playerTotal > 21) {
          //added
          //resets playerTotal so the game can be played multiple times
          System.out.println("you busted Dealer wins!" );
          playerTotal = 0;
          return playerTotal;
        }
      } else if (action.equals("stand")) {
        break;
      } else {
        System.out.println("Invalid action. Please type 'hit' or 'stand'.");
      }
    }
    return playerTotal;
  }
  // algorithm for dealer's turn
  private static int dealerTurn(int dealerTotal) {
    while (dealerTotal < 17) {
      int newCard = dealCard();
      dealerTotal += cardValue(newCard);
    }
    System.out.println("Dealer's total is " + dealerTotal);
    return dealerTotal;
  }
  // algorithm to determine the winner
  private static void determineWinner(int playerTotal, int dealerTotal) {
    if (dealerTotal > 21 || playerTotal > dealerTotal) {
      System.out.println("You win!");
    } else if (dealerTotal == playerTotal) {
      System.out.println("It's a tie!");
    } else {
      System.out.println("Dealer wins!");
      playerTotal = 0;
    }
  }
  // algroithm to deal a card
  //private static int dealCard() {
  private static Card dealCard() {
    //return DECK[currentCardIndex++] % 13;
    return cards[currentCardIndex++];
  }
  // algorithm to determine card value
  private static int cardValue(int card) {
    return card < 9 ? card + 2 : 10;
  }
}