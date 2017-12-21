package game_Screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Okno.Okno;
import handler.Enemy_handler;
import levels.Level_1;
import states.StateMachine;
import states.StateMachineInterface;
import timer.TimerAfterGame;

/**
 * Klasa odpowiada za dzia³anie gry i wywo³anie wszystkich metod
 * niezbêdnych do w³aœciwej gry. Jednoczeœnie tworz¹ca obiekty
 * odpowiedzialne za obs³ugê pocisków przeciwników, bloczki, 
 * statek gracza oraz dzia³anie poziomu.
 * Dziedziczy z klasy abstrakcyjnej "StateMachineInterface".
 * 
 * @author Ja_Karol
 *
 */
public class Game_screen extends StateMachineInterface {

	private Player player;
	private Blocks blocks;
	private Level_1 level;
	private Enemy_handler bulletHandler;
	private TimerAfterGame timerAfterGame = new TimerAfterGame(1500);
	
	private Font GameOver = new Font("Arial",Font.PLAIN, 48);
	
	public static int Score = 0;

	public Game_screen(StateMachine stateMachine){
		super(stateMachine);
		blocks = new Blocks();
		bulletHandler = new Enemy_handler();
		player = new Player(Okno.WIDTH/2 - 30, Okno.HEIGHT/2 + 270 , 50, 30, blocks);
		level = new Level_1(player, bulletHandler);
		
	}
	
	/**
	 * Metoda odpowiada za aktualizacje pozycji gracza oraz dzia³anie poziomu na ekranie.
	 * Nastêpuje wywo³anie metody update() z obiektów "level" oraz "player".
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 */
	@Override
	public void update(double delta) {
		player.update(delta);
		level.update(delta, blocks);
		
		if(level.isGameOver()){
			timerAfterGame.tick(delta);
			if(timerAfterGame.isEventReady()){
				level.reset();
				blocks.reset();
				getStateMachine().setStateMachine((int) 0);
				Score = 0;
			}
		}
	}

	/**
	 * Metoda odpowiada za wyœwietlanie statku gracza, bloczków oraz poziomu gry.
	 * Nastêpuje wywo³anie metody rysuj() z obiektów "level", "player" oraz "blocks".
	 * 
	 * @param g2d obiekt graficzny
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.drawString("WYNIK: " + Score, 5, 15);
		
		g2d.setColor(Color.red);
		g2d.drawString("Zycie: " + player.getHealth(), 760, 15);
		
		//String S_FPS = Integer.toString(Okno.FPS);
		//g2d.setColor(Color.WHITE);
		//g2d.drawString("FPS: " + S_FPS, Okno.WIDTH/2, 15);
		
		player.rysuj(g2d);
		blocks.rysuj(g2d);
		level.rysuj(g2d);
		
		if(level.isGameOver()){
			g2d.setColor(Color.BLUE);
			g2d.setFont(GameOver);
			String napis = "KONIEC GRY";
			int napis_szerokosc = g2d.getFontMetrics().stringWidth(napis);
			g2d.drawString(napis, Okno.WIDTH/2 - napis_szerokosc/2, Okno.HEIGHT/2);
			
		}
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
	}

}
