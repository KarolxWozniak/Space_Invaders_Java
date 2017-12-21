package game_Screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za utworzenie 4 bloczków ( w postaci list przechowywuj¹cych
 * pojedyñcze cegie³ki).
 * 
 * @author Ja_Karol
 *
 */
public class Blocks {

	public ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
	
	public Blocks(){
		buildBlock(70, 480);
		buildBlock(270, 480);
		buildBlock(470, 480);
		buildBlock(670, 480);
	}
	
	/**
	 * Metoda odpowiedzialna za wyœwietlenie bloczków.
	 * 
	 * @param g2d obiekt graficzny
	 */
	public void rysuj(Graphics2D g2d){
		g2d.setColor(Color.YELLOW);
		for(int i = 0; i < wall.size(); i++)
			g2d.fill(wall.get(i));
	}
	
	/**
	 * Metoda odpowiedzialna za geometryczne stworzenie bloczków.
	 * 
	 * @param x po³o¿enie
	 * @param y po³o¿enie
	 */
	public void buildBlock(int x, int y){
		int wallWidth = 3;
		int _x = 0, _y = 0;
		
		//Glowna czesc bloczka
		for(int i = 0; i < 13; i++)
		{
			if((14 +(i * 2)) + wallWidth < 22 + wallWidth){
				row(14 +(i * 2) + wallWidth, x - (i * 3), y + (i * 3));
				_x = (i * 3) + 3;
			}else{
				row(22 + wallWidth, x - _x, y + (i * 3));
				_y = i * 3;
			}
		}
		
		//Dolna lewa wypustka bloczka
		for(int i = 0; i < 5; i++)
			row(8 + wallWidth - i, (x - _x), (y + _y) + (i * 3));
		
		//Dolna prawa wypustka bloczka
		for(int i = 0; i < 5; i++)
			row(8 + wallWidth - i, (x - _x + (14 * 3) + (i * 3)), (y + _y) + (i * 3));
	}
	
	/**
	 * Metoda odpowiedzialna za stworzenie pojedyñczego wiersza, z³o¿onego z cegie³ek, 
	 * tworz¹cego czêœæ bloczka.
	 * 
	 * @param rows wiersze
	 * @param x po³o¿enie
	 * @param y po³o¿enie
	 */
	public void row(int rows, int x, int y){
		for(int i = 0; i < rows; i++){
			Rectangle brick = new Rectangle(x + (i * 3), y, 3, 3);
			wall.add(brick);
		}
	}
	
	public void reset(){
		wall.clear();
		
		buildBlock(70, 480);
		buildBlock(270, 480);
		buildBlock(470, 480);
		buildBlock(670, 480);
	}
}
