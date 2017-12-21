package player_bullets;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import game_Screen.Blocks;

public abstract class Player_weapon_abstract {

	protected double x, y;
	protected int width, height;
	
	public abstract void rysuj(Graphics2D g2d);
	public abstract void update(double delta, Blocks blocks, int kierunek);
	public abstract boolean collisionRect(Rectangle rect);
	public abstract boolean collisionPoly(Polygon poly);
	public abstract boolean destroy();
	
	protected abstract void wallCollision(Blocks blocks);
	protected abstract void isOut();
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
