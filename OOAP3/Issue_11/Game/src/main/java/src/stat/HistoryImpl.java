package src.stat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import src.game.step.GameStep;

@Component
public class HistoryImpl implements History{
	private Queue<GameStep> stepQueue;
	
	public HistoryImpl() {
		stepQueue = new LinkedList<>();
	}
	
	
	@Override
	public void store(GameStep step) {
		stepQueue.add(step);
	}

	@Override
	public String show() {
		return Arrays.toString(stepQueue.toArray());
	}


	@Override
	public void clear() {
		stepQueue = new LinkedList<>();
		
	}
	
	

}
