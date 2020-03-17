package s4n.co.lunchdelivery;

import static s4n.co.lunchdelivery.validation.DroneInputValidation.isContentOk;
import static s4n.co.lunchdelivery.validation.DroneInputValidation.isFileNameValid;
import static s4n.co.lunchdelivery.validation.DroneInputValidation.maxNumberOfDeliveriesIsOk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import s4n.co.lunchdelivery.dto.DroneInput;

public class DroneInputReaderImpl {

	public DroneInputReaderImpl() {
	}
	
	public List<DroneInput> execute(String[] args, int maxNumberOfDeliveries) 
			throws FileNotFoundException, IOException {
		List<DroneInput> inputs = new ArrayList<>();
        for (String fileName : args) {
        	try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        		String[] directions = br.lines().toArray(size -> new String[size]);
        		
        		DroneInput droneInput = new DroneInput(fileName, directions);
        		boolean isInputValid = maxNumberOfDeliveriesIsOk(maxNumberOfDeliveries)
        			.and(isFileNameValid())
        			.and(isContentOk())
        			.apply(droneInput);
        		if (!isInputValid) {
        			throw new IllegalArgumentException("Bad Input");
        		}
        		inputs.add(droneInput);
        	}
        }
        return inputs;
	}
}
