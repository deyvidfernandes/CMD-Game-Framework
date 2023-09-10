package app.action;

import app.Coordinate;
import app.Game.RangedActionInputs;
import app.Game.directionInputs;
import app.xutility.Xenum;

public class RangedAction implements UnitaryAction {
	private directionInputs direction;
	private Coordinate relativeCoord;
	private RangedActionInputs action;
	private boolean goThroughObstacle = true;
	
	public RangedAction(Coordinate relativeCoord, String action) {
		this.relativeCoord = relativeCoord;
		this.action = Xenum.convertToConstant(RangedActionInputs.values(), action);
	}
	
	public directionInputs getDirection() {
		return direction;
	}

	public RangedActionInputs getAction() {
		return action;
	}
	
	public Coordinate getRelativeCoord() {
		return this.relativeCoord;
	}
	
	public boolean getGoThroughObstacle() {
		return this.goThroughObstacle;
	}

}
