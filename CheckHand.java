package peterson;

import java.util.Arrays;

public class CheckHand {

	public final static int PAIR = 100;
	public final static int TWOPAIR = 200;
	public final static int THREEKIND = 300;
	public final static int STRAIGHT = 400;
	public final static int FLUSH = 500;
	public final static int FULLHOUSE = 600;
	public final static int FOURKIND = 700;


	// TAKES HAND AND RECURSIVELY CHECKS TYPE OF HANDS
	// RETURNS A UNIQUE NUMBER CORESPONDING TO THE VALUE
	public int checkHand(Card[] hand) {
		return Math.max(checkPair(hand), 
				highCard(hand) + flush(hand) + straight(hand));
	}

	// FINDS HIGH CARD
	int highCard(Card[] hand) {
		return Math.max(
				hand[0].digit,
				Math.max(Math.max(hand[1].digit, hand[2].digit),
						Math.max(hand[3].digit, hand[4].digit)));
	}

	// CHECKS FOR FLUSH
	int flush(Card[] hand) {
		if (hand[0].suit == hand[1].suit && hand[1].suit == hand[2].suit
				&& hand[2].suit == hand[3].suit && hand[3].suit == hand[4].suit)
			return FLUSH;
		else
			return 0;
	}

	// CHECKS FOR STRAIGHT
	int straight(Card[] hand) {
		int[] values = new int[5];
		for (int i = 0; i < 5; i++)
			values[i] = hand[i].digit;
		Arrays.sort(values);
		if (values[0] + 1 == values[1] && values[1] + 1 == values[2]
				&& values[2] + 1 == values[3] && values[3] + 1 == values[4])
			return STRAIGHT;
		else
			return 0;
	}

	// CHECKS FOR PAIR
	public int checkPair(Card[] hand) {
		if (hand[0].digit == hand[1].digit)
			return checkThree(hand[0].digit, hand[2].digit, hand[3].digit,
					hand[4].digit);
		else if (hand[0].digit == hand[2].digit)
			return checkThree(hand[0].digit, hand[1].digit, hand[3].digit,
					hand[4].digit);
		else if (hand[0].digit == hand[3].digit)
			return checkThree(hand[0].digit, hand[1].digit, hand[2].digit,
					hand[4].digit);
		else if (hand[0].digit == hand[4].digit)
			return checkThree(hand[0].digit, hand[1].digit, hand[2].digit,
					hand[3].digit);
		else if (hand[1].digit == hand[2].digit)
			return checkThree(hand[1].digit, hand[0].digit, hand[3].digit,
					hand[4].digit);
		else if (hand[1].digit == hand[3].digit)
			return checkThree(hand[1].digit, hand[0].digit, hand[2].digit,
					hand[4].digit);
		else if (hand[1].digit == hand[4].digit)
			return checkThree(hand[1].digit, hand[0].digit, hand[2].digit,
					hand[3].digit);
		else if (hand[2].digit == hand[3].digit)
			return checkThree(hand[2].digit, hand[0].digit, hand[1].digit,
					hand[4].digit);
		else if (hand[2].digit == hand[4].digit)
			return checkThree(hand[2].digit, hand[0].digit, hand[1].digit,
					hand[3].digit);
		else if (hand[3].digit == hand[4].digit)
			return checkThree(hand[3].digit, hand[0].digit, hand[1].digit,
					hand[2].digit);
		else
			return (0);
	}

	// CHECKS IF A FOUND PAIR HAS THREE OF A KIND
	public int checkThree(int pair, int card1, int card2, int card3) {
		if (pair == card1)
			return checkFour(pair, card2, card3);
		else if (pair == card2)
			return checkFour(pair, card1, card3);
		else if (pair == card3)
			return checkFour(pair, card1, card2);
		else
			return checkTwoPair(pair, card1, card2, card3);
	}

	// CHECKS IF A FOUND THREE OF A KIND HAS FOUR OF A KIND
	public int checkFour(int triple, int card1, int card2) {
		if (triple == card1 || triple == card2)
			return (FOURKIND + triple);
		else
			return fullHouse1(triple, card1, card2);
	}

	// CHECKS IF THERE IS A SECOND PAIR
	public int checkTwoPair(int pair, int card1, int card2, int card3) {
		if (card1 == card2)
			return fullHouse2(pair, card1, card3);
		else if (card1 == card3)
			return fullHouse2(pair, card1, card2);
		else if (card2 == card3)
			return fullHouse2(pair, card2, card3);
		else
			return (PAIR + pair);
	}

	// CHECKS IF A FOUND THREE OF A KIND IS A FULL HOUSE
	public int fullHouse1(int triple, int card1, int card2) {
		if (card1 == card2)
			return (FULLHOUSE + triple);
		else
			return (THREEKIND + triple);
	}

	// CHECKS IF A FOUND TWO PAIRS IS A FULL HOUSE
	public int fullHouse2(int pair1, int pair2, int card) {
		if (pair1 == card || pair2 == card)
			return (FULLHOUSE + card);
		else
			return (TWOPAIR + Math.max(pair1, pair2));
	}
}
