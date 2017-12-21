package player_bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import Okno.Okno;
import game_Screen.Blocks;

/**
 * Klasa odpowiedzialna za utworzenie i obs³uge pojedyñczego pocisku wystrzelonego
 * przez gracza.
 * 
 * @author Ja_Karol
 *
 */
public class Gun extends Player_weapon_abstract{

	private Rectangle bullet;
	private final double speed = 0.8;
	
	public Gun(double x, double y, int width, int height){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		
		this.bullet = new Rectangle((int)getX(),(int)getY(), getWidth(), getHeight());
	}
	
	/**
	 * Metoda odpowiedzialna za wyœwietlenie pojedyñzego pocisku.
	 * 
	 * @param g2d obiekt graficzny
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		if(bullet == null)
			return;
		g2d.setColor(Color.CYAN);
		g2d.fill(bullet);
	}

	/**
	 * Metoda odpowiedzialna za aktualizacje po³o¿enia pojedyñczego pocisku.
	 * Nastêpuje sprawdzenie czy pocisk zderzy³ siê z bloczkiem oraz
	 * czy nie wyszed³ poza okno.
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 * @param blocks bloczki
	 */
	@Override
	public void update(double delta, Blocks blocks, int kierunek) {
		if(bullet == null)
			return;
		if(kierunek==0){
			this.setY(getY() - (delta * speed));
			bullet.y = (int) this.getY();
			bullet.x = (int) this.getX();
		}
		if(kierunek==-1){
			this.setX(getX() - 0.6*(delta * speed));
			this.setY(getY() - (delta * speed));
			bullet.y = (int) this.getY();
			bullet.x = (int) this.getX();
		}
		if(kierunek==-2){
			this.setX(getX() - 1.2*(delta * speed));
			this.setY(getY() - (delta * speed));
			bullet.y = (int) this.getY();
			bullet.x = (int) this.getX();
		}
		if(kierunek==1){
			this.setX(getX() + 0.6*(delta * speed));
			this.setY(getY() - (delta * speed));
			bullet.y = (int) this.getY();
			bullet.x = (int) this.getX();
		}
		if(kierunek==2){
			this.setX(getX() + 1.2*(delta * speed));
			this.setY(getY() - (delta * speed));
			bullet.y = (int) this.getY();
			bullet.x = (int) this.getX();
		}
		

		wallCollision(blocks);
		isOut();
	}

	/**
	 * Metoda odpowiedzialna za reagowanie na kolizje z pociskiem wystrzelonym
	 * przez statek gracza.
	 * 
	 * @param rect obiekt przestrzenny
	 */
	@Override
	public boolean collisionRect(Rectangle rect) {
		if(this.bullet == null)
			return false;
		if(bullet.intersects(rect)){
			this.bullet = null;
			return true;
		}
		return false;
	}

	
	@Override
	public boolean collisionPoly(Polygon poly) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean destroy() {
		if(bullet == null)
			return true;
		
		return false;
	}

	/**
	 * Metoda odpowiedzialna za reagowanie na kolizje pocisku wystrzelonego przez statek
	 * gracza z cegie³kami tworz¹cymi bloczek.
	 * 
	 * @param blocks bloczki
	 */
	@Override
	protected void wallCollision(Blocks blocks) {
		for(int i = 0; i < blocks.wall.size(); i++){
			if(bullet.intersects(blocks.wall.get(i))){
				blocks.wall.remove(i);
				bullet = null;
				return;
			}
		}
	}

	@Override
	protected void isOut() {
		if(this.bullet == null)
			return;
		if(bullet.y < 0 || bullet.y > Okno.HEIGHT || bullet.x < 0 || bullet.x > Okno.WIDTH){
			bullet = null;
		}
		
	}

}
