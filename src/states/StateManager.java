package states;

import game.Handler;

public class StateManager {
	
	private Handler handler;
	private State currentState;
	
	public static State deathState;
	private State gameState;
	private State helpState;
	private State homeState;
	private State levelState;
	private State settingsState;
	private State shopState;
	
	public StateManager(Handler handler) {
		this.handler = handler;
		deathState = new DeathState(handler);
		gameState = new GameState(handler);
		helpState = new HelpState(handler);
		homeState = new HomeState(handler);
		levelState = new LevelState(handler);
		settingsState = new SettingsState(handler);
		shopState = new ShopState(handler);
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public void setState(State currentState) {
		this.currentState = currentState;
	}

	public State getState() {
		return currentState;
	}

	public State getDeathState() {
		return deathState;
	}

	public State getGameState() {
		return gameState;
	}

	public State getHelpState() {
		return helpState;
	}

	public State getHomeState() {
		return homeState;
	}

	public State getLevelState() {
		return levelState;
	}

	public State getSettingsState() {
		return settingsState;
	}

	public State getShopState() {
		return shopState;
	}
}
