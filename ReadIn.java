package peterson;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadIn {

	public final static int TEN = 10;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;
	public final static int ACE = 14;
	public final static int SPADE = 20;
	public final static int HEART = 21;
	public final static int DIAMOND = 22;
	public final static int CLUB = 23;

	Scanner scan = new Scanner(new File("hands.txt"));
	String str;
	int d, s, playerOneWins, playerTwoWins, count = 0;
	Card card;
	Card[] hand1 = new Card[5];
	Card[] hand2 = new Card[5];
	CheckHand check = new CheckHand();

	public ReadIn() throws IOException {

		// READ IN FILE
		while (scan.hasNext()) {

			str = scan.next();

			// IF 10, J, K, Q, A.....
			if (str.substring(0, 1).equals("T"))
				d = TEN;
			else if (str.substring(0, 1).equals("J"))
				d = JACK;
			else if (str.substring(0, 1).equals("Q"))
				d = QUEEN;
			else if (str.substring(0, 1).equals("K"))
				d = KING;
			else if (str.substring(0, 1).equals("A"))
				d = ACE;
			else
				d = Integer.parseInt(str.substring(0, 1));

			// TURNS SUIT INTO INT
			if (str.substring(1).equals("S"))
				s = SPADE;
			else if (str.substring(1).equals("H"))
				s = HEART;
			else if (str.substring(1).equals("D"))
				s = DIAMOND;
			else
				s = CLUB;

			// NEW CARD FROM DIGIT:SUIT INPUT
			card = new Card(d, s);

			// SEPARATE CARDS INTO TWO HANDS
			if (count < 5) {
				hand1[count] = card;
			} else if (count >= 5 && count < 10) {
				hand2[count % 5] = card;
			}
			count++;

			// RESET COUNT TO 0 TO GET NEXT SET OF HANDS
			if (count == 10) {
				// CHECK HAND TO SEE WHO WON
				if (check.checkHand(hand1) > check.checkHand(hand2))
					playerOneWins++;
				else
					playerTwoWins++;

				count = 0;
			}
		}

		// OUTPUT OF WINNER
		System.out.println("Player One Has: " + playerOneWins + "\n"
				+ "Player Two Has: " + playerTwoWins);
		
		if (playerOneWins > playerTwoWins)
			System.out.println("Player One Wins!!!");
		else
			System.out.println("Player Two Wins!!!");

		// CLOSE SCANNER
		scan.close();
	}
}
