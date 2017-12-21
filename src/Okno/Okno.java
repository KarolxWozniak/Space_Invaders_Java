package Okno;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import states.StateMachine;

/**
 * Klasa Okno dziedziczy z Canvas, która odpowiada za udostêpnienie powierzchni
 * ekranu do dowolnych operacji graficznych oraz implementuje interfejs Runnable, który
 * mo¿e byæ wykonany w osobnym w¹tku.
 * 
 * @author Ja_Karol
 *
 */
public  class Okno extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Jest tu tworzone okno w którym u¿ytkownik bêdzie móg³ graæ.
	 * Ustawiane s¹ wszystkie niezbêdne parametry.
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
	 * Metoda, która odpowiada za odpowiedni¹ p³ynnoœæ na ekranie.
	 * Wywo³uje 2 metody: rysuj(), update().
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
			//Odpowiada to za uœpienie aplikacji na okreœlony czas - FPS
		}
	}
	
	/**
	 * Metoda odpowiada za rysowanie obiektów na ekranie, wykorzystuje do tego
	 * obiekty typu Graphics2D ,które odpowiadaj¹ za renderowanie 2-wymiarowych kszta³tów.
	 * Nastêpuje wywo³anie metody rysuj() z obiektu "state".
	 * 
	 * @param BS bufor dla obiektów graficznych
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
	 * Metoda odpowiada za aktualizowanie obiektów oraz wydarzeñ na ekranie.
	 * Nastêpuje wywo³anie metody update() z obiektu "state".
	 * 
	 * @param delta wspó³czynnik p³ynnoœci
	 */
	public void update(double delta){
		state.update(delta);
	}
}
