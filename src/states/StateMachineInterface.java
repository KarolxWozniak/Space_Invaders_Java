package states;

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class StateMachineInterface {

	private StateMachine stateMachine;

	public StateMachineInterface(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	public abstract void update(double delta);

	public abstract void rysuj(Graphics2D g2d);

	public abstract void init(Canvas canvas);

	public StateMachine getStateMachine() {
		return stateMachine;
	}
}
