package s4n.co.lunchdelivery.validation;

import static org.junit.Assert.*;
import static s4n.co.lunchdelivery.validation.DroneInputValidation.isContentOk;
import static s4n.co.lunchdelivery.validation.DroneInputValidation.isFileNameValid;
import static s4n.co.lunchdelivery.validation.DroneInputValidation.maxNumberOfDeliveriesIsOk;

import org.junit.Test;

import s4n.co.lunchdelivery.dto.DroneInput;

public class DroneInputValidationTest {

	@Test
	public void validateMaxNumberOfDeliveriesNotAllowed() {
		String[] directions = {"AADAAIA", "DDDAIAA","AAAAAIIAA", "DAAAAIA"};
		DroneInput input = new DroneInput("in01.txt", directions);
		
		boolean isInputValid = maxNumberOfDeliveriesIsOk(3)
    			.apply(input);
		
		assertEquals(false, isInputValid);
	}

	@Test
	public void validateMaxNumberOfDeliveriesAreAllowed() {
		String[] directions = {"AADAAIA", "DDDAIAA", "DAAAAIA"};
		DroneInput input = new DroneInput("in01.txt", directions);
		
		boolean isInputValid = maxNumberOfDeliveriesIsOk(3)
    			.apply(input);
		
		assertEquals(true, isInputValid);
	}

	@Test
	public void validateContentIsOk() {
		String[] directions = {"AADAAIA", "DDDAIAA","AAAAAIIAA", "DAAAAIA"};
		DroneInput input = new DroneInput("in01.txt", directions);
		
		boolean isInputValid = isContentOk()
    			.apply(input);
		
		assertEquals(true, isInputValid);
	}

	@Test
	public void validateContentIsNotOk() {
		String[] directions = {"AADAAIA", "DDDAIAA","AAAAAIIAA", "DAAAAEIA"};
		DroneInput input = new DroneInput("in01.txt", directions);
		
		boolean isInputValid = isContentOk()
    			.apply(input);
		
		assertEquals(false, isInputValid);
	}

	@Test
	public void validateFileNameOk() {
		String[] directions = {"AADAAIA", "DDDAIAA","AAAAAIIAA", "DAAAAIA"};
		DroneInput input = new DroneInput("in01.txt", directions);
		
		boolean isInputValid = isFileNameValid()
    			.apply(input);
		
		assertEquals(true, isInputValid);
	}

	@Test
	public void validateFileNameNotOk() {
		String[] directions = {"AADAAIA", "DDDAIAA","AAAAAIIAA", "DAAAAIA"};
		DroneInput input = new DroneInput("dsa.txt", directions);
		
		boolean isInputValid = isFileNameValid()
    			.apply(input);
		
		assertEquals(false, isInputValid);
	}
}
