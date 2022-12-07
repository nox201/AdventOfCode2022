import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class main {

	public static void main(String[] args) throws IOException {
		
		//DEBUG PROGRAM START
		System.out.println("Program started");
		
		//CREATE CONNECTION
		Connection connection = Jsoup.connect("https://adventofcode.com/2022/day/1/input");
		//SET COOKIE - SESSION ID REMOVED FOR SECURITY
		connection.cookie("session", "x");
		
		//SCRAPE WEBPAGE - GET LIST OF CALORIES
		Response calories = connection.execute();
		
		//CONVERT RESPONSE TO CHAR ARRAY
		char[] calorieChars = calories.body().toCharArray();
		
		//CREATE ELF TOTALS
		ArrayList<Integer> elfTotals = new ArrayList<Integer>();
		//CREATE CURRENT ELF TOTAL
		int currentElfTotal = 0;
		//CREATE NEW LINE BUFFER FLAG
		Boolean newLine = false;
		//CREATE STRING BUFFER
		String strBuffer = "";
		
		//ITERATE RESPONSE (CALORIES)
		for(int i = 0; i < calories.body().length(); i++) {
			
			//CHECK FOR NEW LINE
			if(newLine) {
				//CHECK FOR SECOND NEW LINE
				if(!Character.isDigit(calorieChars[i])) {
					//NEW ELF DETECTED - STORE CURRENT ELF TOTAL
					elfTotals.add(currentElfTotal);
					//CLEAR CURRENT ELF TOTAL
					currentElfTotal = 0;
				}
			}
			
			//CHECK FOR INTEGER
			if(Character.isDigit(calorieChars[i])) {

				//ADD TO SOME STORAGE
				strBuffer = strBuffer + calorieChars[i];
				//RESET NEW LINE FLAG
				newLine = false;
				
			}else{
			
				//END OF NUMBER, ADD NUMBER IN BUFFER TO CURRENT ELF
				if(!newLine) {
					currentElfTotal = currentElfTotal + Integer.valueOf(strBuffer);
				}

				//CLEAR BUFFER
				strBuffer = "";
				//SET FLAG - ONE NEW LINE DETECTED
				newLine = true;
				
			}
			
		}
		
		//HIGHEST CALORIES
		int highestCalories = 0;
		//DETERMINE LARGEST NUMBER IN ELF TOTALS
		for(int i = 0; i < elfTotals.size(); i++) {
			if(elfTotals.get(i) > highestCalories) {
				highestCalories = elfTotals.get(i);
			}
		}
		
		//PRINT FINAL HIGHEST CALORIES
		System.out.println("The highest number of calories held by a single elf is " + highestCalories);

	}

}