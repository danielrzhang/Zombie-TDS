package states;

import java.awt.Font;
import java.awt.Graphics;

import game.Assets;
import game.Handler;
import input.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import ui.UIObject;

public class SettingsState extends State {
	
	private UIManager uiManager;

	public SettingsState(Handler handler) {
		super(handler);
		stateName = "SettingsState";
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
	
		uiManager.addUIObject(new UIImageButton(10, 540, UIObject.DEFAULT_BUTTON_HEIGHT * 2, UIObject.DEFAULT_BUTTON_HEIGHT, Assets.backButton, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setState(new HomeState(handler));
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		g.setFont(new Font("Arial", 50, 50));
		g.drawString("WIP", 550, 300);
	}
}
