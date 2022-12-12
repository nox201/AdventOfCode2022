import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class main {

	public static void main(String[] args) throws IOException {
		
		//DEBUG PROGRAM START
		System.out.println("Program started");
		
		//CREATE CONNECTION
		Connection connection = Jsoup.connect("https://adventofcode.com/2022/day/3/input");
		//SET COOKIE - SESSION ID REMOVED FOR SECURITY
		connection.cookie("session", "x");
		
		//SCRAPE WEBPAGE - GET LIST OF MATCHES
		Response response = connection.execute();
		
		//EXPLODE RESPONSE ON NEW LINE
		String[] rucksacks = response.body().split("\n");
		
		//INITALIZE VARIABLES
		ArrayList<Character> dupes = new ArrayList<Character>();
		ArrayList<Character> potentialBadges = new ArrayList<Character>();
		//ArrayList<Integer> priorities = new ArrayList<Integer>();
		int total = 0;
		int threes = 0;
		ArrayList<String> threeStrings = new ArrayList<String>();
		
		//ITERATE RUCKSACKS
		for(int i = 0; i < rucksacks.length; i++) {
			
			if(threes != 3) {
				//STORE ITEMS FOR THREE ELVES
				threeStrings.add(rucksacks[i]);
				//INCREMENT COUNTER
				threes++;
			}else {
				
				System.out.println("1: " + threeStrings.get(0));
				System.out.println("2: " + threeStrings.get(1));
				System.out.println("3: " + threeStrings.get(2));
				
				//ITERATE FIRST STRING
				for(int j = 0; j < threeStrings.get(0).length(); j++) {
					//CHECK SECOND STRING
					if(threeStrings.get(1).contains(String.valueOf(threeStrings.get(0).charAt(j)))) {
						//STORE CHAR
						potentialBadges.add(threeStrings.get(0).charAt(j));
						//System.out.println("::" + threeStrings.get(0).charAt(j));
					}
				}
				//ITERATE MATCHES FROM RUCKSACKS 1 AND 2
				for(int k = 0; k < potentialBadges.size(); k++) {
					//CHECK THIRD STRING
					if(threeStrings.get(2).contains(String.valueOf(potentialBadges.get(k)))) {
						//BADGE FOUND
						System.out.println(potentialBadges.get(k));
						dupes.add(potentialBadges.get(k));
						//BREAK LOOP
						break;
					}
				}
				
				//EMPTY DATA
				potentialBadges.clear();
				threeStrings.clear();
				//RESET THREES COUNTER
				threes = 1;
				//STORE CURRENT ITERATION
				threeStrings.add(rucksacks[i]);
			}
			
			
			
		}
		
		//ITERATE DUPES - i would have used a forEach on the arraylist, but that requires creating an
		//anonymous class to store the dupe in; and i cannot use that anonymous class to alter my total var
		for(int i = 0; i < dupes.size(); i++) {
			//CHECK DUPE CASE
			if(Character.isUpperCase(dupes.get(i))) {
				//SUBTRACT 38 FROM ASCII VALUE FOR UPPERCLASS CHARS
				//System.out.println(dupes.get(i) + " : " + (int) dupes.get(i));
				total += (int) dupes.get(i) - 38;
			}else {
				//SUBTRACT 96 FROM ASCII VALUE FOR LOWERCLASS CHARS
				//System.out.println(dupes.get(i) + " : " + (int) dupes.get(i));
				total += (int) dupes.get(i) - 96;
			}
		}
		
		//OUTPUT SOLUTION
		System.out.println("The total of all badges is " + total);
		
	}

}