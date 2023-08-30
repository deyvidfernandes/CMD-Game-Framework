package app.action;

import app.Game.actionInputs;
import app.Game.directionInputs;

public interface UnitaryAction {
	public directionInputs getDirection();
	public actionInputs getAction();
}
