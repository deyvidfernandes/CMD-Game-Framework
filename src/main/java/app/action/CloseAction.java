package app.action;

import app.Game.actionInputs;
import app.Game.directionInputs;
import app.xutility.Xenum;

public class CloseAction implements UnitaryAction {
	private directionInputs direction;
	private actionInputs action;
	
	public CloseAction(String direction, String action) {
		this.direction = Xenum.convertToConstant(directionInputs.values(), direction);
		this.action = Xenum.convertToConstant(actionInputs.values(), action);
	}
	
	public directionInputs getDirection() {
		return direction;
	}

	public actionInputs getAction() {
		return action;
	}
}