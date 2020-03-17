package s4n.co.lunchdelivery;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import s4n.co.lunchdelivery.dto.DroneInput;
import s4n.co.lunchdelivery.dto.Position;


/**
 * Hello world!
 *
 */
public class LunchDeliveryApp 
{
	private static final int MAX_NUMBER_DELIVERIES_PER_DRONE = 3;
	
	private static final int MAX_NUMBER_OF_DRONES = 20; 

    public static void main( String[] args ) throws IOException 
    {
    	DroneInputReaderImpl reader = new DroneInputReaderImpl();
    	List<DroneInput> inputs = reader.execute(args, MAX_NUMBER_DELIVERIES_PER_DRONE);

        DeliveryServiceImpl deliveryService = new DeliveryServiceImpl(MAX_NUMBER_OF_DRONES);
        Map<Integer, Position[]> deliveryResponses = deliveryService.execute(inputs);

        DroneOutputWriterImpl writer = new DroneOutputWriterImpl();
        writer.execute(deliveryResponses);
    }
}
