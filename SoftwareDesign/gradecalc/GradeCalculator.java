package calculator;

import java.util.List;

public class GradeCalculator {
	
	private int maxGrade;
	private int minGrade;
	
	public GradeCalculator() {
		maxGrade = 5;
		minGrade = 1;
	}
	
	public GradeCalculator(int minGrade, int maxGrade) {
		this.maxGrade = maxGrade;
		this.maxGrade = minGrade;
	}

	double calculateAverage(List<Integer> grades) {
		if (grades == null || grades.isEmpty()) {
			throw new IllegalArgumentException("Grades must not be null or empty");
		}
		double sum = 0d;
		for (Integer grade : grades) {
			if (grade < minGrade || grade > maxGrade)
				throw new IllegalArgumentException("Incorrect grade");
			sum += Double.valueOf(grade);
		}

		return sum / grades.size();
	}

}
