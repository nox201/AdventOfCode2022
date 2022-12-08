import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class main {

	public static void main(String[] args) throws IOException {
		
		//DEBUG PROGRAM START
		System.out.println("Program started");
		
		//CREATE CONNECTION
		Connection connection = Jsoup.connect("https://adventofcode.com/2022/day/2/input");
		//SET COOKIE - SESSION ID REMOVED FOR SECURITY
		connection.cookie("session", "x");
		
		//SCRAPE WEBPAGE - GET LIST OF MATCHES
		Response response = connection.execute();
		
		//EXPLODE RESPONSE ON NEW LINE
		String[] matches = response.body().split("\n");
		
		//INITALIZE VARIABLES
		Character rock = 'X';
		Character paper = 'Y';
		Character scissors = 'Z';
		Character oppRock = 'A';
		Character oppPaper = 'B';
		Character oppScissors = 'C';
		
		//MOVE COMPARISIONS FOR REFERENCE
		//feels like I could determine more from these differences?
		/*
		rock draw -23
		rock win -24
		rock lost -25
		paper lost -22
		paper draw -23
		paper win -24
		scissors win -21
		scissors lost -22
		scissors draw -23
		*/
		
		//INITALIZE TOTAL
		int total = 0;
		int matchScore = 0;
		int myScore = 0;
		
		//ITERATE MATCHES
		for(int i = 0; i < matches.length; i++) {
			
			//RESET SCORES
			matchScore = 0;
			myScore = 0;
			
			//GET OPPONENTS MOVE
			Character oppMove = matches[i].charAt(0);
			//System.out.print(oppMove);
			//GET MY MOVE
			Character myMove = matches[i].charAt(2);
			//System.out.print(myMove);
			
			//MAKE CHAR COMPARISON
			int difference = oppMove.compareTo(myMove);
			
			//SWITCH DIFFERENCE
			switch(difference) {
				case -23:
					//DRAW
					matchScore = 3;
				break;
				case -21:
				case -24:
					//I WON
					matchScore = 6;
				break;
				case -22:
				case -25:
					//I LOST
					matchScore = 0;
				break;
			}
			
			//SWITCH MY MOVE
			switch(myMove) {
				case 'X':
					myScore = 1;
				break;
				case 'Y':
					myScore = 2;
				break;
				case 'Z':
					myScore = 3;
				break;
			}
			
			//DEBUG
			System.out.println(matches[i] + " : " + oppMove.compareTo(myMove) + " : " + myScore + " : " + matchScore);
			//DEBUG
			System.out.println(total);
			//TALLY AND STORE TOTAL
			total += (myScore + matchScore);
			
		}
		
		//OUTPUT SOLUTION
		System.out.println("The final score is " + String.valueOf(total));

	}

}