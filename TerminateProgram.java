import swiftbot.*;
import java.util.Scanner;

public class TerminateProgram {
	public static volatile boolean terminate = false; 
	
	static int zigzagJourneysCompleted = 0;

	public static void termination(SwiftBotAPI API, Scanner scan) throws InterruptedException  {
				
		// To terminate the program press Button X on Swiftbot		
			 
		API.disableAllButtons();
		
		String userTerminateInput = "";
		System.out.println("Do you want to terminate the program? If you do please enter 'yes' else please enter 'no' ");
		while ((!userTerminateInput.equalsIgnoreCase("yes") && terminate == false)|| (userTerminateInput.equalsIgnoreCase("no")) && terminate == false) {
			userTerminateInput = scan.nextLine();
			
			zigzagJourneysCompleted++;
			
			if (userTerminateInput.equalsIgnoreCase("yes")) {
				System.out.println("Please press button X only on the Swiftbot");
				Thread.sleep(5000);

				API.enableButton(Button.X, () -> {
					API.disableAllButtons();
					terminate = true;
					
	            	System.out.println("The number of zigzag journeys you have completed is: " + zigzagJourneysCompleted);
	            	System.out.println("******* Program Terminated *******");
	            	System.out.println("******* Goodbye! ******");
				});
			
				while(terminate == false){
					Thread.sleep(50);
				}
				System.exit(0);
			}
			else if(userTerminateInput.equalsIgnoreCase("no")){
				// Loop back to the user scan QR code 
				
				QRCode.QR(API);
				SwiftBotMove.movement(API);
				TerminateProgram.termination(API, scan);
				DisplayJourneyInfo.displayInfoTextFile(zigzagJourneysCompleted, zigzagJourneysCompleted, API);
				
				terminate = true;
			}				
			else {
				
	        	System.out.println("The input you have entered is invalid. Please try again.");
	        }	
		}
	}
}
	
