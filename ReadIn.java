package peterson;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadIn {

	public final static int ACE = 1;
	public final static int TEN = 10;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;
	public final static int SPADE = 20;
	public final static int HEART = 21;
	public final static int DIAMOND = 22;
	public final static int CLUB = 23;

	Scanner scan = new Scanner(new File("hands.txt"));
	String str;
	int d, s, count = 0;
	Card[] cards = new Card[5];
	Card card;
	Hand p1Hand;
	Hand p2Hand;

	public ReadIn() throws IOException {

		// ////////READ IN FILE//////////////////////////
		while (scan.hasNext()) {

			str = scan.next();
			// ///////// IF 10, J, K, Q, A TURN INTO
			// INTO/////////////////////////
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

			// ///////// TURN SUIT INTO INT ///////////////
			if (str.substring(1).equals("S"))
				s = SPADE;
			else if (str.substring(1).equals("H"))
				s = HEART;
			else if (str.substring(1).equals("D"))
				s = DIAMOND;
			else
				s = CLUB;

			// NEW CARD FROM INPUT
			card = new Card(d, s);
			
			System.out.println(card.digit + " " + card.suit);
		
		}
		scan.close();
	}
}
