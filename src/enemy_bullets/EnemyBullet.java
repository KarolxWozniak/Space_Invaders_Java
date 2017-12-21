package enemy_bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Okno.Okno;
import game_Screen.Blocks;
import game_Screen.Player;

/**
 * Klasa odpowiadaj¹ca za stworzenie i obs³uge pojedyñczego pocisku.
 * Dziedziczy z klasy "Enemy_weapon_abstract".
 * 
 * @author Ja_Karol
 *
 */
public class EnemyBullet extends Enemy_weapon_abstract {

	private Rectangle bullet;
	private double speed = 1.0;
	private int x, y;
	
	public EnemyBullet(double x, double y){
		bullet = new Rectangle((int) x, (int) y, 5, 5);
		setX((int) x); 
		setY((int) y); 
	}
	
	/**
	 * Metoda odpowiadaj¹ca za wyœwietlenie pojedyñczego pocisku na ekranie.
	 * 
	 * @param g2d obiekt graficzny
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		if (bullet == null) {
			return;
		}
		
		g2d.setColor(Color.RED);
		g2d.fill(bullet);
	}

	/**
	 * Metoda odpowiadaj¹ca za aktualizacje po³o¿enia pojedyñczego pocisku na ekranie.
	 * Sprawdzenie czy nast¹pi³a kolizja z bloczkami, albo czy pocisk wyszed³ poza ekran.
	 * @param delta wspó³czynnik p³ynnoœci.
	 * 
	 * @param blocks bloczki
	 * @param player gracz
	 */
	@Override
	public void update(double delta, Blocks blocks, Player player) {
		if (bullet == null) {
			return;
		}
		
		setY((int) (getY() + (1 * speed))); 
		bullet.y = getY();
		
		isOutofBounds();
		wallCollide(blocks);
	}

	@Override
	public boolean collision(Rectangle rect) {
		if (bullet != null && bullet.intersects(rect)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean destory() {
		
		return false;
	}

	/**
	 * Metoda odpowiadaj¹ca za sprawdzenie czy dochodzi do kolizji pocisku
	 * przeciwnika z bloczkiem.
	 * 
	 * @param blocks bloczki
	 */
	@Override
	protected void wallCollide(Blocks blocks) {
		if (bullet == null) {
			return;
		}
		
		for (int w = 0; w < blocks.wall.size(); w++) {
			if(bullet.intersects(blocks.wall.get(w))) {
				blocks.wall.remove(w);
				bullet = null;
				break;
			}
		}
	}

	@Override
	protected void isOutofBounds() {
		if(bullet != null && bullet.y < 0 || bullet.y > Okno.HEIGHT || bullet.x < 0 || bullet.x > Okno.WIDTH){
			bullet = null;
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBullet() {
		return bullet;
	}
}
