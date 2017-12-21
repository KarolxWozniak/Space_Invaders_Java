package levels;

import java.awt.Graphics2D;

import game_Screen.Blocks;

public interface Levels_interface {

	void rysuj(Graphics2D g2d);
	void update(double delta, Blocks blocks);
	void hasDirectionChange (double delta);
	void changeDirectionEnemies (double delta);
	void reset();
	void destroy();
	
	boolean isGameOver();
	
}
