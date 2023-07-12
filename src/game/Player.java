package game;

public final class Player extends Character {
	Player(Map map, int x, int y) {
		super(map, "player", x, y);
		map.setPlayer(this);
	}
	
	private boolean checkMine(int x, int y) {
		try {
			return true; //this.current_map.getTile(this.position[0] + x, this.position[1] + y).getContent().getName().equals("rock");
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
	
	public boolean act(String entry) throws Exception {
		char char0 = entry.charAt(0);
		char char1 = entry.charAt(1);
		
		switch(char0) {
		case 'h':
			hit(String.valueOf(char1));
			return true;
		case 'w':
			try {
				this.walk(String.valueOf(char1));
			} catch(xutility.exceptions.invalidMethodInput exc) {
				System.out.println(exc.getMessage());
				exc.printStackTrace();
				return false;
			}
			return true;
		default:
			return false;
		}
	}
	
}
