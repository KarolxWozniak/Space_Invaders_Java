package game_Screen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import player_bullets.Gun;
import player_bullets.Player_weapon_abstract;
import timer.Timer;

/**
 * Klasa odpowiedzialna za stworzenie i obs³uge listy obiektów "weapons" typu "Player_weapon_abstract".
 * Obiekty te przechowuj¹ pozycje ka¿dego pocisku wystrzelonego przez statek gracza.
 * 
 * @author Ja_Karol
 *
 */
public class Player_weapon {

	private int i = 0;
	private Timer timer;
	public ArrayList<Player_weapon_abstract> weapons = new ArrayList<Player_weapon_abstract>();
	
	public  Player_weapon(){
		timer = new Timer();
	}
	
	/**
	 * Metoda odpowiedzialna za wyœwietlenie wszystkich wystrzelonych, przez gracza,
	 * pocisków na ekranie. Nastêpuje wywo³anie metody rysuj() z obiektu "weapons".
	 * 
	 * @param g2d obiekt graficzny
	 */
	public void rysuj(Graphics2D g2d){
		for(int i = 0; i < weapons.size(); i++)
			weapons.get(i).rysuj(g2d);
	}
	
	/**
	 * Metoda odpowiedzialna za aktualizacje wszystkich wystrzelonych, przez gracza,
	 * pocisków. Nastêpuje wywo³anie metody update() z obiektu "weapons" oraz sprawdzenie czy pocisk
	 * nie zosta³ zniszczony.
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 * @param blocks bloczek
	 */
	public void update(double delta, Blocks blocks, int kierunek){
		for(int i = 0; i < weapons.size(); i++){
			weapons.get(i).update(delta, blocks, kierunek);
			if(weapons.get(i).destroy())
				weapons.remove(i);
		}
	}
	
	public void shootBullet(double x, double y, int width, int height){
		if(timer.timerEvent(350))
		{
			weapons.add(new Gun(x + 24 + i, y + 15, width, height));
		}
	}

	public void reset() {
		weapons.clear();
	}
}
