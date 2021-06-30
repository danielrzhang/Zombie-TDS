package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.StateManager;

public class Game implements Runnable {

	private Display display;
	private String title;
	private int width, height;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bufferStrategy;
	private Graphics g;

	// States
	private StateManager stateManager;
//	public static State deathState;
//	public static State gameState;
//	public static State helpState;
//	public static State homeState;
//	public static State levelState;
//	public static State settingsState;
//	public static State shopState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	private Handler handler;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;	
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		stateManager = new StateManager(handler);
		
		stateManager.setState(new GameState(handler));
	}


	private void tick() {
		keyManager.tick();
		mouseManager.tick();
		
		if (stateManager.getState() != null) {
			stateManager.getState().tick();
		}
	}

	private void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy();

		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, width, height);

		// Draw Here

		if (stateManager.getState() != null) {
			stateManager.getState().render(g);
		}
		
		// End Here
		bufferStrategy.show();
		g.dispose();
	}

	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}

			if (timer >= 1000000000) {
				timer = 0;
			}
		}

		stop();

	}

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public StateManager getStateManager() {
		return stateManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Display getDisplay() {
		return display;
	}
}
