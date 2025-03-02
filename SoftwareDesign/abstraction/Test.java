package abstraction;

import abstraction.test.Body;
import abstraction.test.Head;
import abstraction.test.Snowman;

public class Test {
	public static void main(String[] args) {
		Snowman mrSnowman = new Snowman();
		
		mrSnowman.head = new Head();
		mrSnowman.head.radius = 30;
		mrSnowman.head.setEyesMaterial("Coal");
		mrSnowman.head.setNoseMaterial("Carrot");
		
		mrSnowman.body = new Body();
		mrSnowman.body.snowBallsCount = 2;
		mrSnowman.body.setButtonsCount(5);
		mrSnowman.body.setButtonsMaterial("Stones");
		
		mrSnowman.weight = 10;
		
		SimplePrinter s = new SimplePrinter();
		s.print(mrSnowman);
		s.print("Just simple string!");
		s.print(1012645);
	}
}
