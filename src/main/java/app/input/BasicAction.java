package app.input;

import app.Game.actionInputs;
import app.Game.directionInputs;
import app.xutility.Xenum;

public class BasicAction {
	private directionInputs direction;
	private actionInputs action;
	
	public BasicAction(String direction, String action) {
		this.direction = Xenum.convertToConstant(directionInputs.values(), direction);
		this.action = Xenum.convertToConstant(actionInputs.values(), action);
	}
	
	public directionInputs getDirection() {
		return direction;
	}

	public void setDirection(directionInputs direction) {
		this.direction = direction;
	}

	public actionInputs getAction() {
		return action;
	}

	public void setAction(actionInputs action) {
		this.action = action;
	}
}
