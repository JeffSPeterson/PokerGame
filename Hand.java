package peterson;

public class Hand {

	Card[] hand;

	public Hand(Card[] c) {

		hand = c;
	}

	public Card getCard(int a) {

			Card card = hand[a];
			return card;
	}

}
