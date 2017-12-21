package states;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game_Screen.Game_screen;
import menu_Screen.Instruction;
import menu_Screen.MenuScreen;

/**
 * Klasa odpowiada za inicjalizacje wszystkich stworzonych stanów gry: menu,
 * instrukcji sterowania oraz w³aœciwej gry. Stany te odpowiadaj¹ trzem klas¹. 
 * 
 * @author Ja_Karol
 *
 */
public class StateMachine {
	private ArrayList<StateMachineInterface> states = new ArrayList<StateMachineInterface>();
	private Canvas canvas;
	private int selectState = 0;
	public StateMachine (Canvas canvas){ 
		this.canvas = canvas;
		StateMachineInterface game = new Game_screen(this);
		StateMachineInterface menu = new MenuScreen(this);
		StateMachineInterface sterowanie = new Instruction(this);
		states.add(menu);
		states.add(game);
		states.add(sterowanie);
	}
	
	public void rysuj(Graphics2D g2d){
		states.get(selectState).rysuj(g2d);
	}
	
	public void update(double delta){
		states.get(selectState).update(delta);
	}
	
	public void setStateMachine(int i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		selectState = i;
		states.get(selectState).init(canvas);
	}

	public int  getStates() {
		return selectState;
	}
	
	
}
