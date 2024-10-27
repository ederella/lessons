package src.stat;


import src.game.step.GameStep;

public interface History {

	public void store(GameStep step);
	
	public String show();
	
	public void clear();
	
}
