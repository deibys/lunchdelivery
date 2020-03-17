package s4n.co.lunchdelivery.dto;

import java.io.Serializable;

import lombok.Value;

@Value
public class DroneInput implements Serializable {

	private String fileName;
	private String[] directions;
}
