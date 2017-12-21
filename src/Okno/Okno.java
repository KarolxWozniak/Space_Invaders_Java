package Okno;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import states.StateMachine;

/**
 * Klasa Okno dziedziczy z Canvas, kt�ra odpowiada za udost�pnienie powierzchni
 * ekranu do dowolnych operacji graficznych oraz implementuje interfejs Runnable, kt�ry
 * mo�e by� wykonany w osobnym w�tku.
 * 
 * @author Ja_Karol
 *
 */
public  class Okno extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Jest tu tworzone okno w kt�rym u�ytkownik b�dzie m�g� gra�.
	 * Ustawiane s� wszystkie niezb�dne parametry.
	 * 
	 * @param args args
	 */
	public static void main(String[] args)
	{
		Okno okno = new Okno();
		JFrame frame = new JFrame ();
		frame.add(okno);
		frame.pack();
		frame.setTitle("Gra: Space Invaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		okno.start();
	}
	

	public boolean dziala = false;
	public Thread watek;
	public static int WIDTH = 800, HEIGHT = 600; 
	public static int FPS;
	public static int IsShoot = 0; 
	public static StateMachine state;
	
	public synchronized void start(){
		if(dziala)
			return;
		dziala = true;
		watek = new Thread(this);
		watek.start();
	}
	
	public synchronized void stop(){
		if(!dziala)
			return;
		dziala = false;
		
		try {
			watek.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public Okno(){
		this.setSize(WIDTH,HEIGHT);
		this.setFocusable(true);
		state = new StateMachine(this);
		state.setStateMachine((int) 0);
	}
	
	
	/**
	 * Metoda, kt�ra odpowiada za odpowiedni� p�ynno�� na ekranie.
	 * Wywo�uje 2 metody: rysuj(), update().
	 * 
	 */
	public void run() {
		long timer = System.currentTimeMillis();
		long LastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final int OPTIMAL_TIME = 100000000 / TARGET_FPS;
		int frames = 0;
		
		this.createBufferStrategy(3);
		BufferStrategy BS = this.getBufferStrategy();
		
		while(dziala){
			long now = System.nanoTime();
			long updateLength = now - LastLoopTime;
			LastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			//System.out.println(delta);
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				FPS = frames;
				frames = 0;
			}
			
			rysuj(BS);
			update(delta);
			
			try{
				Thread.sleep(((LastLoopTime - System.nanoTime())+ OPTIMAL_TIME) / 1000000);
			}catch(Exception e){};
			//Odpowiada to za u�pienie aplikacji na okre�lony czas - FPS
		}
	}
	
	/**
	 * Metoda odpowiada za rysowanie obiekt�w na ekranie, wykorzystuje do tego
	 * obiekty typu Graphics2D ,kt�re odpowiadaj� za renderowanie 2-wymiarowych kszta�t�w.
	 * Nast�puje wywo�anie metody rysuj() z obiektu "state".
	 * 
	 * @param BS bufor dla obiekt�w graficznych
	 */
	public void rysuj(BufferStrategy BS){
		do{
			do{
				Graphics2D g2d = (Graphics2D)BS.getDrawGraphics();
				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);
				
				state.rysuj(g2d);
				
				g2d.dispose();
			}while(BS.contentsRestored());
			BS.show();
		}while(BS.contentsLost());
	}
	
	/**
	 * Metoda odpowiada za aktualizowanie obiekt�w oraz wydarze� na ekranie.
	 * Nast�puje wywo�anie metody update() z obiektu "state".
	 * 
	 * @param delta wsp�czynnik p�ynno�ci
	 */
	public void update(double delta){
		state.update(delta);
	}
}
