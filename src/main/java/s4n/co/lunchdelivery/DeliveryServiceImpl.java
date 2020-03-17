package s4n.co.lunchdelivery;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import s4n.co.lunchdelivery.dto.DroneInput;
import s4n.co.lunchdelivery.dto.Position;
import s4n.co.lunchdelivery.running.Drone;

public class DeliveryServiceImpl {

	private ThreadPoolExecutor executor;

	public DeliveryServiceImpl(int maxNumberOfDrones) {
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxNumberOfDrones);
	}
	
	public Map<Integer, Position[]> execute(List<DroneInput> inputs) {
		Map<Integer, Future<Position[]>> droneFutureResponses = new HashMap<>();
		for (DroneInput input : inputs) {
			Drone drone = new Drone(input);
			Future<Position[]> futureResponse = executor.submit(drone);
			droneFutureResponses.put(drone.getId(), futureResponse);
		}
		executor.shutdown();
		Map<Integer, Position[]> droneResponses = new HashMap<>();
		
		for (Map.Entry<Integer,Future<Position[]>> entry : droneFutureResponses.entrySet()) {
			try {
				droneResponses.put(entry.getKey(), entry.getValue().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return droneResponses;
		
	}
}
