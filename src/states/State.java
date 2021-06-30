package states;

import java.awt.Graphics;

import game.Handler;

public abstract class State {
	
	protected Handler handler;
	protected String stateName;
	
	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public String getStateName() {
		return stateName;
	}
}
