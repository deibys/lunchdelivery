package s4n.co.lunchdelivery.dto;

import lombok.Value;

@Value
public class Coordinate {

	private int x;
	private int y;

	public Coordinate add(Coordinate c) {
		return new Coordinate(this.x + c.getX(), 
				this.y + c.getY()  );
	}
}
