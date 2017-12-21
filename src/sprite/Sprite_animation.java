package sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import timer.Timer;

/**
 * Klasa odpowiedzialna za animacje przeciwników.
 * Przechowuje liste obiektów typu "BufferedImage".
 * 
 * @author Ja_Karol
 *
 */
public class Sprite_animation {
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private boolean loop = false;
	private boolean play = false;
	private boolean destroy_after_anim = false;

	private Timer timer;
	private int animationSpeed, width, height, limit;
	private double x, y;
	private byte currentSprite;
	
	/**
	 * Pocz¹tkowe wype³nienie listy obiektami graficznymi, które
	 * reprezentuj¹ poszczególne stany animacji. 
	 * 
	 * @param x wspó³rzêdne
	 * @param y wspó³rzêdne
	 * @param rows wiersze
	 * @param columns kolumny
	 * @param animationSpeed prêdkoœæ animacji w milisekundach
	 * @param img obrazek
	 */
	public Sprite_animation(double x, double y, int rows, int columns, int animationSpeed, String img){
		this.animationSpeed = animationSpeed;
		this.x = x;
		this.y = y;
		
		try{
			URL url = this.getClass().getResource(img);
			BufferedImage pSprite = ImageIO.read(url);
			int spriteWidth = pSprite.getWidth() / columns;
			int spriteHeight = pSprite.getHeight() / rows;
			for(int _y = 0; _y < rows; _y++){
				for( int _x = 0; _x < columns; _x++){
					addSprite(pSprite, 0+(_x * spriteWidth), 0 +(_y * spriteHeight), spriteWidth, spriteHeight);
				}
			}
			
		}catch(IOException e){};
		
		timer = new Timer();
		limit = sprites.size() - 1;
	}
	
	
	/**
	 * Metoda odpowiada za wyœwietlenie na ekranie obrazka symbolizuj¹cego przeciwnika.
	 * 
	 * @param g2d obiekt graficzny
	 */
	public void rysuj(Graphics2D g2d){
		if(isSpriteAnimDestroyed())
			return;
		g2d.drawImage(sprites.get(currentSprite), (int)getX(), (int)getY(), width, height, null);
	}
	
	
	/**
	 * Metoda odpowiada za aktualizacje animacji przeciwników.
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 */
	public void update(double delta){
		if(isSpriteAnimDestroyed())
			return;
		
		if(loop && !play){
			loopAnimation();
		}
		if(play && !loop){
			playAnimation();
		}
	}
	
	public void stopAnimation(){
		loop = false;
		play = false;
	}
	
	public void resetSprite(){
		loop = false;
		play = false;
		currentSprite = 0;
	}
	
	/**
	 * Metoda odpowiada za przebieg animacji zniszczenia przeciwnika poprzez odpowiednie ustawianie flag.
	 */
	private void playAnimation(){
		if(timer.isTimerReady(animationSpeed) && currentSprite != limit && !isDestroy_after_anim()){
			play = false;
			currentSprite = 0;
		}
		else if(timer.isTimerReady(animationSpeed) && currentSprite == limit && isDestroy_after_anim()){
			sprites = null;
		}else if(timer.timerEvent(animationSpeed) && currentSprite != limit){
			currentSprite++;
		}
	}
	
	/**
	 * Metoda odpowiada za zapêtlenie animacji poprzez odpowiednie ustawienie flag.
	 */
	private void loopAnimation(){
		if(timer.isTimerReady(animationSpeed) && currentSprite == limit){
			currentSprite = 0;
			timer.resetTimer();
		}else if(timer.timerEvent(animationSpeed) && currentSprite != limit){
			currentSprite++;
		}
	}
	
	
	
	public boolean isLoop() {
		return loop;
	}


	public void setLoop(boolean loop) {
		this.loop = loop;
	}


	public byte getCurrentSprite() {
		return currentSprite;
	}


	public void setCurrentSprite(byte currentSprite) {
		this.currentSprite = currentSprite;
	}


	public boolean isSpriteAnimDestroyed(){
		if(sprites == null)
			return true;
		
		return false;
	}
	public void addSprite(BufferedImage spriteMap, int x, int y, int width, int height){
		sprites.add(spriteMap.getSubimage(x, y, width, height));
	}
	
	
	public void setPlay(boolean play, boolean destroy_after_anim){
		if(loop){
			loop = false;
		}
		this.play = play;
		this.setDestroy_after_anim(destroy_after_anim);
		
	}


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


	public boolean isDestroy_after_anim() {
		return destroy_after_anim;
	}


	public void setDestroy_after_anim(boolean destroy_after_anim) {
		this.destroy_after_anim = destroy_after_anim;
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


	public int getAnimationSpeed() {
		return animationSpeed;
	}


	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		if(limit > 0){
			this.limit = limit - 1;
		}else{
			this.limit = limit;
		}
	}

	public void resetLimit(){
		limit = sprites.size() - 1;
	}
	public boolean isPlay() {
		return play;
	}
	
	
}
