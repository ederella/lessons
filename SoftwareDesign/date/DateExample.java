package date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateExample {
	public static void main(String[] args) {
		String dateString = "2024-05-13 14:30:00";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime date = LocalDateTime.parse(dateString, format);
		System.out.println("Date: " + date.format(format));

	}
}