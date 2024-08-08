import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import swiftbot.ImageSize;
import swiftbot.SwiftBotAPI;

public class QRCode {
	
	public static int[] userInputsArray = new int [2];

	public static int[] QR(SwiftBotAPI API) {	
		
		BufferedImage Image = API.getQRImage();
			 
		try {
			Image = API.takeGrayscaleStill(ImageSize.SQUARE_144x144);
			
			if(Image == null) {

                System.out.println("ERROR: Image is null");
			}
			else {
				ImageIO.write(Image, "jpg", new File("/home/pi/QRCode.jpg"));
				
				System.out.println("The SwiftBot camera works!");
                Thread.sleep(1000);
			}
			
		}catch	(Exception e){
			e.printStackTrace();
		}	             			
		
		try{
			boolean QRCodeScanned = false;
			while(!QRCodeScanned) {
            System.out.println("Taking a capture in 5 seconds..");
            Thread.sleep(5000);
    
            BufferedImage img = API.getQRImage();
            String decodedMessage = API.decodeQRImage(img);
    
            if(decodedMessage.isEmpty()){
                System.out.println("No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
            }
            else{
                System.out.println("SUCCESS: QR code found");
                System.out.println("Decoded message: " + decodedMessage);
                
                String[] values = decodedMessage.split("\\s+");
                          
                if(values.length == 2) {
                	userInputsArray[0] = Integer.parseInt(values[0].trim());
                	userInputsArray[1] = Integer.parseInt(values[1].trim());
                	
                	System.out.println("The length of each section of a zigzag is: " + userInputsArray[0]);
                    System.out.println("The number of sections of one zigzag journey is: " + userInputsArray[1]);
                    
                    if(userInputsArray[0] >= 15 && userInputsArray[0] <= 85 && userInputsArray[1] <= 12 && userInputsArray[1] % 2 == 0) {
                    	
                    	System.out.println("Valid Inputs!");  
                    	break;
                    }
                    else {
                    	System.out.println("Error! You have entered invalid numeric values. "
                    			+ "The length of a zigzag section should be between 15 and 85cm, and "
                    			+ "the number of zigzag sections should be 12 or less and even.");                   	
                    }
                }
                else {
                	System.out.println("The QR code does not contain the user's two inputs");
                }                            
            }
		}
        }catch(Exception e){
            System.out.println("ERROR: Unable to scan for code.");
            e.printStackTrace();            
        }		
		
		return userInputsArray;
    }
	
	public SwiftBotAPI getAPI() {
		
		return getAPI();		
	}	
}


