package app;

import app.Game.CloseActionInputs;
import app.Game.directionInputs;
import app.action.CloseAction;
import app.action.RangedAction;
import app.action.UnitaryAction;

public final class Player extends Character {
	Player(GameMap map, int x, int y) {
		super(map, "player", x, y);
		map.setPlayer(this);
	}
	
	private boolean checkMine(int x, int y) {
		try {
			return true;
		}
		catch (ArrayIndexOutOfBoundsException error) {
		   return false;
		}
	}
	
	public boolean hit(directionInputs dir) {

		switch (dir) {
		case W:
			if (this.checkMine(0, -1)) {
				current_map.getTile(this.position[0], this.position[1] - 1).getContent().receiveDamage(1);
			} else {
				return false;
			}
			break;
		case S:
			if (this.checkMine(0, 1)) {
				current_map.getTile(this.position[0], this.position[1] + 1).getContent().receiveDamage(1);	
			} else {
				return false;
			}
			break;
		case A:
			if (this.checkMine(-1, 0)) {
				current_map.getTile(this.position[0] - 1, this.position[1]).getContent().receiveDamage(1);
			} else {
				return false;
			}
			break;
		case D:
			if (this.checkMine(1, 0)) {
				current_map.getTile(this.position[0] + 1, this.position[1]).getContent().receiveDamage(1);
			} else {
				return false;
			}
			break;
		default:
			return false;
		}
		this.current_map.getTile(this.position).setContent(this);
		return true;
		
	}
	
	
	public boolean act(UnitaryAction genericUnitaryAction) {
		if (genericUnitaryAction instanceof CloseAction) {
			CloseAction closeUnitaryAction = (CloseAction) genericUnitaryAction;
			return doCloseAction(closeUnitaryAction);
		} else if (genericUnitaryAction instanceof RangedAction) {
			RangedAction closeUnitaryAction = (RangedAction) genericUnitaryAction;
			return true; //doRangedAction(closeUnitaryAction);
		}
		return true;
	}
	
	private boolean doCloseAction(CloseAction closeUnitaryAction) {
		switch(closeUnitaryAction.getAction()) {
		case H:
			hit(closeUnitaryAction.getDirection());
			return true;
		case W:
			try {
				this.walk(closeUnitaryAction.getDirection());
			} catch(IllegalArgumentException exc) {
				exc.printStackTrace();
				return false;
			}
				return true;
		default:
			return false;
		}
	}
	
	private boolean doRangedAction(CloseAction closeUnitaryAction) {
		switch(closeUnitaryAction.getAction()) {
		case H:
			hit(closeUnitaryAction.getDirection());
			return true;
		case W:
			try {
				this.walk(closeUnitaryAction.getDirection());
			} catch(IllegalArgumentException exc) {
				exc.printStackTrace();
				return false;
			}
			return true;
		default:
			return false;
		}
	}
}
