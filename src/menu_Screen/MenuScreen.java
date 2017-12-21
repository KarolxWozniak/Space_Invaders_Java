package menu_Screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Okno.Okno;
import states.StateMachine;
import states.StateMachineInterface;

/**
 * Klasa odpowiada za wyœwietlenie na ekranie menu z którego mo¿na przejœæ do instrukcji
 * oraz do samej gry.
 * 
 * @author Ja_Karol
 *
 */
public class MenuScreen extends StateMachineInterface implements MouseListener, KeyListener {

	private Font tittleFont = new Font("Arial", Font.PLAIN, 64);
	private Font startFont = new Font("Arial", Font.PLAIN, 32);
	private Font sterowanieFont = new Font("Arial", Font.PLAIN, 32);
	private String tittle = "Space Invaders";
	private String start = "Graj";
	private String sterowanie = "Sterowanie";
	private int tittleWidth, startWidth, sterowanieWidth;
  
	 
	public MenuScreen(StateMachine stateMachine) {
		super(stateMachine);
		

	}

	@Override
	public void update(double delta) {

	}
	
	/**
	 * Metoda odpowiada za wyœwietlenie napisów na ekranie.
	 * 
	 * @param g2d obiekt graficzny
	 */
	@Override
	public void rysuj(Graphics2D g2d) {
		g2d.setFont(tittleFont);
		tittleWidth = g2d.getFontMetrics().stringWidth(tittle);
		g2d.setColor(Color.green);
		g2d.drawString(tittle, (Okno.WIDTH/2)-(tittleWidth/2), (Okno.HEIGHT/2)-200);
		
		g2d.setFont(startFont);
		g2d.setColor(Color.white);
		startWidth = g2d.getFontMetrics().stringWidth(start);
		g2d.drawString(start, (Okno.WIDTH/2)-(startWidth/2), (Okno.HEIGHT/2)-50);
		
		g2d.setFont(sterowanieFont);
		g2d.setColor(Color.white);
		sterowanieWidth = g2d.getFontMetrics().stringWidth(sterowanie);
		g2d.drawString(sterowanie, (Okno.WIDTH/2)-(sterowanieWidth/2), (Okno.HEIGHT/2)+50);
		
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addMouseListener(this);
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getStateMachine().setStateMachine((int) 1);
		}
	}		

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		if (x > (Okno.WIDTH/2)-(startWidth/2) && x < (Okno.WIDTH/2)-(startWidth/2) + startWidth && y > (Okno.HEIGHT/2)-50-32 && y < (Okno.HEIGHT/2)-50){
			Okno.IsShoot=1;
			getStateMachine().setStateMachine((int) 1);
		}
		if (x > (Okno.WIDTH/2)-(sterowanieWidth/2) && x < (Okno.WIDTH/2)-(sterowanieWidth/2) + sterowanieWidth && y > (Okno.HEIGHT/2)+50-32 && y < (Okno.HEIGHT/2)+50){
			getStateMachine().setStateMachine((int) 2);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	

}

