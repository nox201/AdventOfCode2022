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
		Character oppRock = 'A';
		Character oppPaper = 'B';
		Character oppScissors = 'C';
		Character lose = 'X';
		Character draw = 'Y';
		Character win = 'Z';
		
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
			Character desiredOutcome = matches[i].charAt(2);
			//System.out.print(myMove);
			
			//SWITCH MY MOVE
			//feels like this could be done with an associative array and cycling through the keys
			//but i am sick. *sniffles*
			switch(desiredOutcome) {
				case 'X':
					//LOSE
					matchScore = 0;
					//DETERMINE MY SCORE
					switch(oppMove) {
						case 'A':
							myScore = 3;
						break;
						case 'B':
							myScore = 1;
						break;
						case 'C':
							myScore = 2;
						break;
					}
				break;
				case 'Y':
					//DRAW
					matchScore = 3;
					//DETERMINE MY SCORE
					switch(oppMove) {
						case 'A':
							myScore = 1;
						break;
						case 'B':
							myScore = 2;
						break;
						case 'C':
							myScore = 3;
						break;
					}
				break;
				case 'Z':
					//WIN
					matchScore = 6;
					//DETERMINE MY SCORE
					switch(oppMove) {
						case 'A':
							myScore = 2;
						break;
						case 'B':
							myScore = 3;
						break;
						case 'C':
							myScore = 1;
						break;
					}
				break;
			}
			
			//DEBUG
			System.out.println(matches[i] + " : " + myScore + " : " + matchScore);
			//DEBUG
			System.out.println(total);
			//TALLY AND STORE TOTAL
			total += (myScore + matchScore);
			
		}
		
		//OUTPUT SOLUTION
		System.out.println("The final score is " + String.valueOf(total));

	}

}