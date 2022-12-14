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
		Connection connection = Jsoup.connect("https://adventofcode.com/2022/day/4/input");
		//SET COOKIE - SESSION ID REMOVED FOR SECURITY
		connection.cookie("session", "53616c7465645f5fcd4fb137ff8da022feb2198fb49194132fde24c0fa2b291e5b7b03ff6a9668bc9f582c9ff8907fc138337942aba8f80bc4620018c824f671");
		
		//SCRAPE WEBPAGE - GET LIST OF MATCHES
		Response response = connection.execute();
		
		//EXPLODE RESPONSE ON NEW LINE
		String[] sections = response.body().split("\n");
		
		//INITALIZE VARIABLES
		Integer total = 0;
		
		//ITERATE SECTIONS
		for(int i = 0; i < sections.length; i++) {
			
			//SPLIT SECTION BY ELVES
			String[] elfSections = sections[i].split(",");
			//SPLIT ELF SECTIONS INTO MIN AND MAX VALUES
			Integer elf1Min = Integer.valueOf(elfSections[0].split("-")[0]);
			Integer elf1Max = Integer.valueOf(elfSections[0].split("-")[1]);
			Integer elf2Min = Integer.valueOf(elfSections[1].split("-")[0]);
			Integer elf2Max = Integer.valueOf(elfSections[1].split("-")[1]);
			
			//CHECK IF EITHER SET DOESNT OVERLAP THE OTHER
			if(elf1Min > elf2Max || elf2Min > elf1Max) {	
				//DO NOTHING
			}else {
				total++;
			}
			
			//DEBUG
			//System.out.println(elfSections[0]);
			//System.out.println(elfSections[1]);
			//System.out.println(elf1Min);
			//System.out.println(elf1Max);
			//System.out.println(elf2Min);
			//System.out.println(elf2Max);
			
		}
		
		//OUTPUT SOLUTION
		System.out.println("The total of overlapping sections is " + total);
		
	}

}