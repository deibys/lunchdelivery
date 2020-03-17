package s4n.co.lunchdelivery.running;

import static org.junit.Assert.*;

import org.junit.Test;

import s4n.co.lunchdelivery.dto.CardinalPoint;
import s4n.co.lunchdelivery.dto.DroneInput;
import s4n.co.lunchdelivery.dto.Position;

public class DroneTest {

	@Test
	public void testCall() {
		String[] directions = {"AAAAIAA", "DDDAIAD","AAIADAD"};
		DroneInput input = new DroneInput("in01.txt", directions);

		Drone drone = new Drone(input);
		
		Position[] deliveries = drone.call();

		assertEquals(directions.length, deliveries.length);
		//first point should be (-2,4) WEST
		assertEquals(CardinalPoint.WEST, deliveries[0].getDirection());
		assertEquals(-2, deliveries[0].getCoordinate().getX());
		assertEquals(4, deliveries[0].getCoordinate().getY());
		//second point should be (-1,3) SOUTH
		assertEquals(CardinalPoint.SOUTH, deliveries[1].getDirection());
		assertEquals(-1, deliveries[1].getCoordinate().getX());
		assertEquals(3, deliveries[1].getCoordinate().getY());
		//second point should be (-1,3) SOUTH
		assertEquals(CardinalPoint.WEST, deliveries[2].getDirection());
		assertEquals(0, deliveries[2].getCoordinate().getX());
		assertEquals(0, deliveries[2].getCoordinate().getY());
	}
}
