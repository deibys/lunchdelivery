package s4n.co.lunchdelivery;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import s4n.co.lunchdelivery.dto.Position;

public class DroneOutputWriterImpl {

	public DroneOutputWriterImpl() {}
	
	public void execute(Map<Integer, Position[]> deliveryResponses) throws IOException {
		for (Map.Entry<Integer,Position[]> entry : deliveryResponses.entrySet()) {
        	String fileName = "out" + entry.getKey() + ".txt";
        	
        	PrintWriter pw = new PrintWriter(new FileWriter(fileName));
        	for (int i = 0; i < entry.getValue().length; i++) {
        		Position[] positions = entry.getValue();
        		pw.println( positions[i].toString());
        	}         
        	pw.close();
        }
	}
}
