package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AverageCalculatorTest {

	AverageCalculator calc;
	@BeforeEach
	public void init() {
		calc = new AverageCalculator();
	}

	@Test
	final void testCalculateAverage() {
		int[] arr = {1,2,3,4,5};
		assertDoesNotThrow(() -> calc.calculateAverage(arr));
		assertEquals(3.0, calc.calculateAverage(arr));
	}

}
