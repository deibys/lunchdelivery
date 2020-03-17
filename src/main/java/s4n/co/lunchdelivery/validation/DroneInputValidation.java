package s4n.co.lunchdelivery.validation;

import java.util.Arrays;
import java.util.function.Function;

import s4n.co.lunchdelivery.dto.DroneInput;

public interface DroneInputValidation extends Function<DroneInput, Boolean> {

	
	static DroneInputValidation maxNumberOfDeliveriesIsOk(int maxNumber) {
		return input -> input.getDirections().length <= maxNumber;
	}
	
	static DroneInputValidation isFileNameValid() {
		return input -> input.getFileName().startsWith("in");
	}

	static DroneInputValidation isContentOk() {
		return input -> 
			Arrays.stream(input.getDirections())
				.allMatch(s -> s.matches("[ADI]+") );
	}

	default DroneInputValidation and(DroneInputValidation other) {
        return input -> this.apply(input) && other.apply(input);
    }
}
