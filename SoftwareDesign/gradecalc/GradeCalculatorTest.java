package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeCalculatorTest {
	
	private GradeCalculator calc;
	
	@BeforeEach
	public void init() {
		calc = new GradeCalculator();
	}

	@Test
	final void testNull() {
		List<Integer> grades = null;
		assertThrows(IllegalArgumentException.class, ()->calc.calculateAverage(grades));
		
	}
	
	@Test
	final void testEmpty() {
		List<Integer> grades = new ArrayList<Integer>();
		assertThrows(IllegalArgumentException.class, ()->calc.calculateAverage(grades));
	}
	
	@Test
	final void testGreaterThanMax() {
		List<Integer> grades = List.of(6, 7, 8, 9);
		assertThrows(IllegalArgumentException.class, ()->calc.calculateAverage(grades));
	}
	
	@Test
	final void testLessThanMin() {
		List<Integer> grades = List.of(-1, -100, -9);
		assertThrows(IllegalArgumentException.class, ()->calc.calculateAverage(grades));
	}
	
	@Test
	final void testNormal() {
		List<Integer> grades = List.of(5, 5, 4, 5, 3, 4);
		assertDoesNotThrow(()->calc.calculateAverage(grades));
		assertEquals(4.333, calc.calculateAverage(grades), 0.01);
	}

}
