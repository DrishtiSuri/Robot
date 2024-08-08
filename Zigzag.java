import swiftbot.SwiftBotAPI;
import java.util.Scanner;

public class Zigzag {
	public static void main(String[] args) throws InterruptedException {
				
		SwiftBotAPI API = new SwiftBotAPI();
		Scanner scan = new Scanner(System.in);

		WelcomeMessage.message(scan);
		SwiftBotMove.movement(API);		
		TerminateProgram.termination(API, scan);	
		DisplayJourneyInfo.displayInfoTextFile(0, 0, API);
			
	}	
}














