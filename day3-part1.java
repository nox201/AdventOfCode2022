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
		ArrayList<Integer> priorities = new ArrayList<Integer>();
		int total = 0;
		
		//ITERATE RUCKSACKS
		for(int i = 0; i < rucksacks.length; i++) {
			
			//DEBUG
			//System.out.println(rucksacks[i]);
			
			//CHECK THAT ALL SACKS ARE EVEN IN LENGTH
			if(rucksacks[i].length() % 2 == 1) {
				//NO ODD LENGTHS, CAN SPLIT SACKS EVENLY
				System.out.println("rucksacks " + i + " is odd");
			}
			
			//CREATE REGEX - GETS GROUPS HALF THE LENGTH OF THE STRING
			String regex = "(?<=\\G.{" + Integer.toString(rucksacks[i].length() / 2) + "})";
			
			//SPLIT ITEMS IN HALF
			String[] items = rucksacks[i].split(regex);
			//System.out.println(items[0] + " : " + items[1]);
			
			//ITERATE ITEMS IN FIRST COMPARTMENT
			for(int j = 0; j < items[0].length(); j++) {
				//CHECK IF ITEM EXISTS IN OTHER COMPARTMENT
				if(items[1].contains(String.valueOf(items[0].charAt(j)))){
					//System.out.println(String.valueOf(items[0].charAt(j)));
					//STORE DUPLICATE ITEM
					dupes.add(items[0].charAt(j));
					//DUPE STORED - BREAK LOOP TO PREVENT MULTIPLE DUPES BEING STORED
					break;
				}
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
		System.out.println("The total of all priorities is " + total);
		
	}

}