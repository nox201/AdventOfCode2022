import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class main {
	
	//INITALIZE VARIABLES
	static ArrayList<ArrayList<String>> crates = new ArrayList<ArrayList<String>>();

	public static void main(String[] args) throws IOException {
		
		//DEBUG PROGRAM START
		System.out.println("Program started");
		
		//CREATE CONNECTION
		Connection connection = Jsoup.connect("https://adventofcode.com/2022/day/5/input");
		//SET COOKIE - SESSION ID REMOVED FOR SECURITY
		connection.cookie("session", "x");
		
		//SCRAPE WEBPAGE - GET LIST OF MATCHES
		Response response = connection.execute();
		
		//EXPLODE RESPONSE ON NEW LINE
		String[] body = response.body().split("\n");
		
		//ADD 9 STACKS OF CRATES
		for(int i = 0; i < 9; i++) {
			crates.add(new ArrayList<String>());
		}
		ArrayList<String> instructions = new ArrayList<String>();
		
		//ITERATE BODY OF TEXT TO POPULATE VARIABLES
		for(int i = 0; i < body.length; i++) {
			//MAKE CRATE STACKS
			if(i < 8) {
				//TURN STRING INTO CHAR ARRAY
				char[] chars = body[i].toCharArray();
				//START LOOP COUNTER
				int loop = 0;
				//ITERATE CHARS
				for(Character chr : chars) {
					//CHECK IF CHAR IS LETTER
					if(Character.isLetter(chr)) {
						//SWITCH ON POSITION IN STRING TO DETERMINE WHICH STACK LETTER EXISTS ON
						switch(loop) {
							case 1:
								//ADD TO STACK
								crates.get(0).add((String) chr.toString());
							break;
							case 5:
								crates.get(1).add((String) chr.toString());
							break;
							case 9:
								crates.get(2).add((String) chr.toString());
							break;
							case 13:
								crates.get(3).add((String) chr.toString());
							break;
							case 17:
								crates.get(4).add((String) chr.toString());
							break;
							case 21:
								crates.get(5).add((String) chr.toString());
							break;
							case 25:
								crates.get(6).add((String) chr.toString());
							break;
							case 29:
								crates.get(7).add((String) chr.toString());
							break;
							case 33:
								crates.get(8).add((String) chr.toString());
							break;
						
						}
					}
					//BUMP LOOP COUNTER
					loop++;
				}
			}
			//NEW LINE
			if(i == 9) {
				continue;
			}
			//INSTRUCTIONS
			if(i > 9) {
				instructions.add(body[i]);
			}
		}
		
		//REVERSE STACK ORDER
		for(int i = 0; i < crates.size(); i++) {
			Collections.reverse(crates.get(i));
		}
		
		//ITERATE INSTRUCIONS
		for(int i = 0; i < instructions.size(); i++) {
			
			String[] parts = instructions.get(i).split(" ");
			
			/*System.out.println(parts[0]);
			System.out.println(parts[1]);
			System.out.println(parts[2]);
			System.out.println(parts[3]);
			System.out.println(parts[4]);
			System.out.println(parts[5]);*/
			
			//MOVE CRATES
			moveCrate(Integer.valueOf(parts[1]), Integer.valueOf(parts[3]) - 1, Integer.valueOf(parts[5]) - 1);
			//debugCrates();
			
		}
				
		
		//DEBUG INSTRUCTIONS
		instructions.forEach(new Consumer<String>() {
			public void accept(String instruction) {
				//System.out.println(instruction);
			}
		});
		
		//OUTPUT FINAL CRATE POSITION
		debugCrates();
		
	}
	
	
	public static void moveCrate(int iterations, int fromStack, int toStack) 
	{
		//CREATE RECORD OF CRATES MOVED
		ArrayList<String> cratesMoved = new ArrayList<String>(); 
		for(int i = 0; i < iterations; i++) {
			//ADD TO CRATES MOVED
			cratesMoved.add(crates.get(fromStack).get(crates.get(fromStack).size() - 1));
			//REMOVE VALUE FROM FROMSTACK
			crates.get(fromStack).remove(crates.get(fromStack).size() - 1);
		}
		//REVERSE CRATES MOVED ORDER TO PRESERVE THE ORDER TO PUT THEM BACK
		Collections.reverse(cratesMoved);
		//FOREACH CRATES MOVED MOVE THEM ONTO THE TOSTACK
		cratesMoved.forEach((crate) -> crates.get(toStack).add(crate));
	}
	
	public static void debugCrates() {
		//DEBUG CRATES
		for(int i = 0; i < crates.size(); i++) {
			String debugStr = "Stack " + i + " : ";
			for(int j = 0; j < crates.get(i).size(); j++) {
				debugStr = debugStr.concat(crates.get(i).get(j));
			}
			System.out.println(debugStr);
		}
	}
	

}