package states;

import java.awt.Graphics;

import game.Assets;
import game.Handler;
import input.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import ui.UIObject;

public class HomeState extends State {

	private UIManager uiManager;
	
	public HomeState(Handler handler) {
		super(handler);
		stateName = "HomeState";
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addUIObject(new UIImageButton(1200 / 2 - UIObject.DEFAULT_BUTTON_WIDTH / 2, 300, UIObject.DEFAULT_BUTTON_WIDTH, UIObject.DEFAULT_BUTTON_HEIGHT, Assets.startButton, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setState(new GameState(handler));
			}}));
		
		uiManager.addUIObject(new UIImageButton(1200 / 2 - UIObject.DEFAULT_BUTTON_WIDTH / 2, 360, UIObject.DEFAULT_BUTTON_WIDTH, UIObject.DEFAULT_BUTTON_HEIGHT, Assets.helpButton, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setState(new HelpState(handler));
			}}));
		
		uiManager.addUIObject(new UIImageButton(1200 / 2 - UIObject.DEFAULT_BUTTON_WIDTH / 2, 420, UIObject.DEFAULT_BUTTON_WIDTH, UIObject.DEFAULT_BUTTON_HEIGHT, Assets.settingsButton, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setState(new SettingsState(handler));
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
