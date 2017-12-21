package game_Screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import Okno.Okno;

/**
 * Klasa odpowiadaj¹ca za stworzenie i wszystkie w³aœciwoœci statku
 * gracza, niezbêdne do prawid³owego dzia³ania aplikacji.
 * Zaimplementowana obs³uga klawiszy.
 * 
 * @author Ja_Karol
 *
 */
public class Player implements KeyListener{

	private double x, y, startX, startY;
	private int width, height;
	private BufferedImage pSprite;
	private Rectangle rect;
	private boolean left = false;
	private boolean right = false;
	private boolean shoot = false, shoot_L = false, shoot_R = false;
	private final double speed = 0.7;
	private int health;
	public static int kierunek = 0, kierunek_L=0, kierunek_R=0, pomKierunek=0;
	public static int flaga_L = 0, flaga_R=0;
	
	private Blocks blocks;
	
	public Player_weapon playerWeapon, playerWeapon_L, playerWeapon_R;
	
	/**
	 * Ustawienie wymiarów statku oraz obrazka go przedstawiaj¹cego.
	 * 
	 * @param x wspó³rzêdne
	 * @param y wspó³rzêdne
	 * @param width szerokoœc
	 * @param height wysokoœc
	 * @param blocks bloczek
	 */
	public Player(double x, double y, int width, int height, Blocks blocks){
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		this.width = width;
		this.height = height;
		this.health = 3;
		
		rect = new Rectangle((int)x, (int)y, width, height);
		
		try{
			URL url = this.getClass().getResource("/images/Player.png");
			pSprite = ImageIO.read(url);
		}catch(IOException e){};
		
		this.blocks = blocks;
		playerWeapon = new Player_weapon();
		
	}

	/**
	 * Metoda odpowiadaj¹ca za wyœwietlenie grafiki reprezentuj¹cej statek gracza.
	 * Nastêpuje wywo³anie metody rysuj() z obiektu "playerWeapon".
	 * 
	 * @param g2d obiekt graficzny
	 */
	public void rysuj(Graphics2D g2d){
		g2d.drawImage(pSprite, (int)x, (int)y, width, height, null);
		playerWeapon.rysuj(g2d);

	}
	
	/**
	 * Metoda odpowiadaj¹ca za aktualizacje po³o¿enia statku gracza.
	 * Nastêpuje wywo³anie metody update() z obiektu "playerWeapon".
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 */
	public void update(double delta){
		if(right && !left && x < Okno.WIDTH - width){
			x += speed * delta;
			rect.x = (int) x;
		}if(!right && left && x > 10){
			x -= speed * delta;
			rect.x = (int) x;
		}
		playerWeapon.update(delta, blocks, kierunek);
		
		if(shoot){
			playerWeapon.shootBullet(x, y, 5, 5);
		}
		if(shoot_L){
			playerWeapon.shootBullet(x, y, 5, 5);
		}
		if(shoot_R){
			playerWeapon.shootBullet(x, y, 5, 5);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
			right = true;
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
			left = true;
		
		if(key == KeyEvent.VK_SPACE){
			kierunek = 0;
			shoot = true;
		}
		if(key == KeyEvent.VK_Q){
			
			if(flaga_L==0){
				kierunek = -1;//-1
			}
			if(flaga_L==1){
				kierunek = -2;//-2
			}
			
			shoot_L = true;
		}
			
		if(key == KeyEvent.VK_E){
			
			if(flaga_R==0){
				kierunek = 1;//1
			}
			if(flaga_R==1){
				kierunek = 2;//2
			}
			
			shoot_R = true;
		}
			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
			right = false;
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
			left = false;
		if(key == KeyEvent.VK_SPACE){
			flaga_L=0;
			flaga_R=0;
		}
			shoot = false;
		if(key == KeyEvent.VK_Q){
			if(flaga_L==0){
				
				flaga_L= 1;
				flaga_R=0;
			}
			if(flaga_L==1){
				flaga_R=0;
			}
			shoot_L = false;
		}
			
		if(key == KeyEvent.VK_E){
			if(flaga_R==0){
				
				flaga_R=1;
				flaga_L=0;
			}
			if(flaga_R==1){
				flaga_L=0;
			}
			shoot_R = false;
		}
			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void reset() {
		health = 3;
		left = false;
		right = false;
		shoot=false;
		shoot_L=false;
		shoot_R=false;
		
		x = startX;
		y = startY;
		rect.x = (int) x;
		rect.y = (int) y;
		playerWeapon.reset();
	}
	
	public void hit() {
		setHealth(getHealth()-1);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	public Rectangle getRect() {
		return rect;
	}
}
