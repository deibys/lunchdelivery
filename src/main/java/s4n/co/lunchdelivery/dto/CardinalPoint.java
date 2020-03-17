package s4n.co.lunchdelivery.dto;

import java.util.HashMap;
import java.util.Map;

public enum CardinalPoint {
	NORTH(0), SOUTH(2), EAST(1), WEST(3);

	private final int number;
	
	public int getNumber() {
		return this.number;
	}

	private static final Map<Integer, CardinalPoint> CARDINAL_POINT_BY_NUMBER = new HashMap<>();
	  static {
	    for (CardinalPoint cp : values()) {
	    	CARDINAL_POINT_BY_NUMBER.put(cp.getNumber(), cp);
	    }
	  }
	  
	public static CardinalPoint fromNumber(int number) {
		return CARDINAL_POINT_BY_NUMBER.get(number);
	}
	
	private  CardinalPoint(int number) {
		this.number = number;
	}
}
