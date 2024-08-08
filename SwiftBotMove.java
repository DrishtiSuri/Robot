import swiftbot.SwiftBotAPI;
import swiftbot.Underlight;
import java.util.Random;

public class SwiftBotMove {
	
	public static int randomSpeed;

	public static int movement(SwiftBotAPI API) {
			
			int[] userInput = QRCode.QR(API);				
					
			// Random speed generator for wheels			 
			Random randomSpeedGenerator = new Random();	
			int minSpeed = 40;
			int maxSpeed = 100;
			int randomSpeed = randomSpeedGenerator.nextInt(maxSpeed - minSpeed + 1) + minSpeed;		
			System.out.println("The speed the wheels of the swiftbot will move at is " + randomSpeed);
			
			// Calculating the time it will take to cover a zigzag section				
			int time = (int) Math.ceil((double) userInput[0] / (randomSpeed / 26.4)* 100);
			System.out.println("The time taken to cover the distance of a zigzag section is " + time);			
										
			int[] secondColourToLightUp = {0,255,0}; // Underlights colour will turn blue	
						
			int[] colourToLightUp = {0,0,255}; // Underlights colour will turn green
			
			long startTimer = System.currentTimeMillis();
			int sectionCounter;
				for(sectionCounter = 0; sectionCounter < userInput[1]; sectionCounter ++ ) {
					
					System.out.println("Starting section: " + sectionCounter);				
					API.fillUnderlights(colourToLightUp); // Underlights = green					
					API.move(randomSpeed,randomSpeed,time); // Swiftbot moves with underlights green 
					API.move(0,0,1000); // Stops the swiftbot for 1 second. 1 sec = 1000 millisec									
					API.move(0, 70, 1000); // Turn swiftbot 90 degrees orthogonally to left - 1 sec	
					sectionCounter++;
					
					System.out.println("Starting section: " + sectionCounter);			
					API.fillUnderlights(secondColourToLightUp); // Underlights = blue					
					API.move(randomSpeed, randomSpeed, time); // Swiftbot moves with underlights blue
					API.move(0, 0, 1000); // Stops the swiftbot for 1 second
					API.move(65, 0, 1000); // Turn swiftbot 90 degrees orthogonally to right - 1 sec
				}
				
				
							
				// Check if sectionCounter is equal to the number of sections
				if(sectionCounter == userInput[1]) {
					
					//Code for swiftbot to retrace back to the starting point					
					API.move(-50, 50, 1380); // The swiftbot turns orthogonally to the right
					
					for(sectionCounter = userInput[1]; sectionCounter > 0; sectionCounter--) {
						
						System.out.println("Starting section: " + sectionCounter);									
						API.fillUnderlights(secondColourToLightUp); // Underlights = blue
						API.move(randomSpeed, randomSpeed, time); // Swiftbot moves with underlights blue
						API.move(0, 0, 1000); // Stops the swiftbot for 1 second				
						API.move(0, 70, 1000); // Turn swiftbot 90 degrees orthogonally to right - 1 sec		
						sectionCounter --;
						
						System.out.println("Starting section: " + sectionCounter);				
						API.fillUnderlights(colourToLightUp); // Underlights = green					
						API.move(randomSpeed,randomSpeed,time); // Swiftbot moves with underlights green 
						API.move(0,0,1000); // Stops the swiftbot for 1 second. 1 sec = 1000 millisec									
						API.move(65, 0, 1000); // Turn swiftbot 90 degrees orthogonally to left - 1 sec	
					}
					long endTimer = System.currentTimeMillis();
							
					long duration = (endTimer - startTimer)/1000;
					System.out.println("The duration for the zigzag from starting point to ending point is:" + duration);
					
					API.disableUnderlights();
					return (int) duration;
				}
				else {
					 //If the swiftbot is unable to complete its journey from starting to ending point then it will exit the system here.
					System.out.println("There has been an error! The system will be exited!");
					System.exit(0);					
				}						
				return time;
			
		}

	public int getRandomSpeed() {
		
		return randomSpeed;
	}		
	
	public static long startTime() {
		
		return System.currentTimeMillis();		
	}
	
	public static long endTime() {
		
		return System.currentTimeMillis();
	}
}
