package handler;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import enemy_bullets.Enemy_weapon_abstract;
import game_Screen.Blocks;
import game_Screen.Player;


/**
 * Klasa odpowiedzialna za utworzenie oraz obs�uge listy przechowuj�cej obiekty typu
 * "Enemy_weapon_abstract", czyli pocisk�w wystrzelone przez przeciwnik�w.
 * 
 * @author Ja_Karol
 *
 */
public class Enemy_handler {

private List<Enemy_weapon_abstract> weaponTypes = new ArrayList<>();
	
	public void addBullet(Enemy_weapon_abstract weaponType) {
		this.weaponTypes.add(weaponType);
	}

	/**
	 * Metoda odpowiadaj�ca za wy�wietlenie pocisk�w na ekranie.
	 * Nast�puje wywo�anie metody rysuj() z obiektu "weaponTypes".
	 * 
	 * @param g2d obiekt graficzny
	 */
	public void rysuj(Graphics2D g2d) {
		for (Enemy_weapon_abstract enemyWeaponType : weaponTypes) {
			enemyWeaponType.rysuj(g2d);
		}
	}
	
	/**
	 * Metoda odpowiada za aktualizacje pozycji dla wszystkich pocisk�w.
	 * Sprawdza czy nast�pi�a kolizja pocisku ze statkiem gracza.
	 * Nast�puje wywo�anie metody update() z obiektu "weaponTypes".
	 * 
	 * @param delta wsp�czynnik p�ynno�ci
	 * @param blocks bloczek
	 * @param player gracz
	 */
	public void update(double delta, Blocks blocks, Player player) {
		for (int i = 0; i < weaponTypes.size(); i++) {
			weaponTypes.get(i).update(delta, blocks, player);
			if (weaponTypes.get(i).collision(player.getRect())) {
				weaponTypes.remove(i);
				player.hit();
			}
		}
		
	}
	
	public void reset() {
		weaponTypes.clear();
	}
	
	
}
