package app;

import app.Game.actionInputs;
import app.Game.directionInputs;

import app.input.BasicAction;

public final class Player extends Character {
	Player(Map map, int x, int y) {
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
	
	public boolean act(BasicAction basicAction) {
		
		switch(basicAction.getAction()) {
		case H:
			hit(basicAction.getDirection());
			return true;
		case W:
			try {
				this.walk(basicAction.getDirection());
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
