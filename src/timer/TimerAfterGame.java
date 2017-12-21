package timer;

/**
 * Klasa odpowiedzialna za stworzenie i obs³ugowanie timera potrzebnego przy koñczeniu gry.
 * 
 * @author Ja_Karol
 *
 */
public class TimerAfterGame {

private float tick, timerAfterGame;
	
	public TimerAfterGame(float timerAfterGame) {
		this.timerAfterGame = timerAfterGame;
		this.tick = 0;
	}
	
	public void tick(double delta) {
		if (tick <= timerAfterGame) {
			tick += 1 * delta;
		}
	}
	
	public boolean isEventReady() {
		if (tick >= timerAfterGame) {
			resetTimer();
			return true;
		}
		return false;
	}
	
	private void resetTimer() {
		tick = 0;
	}
	
}
