package menu_Screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Okno.Okno;
import states.StateMachine;
import states.StateMachineInterface;

/**
 * Klasa odpowiada za wyœwietlenie na ekranie instrukcji jak graæ w grê.
 * Dziedziczy z klasy abstrakcyjnej "StateMachineInterface".
 * 
 * @author Ja_Karol
 *
 */
public class Instruction extends StateMachineInterface implements KeyListener {

	private Font tittleFont = new Font("Arial", Font.PLAIN, 32);
	private Font st_1Font = new Font("Arial", Font.PLAIN, 32);
	private Font st_2Font = new Font("Arial", Font.PLAIN, 32);
	private Font st_3Font = new Font("Arial", Font.PLAIN, 32);
	private String tittle = "STEROWANIE";
	private String st_1 = "* Klawisze 'W','A','S','D' lub strza³ki - poruszanie statkiem";
	private String st_2 = "* Klawisz 'Spacja' - strelanie ze statku";
	private String st_3 = "* Klawisz 'Esc' - cofniêcie siê do Menu";
	private int tittleWidth, st_1Width, st_2Width, st_3Width;
	public Instruction(StateMachine stateMachine) {
		super(stateMachine);
		
	}

	@Override
	public void update(double delta) {
		
	}

	/**
	 * Metoda odpowiada za wyœwietlenie napisów na ekranie.
	 * 
	 * @param g2d obiekt graficzny
	 *
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		g2d.setFont(tittleFont);
		tittleWidth = g2d.getFontMetrics().stringWidth(tittle);
		g2d.setColor(Color.green);
		g2d.drawString(tittle, (Okno.WIDTH/2)-(tittleWidth/2), (Okno.HEIGHT/2)-195);
		
		g2d.setFont(st_1Font);
		g2d.setColor(Color.white);
		st_1Width = g2d.getFontMetrics().stringWidth(st_1);
		g2d.drawString(st_1, (Okno.WIDTH/2)-(st_1Width/2), (Okno.HEIGHT/2)-25);
		
		g2d.setFont(st_2Font);
		g2d.setColor(Color.white);
		st_2Width = g2d.getFontMetrics().stringWidth(st_2);
		g2d.drawString(st_2, (Okno.WIDTH/2)-(st_2Width/2), (Okno.HEIGHT/2)+75);
		
		g2d.setFont(st_3Font);
		g2d.setColor(Color.white);
		st_3Width = g2d.getFontMetrics().stringWidth(st_3);
		g2d.drawString(st_3, (Okno.WIDTH/2)-(st_3Width/2), (Okno.HEIGHT/2)+175);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			getStateMachine().setStateMachine((int) 0);
		}		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
