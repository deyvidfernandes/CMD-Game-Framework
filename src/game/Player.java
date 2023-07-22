package game;

public final class Player extends Character {
	Player(Map map, int x, int y) {
		super(map, "player", x, y);
		map.setPlayer(this);
	}
	
	private boolean checkMine(int x, int y) {
		try {
			return true;
		}
		catch (java.lang.ArrayIndexOutOfBoundsException error) {
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
	
	public boolean act(UserInput userInput) {
		
		switch(userInput.getAction()) {
		case "h":
			hit(userInput.getDir());
			return true;
		case "w":
			try {
				this.walk(userInput.getDir());
			} catch(xutility.exceptions.InvalidMethodInput exc) {
				exc.printStackTrace();
				return false;
			}
			return true;
		default:
			return false;
		}
	}
	
}
