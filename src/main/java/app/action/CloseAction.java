package app.action;

import app.Game.CloseActionInputs;
import app.Game.directionInputs;
import app.xutility.Xenum;

public class CloseAction implements UnitaryAction {
	private directionInputs direction;
	private CloseActionInputs action;
	
	public CloseAction(String direction, String action) {
		this.direction = Xenum.convertToConstant(directionInputs.values(), direction);
		this.action = Xenum.convertToConstant(CloseActionInputs.values(), action);
	}
	
	public directionInputs getDirection() {
		return direction;
	}

	public CloseActionInputs getAction() {
		return action;
	}
}