package s4n.co.lunchdelivery.dto;

import lombok.Value;

@Value
public class Position {

	private Coordinate coordinate;
	private CardinalPoint direction;
	
	public String toString() {
		return "(" + coordinate.getX() + "," + coordinate.getY() + ") " + direction.name();
	}
}
