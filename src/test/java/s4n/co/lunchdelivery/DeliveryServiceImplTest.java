package s4n.co.lunchdelivery;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import s4n.co.lunchdelivery.dto.DroneInput;
import s4n.co.lunchdelivery.dto.Position;

public class DeliveryServiceImplTest {

	@Test
	public void testExecute() {
		String[] directions = {"AAAAIAA", "DDDAIAD","AAIADAD"};
		DroneInput input = new DroneInput("in01.txt", directions);
		
		List<DroneInput> inputs = new ArrayList<>();
		inputs.add(input);
		
		DeliveryServiceImpl service = new DeliveryServiceImpl(20);
		Map<Integer, Position[]> responses = service.execute(inputs);
		
		assertEquals(directions.length, responses.get(1).length);
	}
}
