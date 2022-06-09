import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameScreen extends JFrame {

	public GameScreen(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	GameScreen gameScreen=new GameScreen("Space Game");
	
	//gameScreen.setResizable(false);
	gameScreen.setFocusable(false);
	gameScreen.setSize(700,600);
	gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Game game=new Game();
	game.requestFocus();
	game.addKeyListener(game);
	game.setFocusable(true);
	game.getFocusTraversalKeysEnabled();
	gameScreen.add(game);
	gameScreen.setVisible(true);
	
	}

}
