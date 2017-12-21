package levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import enemy.EnemyBasic;
import enemy.EnemyInterface;
import game_Screen.Blocks;
import game_Screen.Player;
import handler.Enemy_handler;

/**
 * Klasa odpowiedzialna za obs�uge poziomu gry, a dok�adniej za przeciwnik�w oraz ich zachowanie.
 * 
 * @author Ja_Karol
 *
 */
public class Level_1 implements Levels_interface{

	private Enemy_handler bulletHandler;
	private Player player;
	private ArrayList<EnemyInterface> enemies = new ArrayList<EnemyInterface>();
	
	public Level_1(Player player, Enemy_handler bulletHandler){
		this.player = player;
		this.bulletHandler = bulletHandler;
		addEnemies();
	}
	
	/**
	 * Metoda odpowiedzialna za dodanie przeciwnik�w.
	 * 
	 */
	public void addEnemies (){
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 10; x++){
				EnemyInterface e = new EnemyBasic(150 + (x * 40), 25 + (y * 40), 1, 3, bulletHandler);
				enemies.add(e);
			}
		}
	}
	
	/**
	 * Metoda odpowiedzialna za wy�wietlenie przeciwnik�w oraz pocisk�w przeciwnik�w
	 * 
	 * @param g2d obiekt graficzny
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		if(enemies == null)
			return;
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).rysuj(g2d);
		}
		bulletHandler.rysuj(g2d);
	}

	/**
	 * Metoda odpowiedzialna za aktualizacje przeciwnik�w oraz pocisk�w przeciwnik�w.
	 * 
	 * @param delta wsp�czynnik p�ynno�ci
	 * @param blocks obiekt graficzny
	 */
	@Override
	public void update(double delta, Blocks blocks) {
		if(enemies == null)
			return;
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update(delta, player, blocks);
		}
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).collide(i, player, blocks, enemies);
		}
		hasDirectionChange(delta);
		bulletHandler.update(delta, blocks, player);
	}

	/**
	 * Metoda odpowiedzialna za sprawdzanie czy trzeba zmieni� kierunek
	 * poruszania si� przeciwnik�w.
	 * 
	 * @param delta wsp�czynnik p�ynno�ci
	 */
	@Override
	public void hasDirectionChange(double delta) {
		if(enemies == null)
			return;
		
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).isOutofBounds()){
				changeDirectionEnemies(delta);
			}
		}
	}

	/**
	 * Metoda odpowiedzialna za zmian� kierunku przeciwnik�w.
	 * Nastepuje wywo�anie metody "changeDirection" z obiektu "enemies".
	 * 
	 * @param delta wsp�czynnik p�ynno�ci
	 */
	@Override
	public void changeDirectionEnemies(double delta) {
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).changeDirection(delta);
		}
	}

	@Override
	public void reset() {
		player.reset();
		enemies.clear();
		bulletHandler.reset();
		addEnemies();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public boolean isGameOver() {
		return player.getHealth() <= 0;
	}

	
}
