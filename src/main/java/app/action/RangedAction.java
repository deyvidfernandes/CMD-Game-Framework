package app.action;

import app.Coordinate;
import app.Game.actionInputs;
import app.Game.directionInputs;
import app.xutility.Xenum;

public class RangedAction implements UnitaryAction {
	private directionInputs direction;
	private Coordinate relativeCoord;
	private actionInputs action;
	private boolean goThroughObstacle = true;
	
	public RangedAction(Coordinate relativeCoord, String action) {
		this.relativeCoord = relativeCoord;
		this.action = Xenum.convertToConstant(actionInputs.values(), action);
	}
	
	public directionInputs getDirection() {
		return direction;
	}

	public actionInputs getAction() {
		return action;
	}
	
	public Coordinate getRelativeCoord() {
		return this.relativeCoord;
	}
	
	public boolean getGoThroughObstacle() {
		return this.goThroughObstacle;
	}

}
