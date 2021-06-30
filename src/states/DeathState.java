package states;

import java.awt.Font;
import java.awt.Graphics;

import game.Assets;
import game.Handler;
import input.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import ui.UIObject;

public class DeathState extends State {
	
	private UIManager uiManager;

	public DeathState(Handler handler) {
		super(handler);
		stateName = "DeathState";
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addUIObject(new UIImageButton(1200 / 2 - UIObject.DEFAULT_BUTTON_WIDTH / 2, 300, UIObject.DEFAULT_BUTTON_WIDTH, UIObject.DEFAULT_BUTTON_HEIGHT, Assets.restartButton, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getStateManager().setState(new GameState(handler));
			}}));
		
		uiManager.addUIObject(new UIImageButton(1200 / 2 - UIObject.DEFAULT_BUTTON_WIDTH / 2, 360, UIObject.DEFAULT_BUTTON_WIDTH, UIObject.DEFAULT_BUTTON_HEIGHT, Assets.returnButton, new ClickListener() {
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
		g.drawString("You Died!", 500, 200);
	}

}
