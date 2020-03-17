package s4n.co.lunchdelivery.dto;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testAddWhenMoveToEast() {
		Coordinate c = new Coordinate(0,0);
		Coordinate c1 = new Coordinate(1,0);
		
		Coordinate newCoordinate = c.add(c1);
		
		assertEquals(1, newCoordinate.getX());
		assertEquals(0, newCoordinate.getY());
	}

	@Test
	public void testAddWhenMoveToWest() {
		Coordinate c = new Coordinate(0,4);
		Coordinate c1 = new Coordinate(-2,0);
		
		Coordinate newCoordinate = c.add(c1);
		
		assertEquals(-2, newCoordinate.getX());
		assertEquals(4, newCoordinate.getY());
	}
}
