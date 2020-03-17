package s4n.co.lunchdelivery.running;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import s4n.co.lunchdelivery.dto.CardinalPoint;
import s4n.co.lunchdelivery.dto.Coordinate;
import s4n.co.lunchdelivery.dto.Position;
import s4n.co.lunchdelivery.dto.DroneInput;

public class Drone implements Callable<Position[]> {

	private static final int MAX_NUMBER_BLOCKS_AWAY = 10;

	private static Map<CardinalPoint, Coordinate> cardinalPointAdvance;
	static {
		cardinalPointAdvance = new HashMap<>();
		cardinalPointAdvance.put(CardinalPoint.NORTH, new Coordinate(0,1));
		cardinalPointAdvance.put(CardinalPoint.SOUTH, new Coordinate(0,-1));
		cardinalPointAdvance.put(CardinalPoint.EAST, new Coordinate(1,0));
		cardinalPointAdvance.put(CardinalPoint.WEST, new Coordinate(-1,0));
	}

	private int id;
	private DroneInput input;
	private Coordinate droneCoordinate;
	private CardinalPoint droneFacingDirection;

	public Drone(DroneInput input) {
		this.input = input;
		this.id = getDroneNumber(input.getFileName());
		this.droneCoordinate =  new Coordinate(0,0);
		this.droneFacingDirection = CardinalPoint.NORTH;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public Position[] call() {
		Position[] deliveries = new Position[input.getDirections().length];
		int i = 0;
		for (String direction : input.getDirections()) {
			char[] charArray = new char[direction.length()]; 
			StringReader reader = new StringReader(direction);
			try {
				reader.read(charArray);
				for (char letter : charArray) {
					if (letter == 'A') {
						droneCoordinate = droneCoordinate
								.add(cardinalPointAdvance.get(droneFacingDirection));
					} else if (letter == 'D') {
						droneFacingDirection = calculateFacingDirection(droneFacingDirection, 1);
					} else if (letter == 'I') {
						droneFacingDirection = calculateFacingDirection(droneFacingDirection, -1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			deliveries[i] = new Position(droneCoordinate, droneFacingDirection);
			i++;
		}
	    return deliveries;	
	}

	private CardinalPoint calculateFacingDirection(CardinalPoint droneFacingDirection, int factor) {
		int m = Math.floorMod(droneFacingDirection.getNumber() + factor, 4);
		return CardinalPoint.fromNumber(m);
	}
	
	private int getDroneNumber(String str) {
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        if (m.find()) {
        	String matched = m.group();
            return Integer.parseInt(matched);
        }
        return 0;
	}
}
