import swiftbot.SwiftBotAPI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DisplayJourneyInfo {

	public static void displayInfoTextFile(long startTime, long endTime, SwiftBotAPI API) {
		
		try {
			FileWriter writer = new FileWriter("/home/pi/Documents/logInfo.txt"); 

			BufferedWriter bw = new BufferedWriter(writer);
			
			bw.write("------------ Zigzag Journey Information ------------ ");
		
			int[] userInput = QRCode.QR(API);
		
			SwiftBotMove movement = new SwiftBotMove();
			int speed = movement.getRandomSpeed();
			long duration = SwiftBotMove.movement(API);
		
			// Calculations of traversed path, distance the swiftbot travelled and the duration it took
			int traversedPath = userInput[0] * userInput[1];
			double distance = userInput[0] / 2.0 * Math.sqrt(Math.pow(userInput[1], 2) * 2.0);			
			long startEndDuration = duration;
				
			writer.write("The number of zigzag sections inputted is " + userInput[1] + "\n");
			writer.write("The length of each zigzag length is " + userInput[0] + "\n");
			writer.write("The speed of the swiftbot in which it moved at is " + speed + "\n");   
			writer.write("The length of the traversed path is " + traversedPath + "\n"); 
			writer.write("The duration the swiftbot took is " + startEndDuration + "\n"); 
			writer.write("The distance the swiftbot travelled is " + distance + "\n"); 
		
			bw.close();
			writer.close();
	
		}
		catch(IOException e) {
            e.printStackTrace();
		}
    }	
}
