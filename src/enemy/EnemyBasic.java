package enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import Okno.Okno;
import enemy_bullets.EnemyBullet;
import game_Screen.Blocks;
import game_Screen.Game_screen;
import game_Screen.Player;
import handler.Enemy_handler;
import sprite.Sprite_animation;
import timer.Timer;

/**
 * Klasa odpowiedzialna za stworzenie i obs³uge pojedyñczego przeciwnika.
 * @author Ja_Karol
 *
 */
public class EnemyBasic extends EnemyInterface{

	private Rectangle rect;
	private Sprite_animation enemySprite;
	private int shootTime;
	private Timer shootTimer;
	private double speed = 0.2d;
	
	/**
	 * Ustawia parametry niezbêdne do graficznego wyœwietlenia przeciwnika.
	 * @param x wspó³rzêdne
	 * @param y wspó³rzêdne
	 * @param rows wiersze
	 * @param columns kolumny
	 * @param bulletHandler pociski przeciwników
	 */
	public EnemyBasic(double x, double y, int rows, int columns, Enemy_handler bulletHandler){
		super(bulletHandler);
		enemySprite = new Sprite_animation(x, y, rows, columns, 300, "/images/Invaders.png");
		enemySprite.setWidth(25);
		enemySprite.setHeight(25);
		enemySprite.setLimit(2);
		
		this.setRect(new Rectangle((int) enemySprite.getX(), (int) enemySprite.getY(), enemySprite.getWidth(), enemySprite.getHeight()));
		enemySprite.setLoop(true);
		
		shootTimer = new Timer();
		shootTime = new Random().nextInt(12000);
	}
	
	/**
	 * Metoda odpowiedzialna za wyœwietlenie przeciwnika.
	 * 
	 * @param g2d obiekt graficzny
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		enemySprite.rysuj(g2d);
	}

	/**
	 * Metoda odpowiedzialna za aktualizacje po³o¿enia przeciwnika oraz
	 * za wystrzelenie przez niego pocisku w losowym momencie.
	 * 
	 * @param delta obiekt graficzny
	 * @param player gracz
	 * @param blocks bloczki
	 */
	@Override
	public void update(double delta, Player player, Blocks blocks) {
		enemySprite.update(delta);
		
		enemySprite.setX(enemySprite.getX() - (delta * speed));
		this.getRect().x = (int) enemySprite.getX();
		
		if (shootTimer.timerEvent(shootTime)){
			getBulletHandler().addBullet(new EnemyBullet(getRect().x, getRect().y));
			shootTime = new Random().nextInt(12000);
		}
	}

	/**
	 * Metoda odpowiedzialna za zmianê kierunku pojedyñczego przeciwnika.
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 */
	@Override
	public void changeDirection(double delta) {
		speed *= -1.05d;
		enemySprite.setX(enemySprite.getX() - (delta * speed));
		this.getRect().x = (int) enemySprite.getX();
		
		enemySprite.setY(enemySprite.getY() + (1 * 5));
		this.getRect().y = (int) enemySprite.getY();
	}

	/**
	 * Metoda odpowiedzialna za reagowanie na kolizje przeciwników z pociskami
	 * wystrzelonymi przez gracza oraz za rozpoczêcie animacji zniszczenia w razie
	 * tej¿e kolizji.
	 * 
	 * @param i integer
	 * @param player gracz
	 * @param blocks bloczki
	 * @param enemys lista przeciwników
	 */
	@Override
	public boolean collide(int i, Player player, Blocks blocks, ArrayList<EnemyInterface> enemys) {
		if(enemySprite.isPlay()){
			if(enemys.get(i).deathScene()){
				//System.out.println("NISZCZY");
				//System.out.println(i);
				enemys.remove(i);
			}
			return false;
		}
		
		for(int w = 0; w < player.playerWeapon.weapons.size(); w++) {
			if(enemys != null && player.playerWeapon.weapons.get(w).collisionRect(((EnemyBasic) enemys.get(i)).getRect())) {
				enemySprite.resetLimit();
				enemySprite.setAnimationSpeed(120);
				enemySprite.setPlay(true, true);
				Game_screen.Score += 10;
				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deathScene() {
		if(!enemySprite.isPlay())
			return false;
		
		if(enemySprite.isSpriteAnimDestroyed()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isOutofBounds() {
		if( rect.x > 0 && rect.x < Okno.WIDTH - rect.width )
			return false;
		return true;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

}
