package enemy;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game_Screen.Blocks;
import game_Screen.Player;
import handler.Enemy_handler;

public abstract class EnemyInterface {

	private Enemy_handler bulletHandler;

	public EnemyInterface (Enemy_handler bulletHandler){
		this.bulletHandler = bulletHandler;
	}
	
	public Enemy_handler getBulletHandler(){
		return bulletHandler;
	}
	public abstract void rysuj(Graphics2D g2d);
	public abstract void update(double delta, Player player, Blocks blocks);
	public abstract void changeDirection (double delta);
	public abstract boolean collide (int i, Player player, Blocks blocks, ArrayList<EnemyInterface> enemies);
	public abstract boolean deathScene();
	public abstract boolean isOutofBounds();
}
