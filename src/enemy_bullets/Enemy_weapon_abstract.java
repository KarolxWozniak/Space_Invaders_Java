package enemy_bullets;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import game_Screen.Blocks;
import game_Screen.Player;

public abstract class Enemy_weapon_abstract {
	public abstract void rysuj(Graphics2D g2d);
	public abstract void update(double delta, Blocks blocks, Player player);
	
	public abstract boolean collision(Rectangle rect);
	public abstract boolean destory();
	
	protected abstract void wallCollide(Blocks blocks);
	protected abstract void isOutofBounds();
	
	public abstract int getX();
	public abstract int getY();
}
