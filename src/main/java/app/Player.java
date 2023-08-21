package app;

import app.input.BasicAction;
import app.xutility.exceptions.InvalidMethodInput;

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
	
	public boolean hit(String dir) {

		switch (dir) {
		case "w":
			if (this.checkMine(0, -1)) {
				current_map.getTile(this.position[0], this.position[1] - 1).getContent().receiveDamage(1);
			} else {
				return false;
			}
			break;
		case "s":
			if (this.checkMine(0, 1)) {
				current_map.getTile(this.position[0], this.position[1] + 1).getContent().receiveDamage(1);	
			} else {
				return false;
			}
			break;
		case "a":
			if (this.checkMine(-1, 0)) {
				current_map.getTile(this.position[0] - 1, this.position[1]).getContent().receiveDamage(1);
			} else {
				return false;
			}
			break;
		case "d":
			if (this.checkMine(1, 0)) {
				current_map.getTile(this.position[0] + 1, this.position[1]).getContent().receiveDamage(1);
			} else {
				return false;
			}
			break;
		case "terminate":
			return false;
		default:
			return false;
		}
		this.current_map.getTile(this.position).setContent(this);
		return true;
		
	}
	
	public boolean act(BasicAction basicAction) {
		
		switch(basicAction.action) {
		case "h":
			hit(basicAction.direction);
			return true;
		case "w":
			try {
				this.walk(basicAction.direction);
			} catch(InvalidMethodInput exc) {
				exc.printStackTrace();
				return false;
			}
			return true;
		default:
			return false;
		}
	}
	
}
