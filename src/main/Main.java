package main;

import game.Game;

public class Main {

	public static void main(String[] args) {
		Game game = new Game("Zombie TDS", 1200, 600);
		game.start();
	}

}
