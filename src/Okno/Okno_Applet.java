package Okno;

import java.awt.BorderLayout;

import javax.swing.JApplet;

/**
 * Klasa g³ówna, pocz¹tkowa która dziedziczy z JApplet.
 * Posiada ona 3 metody.
 * Tworzona jest w niej zmienna typu Okno.
 *  
 * @author Ja_Karol
 *
 */
public class Okno_Applet extends JApplet{

	private static final long serialVersionUID = 1L;
	
	private Okno okno = new Okno();
	
	public void init(){
		setLayout(new BorderLayout());
		add(okno);
	}
	
	public void start(){
		okno.start();
	}
	
	public void stop(){
		okno.stop();
	}
	
}
