import java.util.Arrays;

public class Character extends Entity {	
	private String character_type;
	
	Character(Map map, String character_type, int[] position) {
		super(map, character_type, position);
		this.character_type = character_type;
	}

	Character(Map map, String character_type, int x, int y) {
		super(map, character_type, x, y);
		this.character_type = character_type;
	}
	
	private boolean checkMovment(int x, int y) {
		try {
			return this.current_map.getTile(this.position[0] + x, this.position[1] + y).isEmpty();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException error) {
		   return false;
		}
	}
	
	public Boolean walk(String dir) {

		switch (dir) {
		case "w":
			if (this.checkMovment(0, -1)) {
				this.current_map.getTile(this.position).removeContent();
				this.position[1] -= 1;
			} else {
				return false;
			}
			break;
		case "s":
			if (this.checkMovment(0, 1)) {
				this.current_map.getTile(this.position).removeContent();
				this.position[1] += 1;
			} else {
				return false;
			}
			break;
		case "a":
			if (this.checkMovment(-1, 0)) {
				this.current_map.getTile(this.position).removeContent();
				this.position[0] -= 1;
			} else {
				return false;
			}
			break;
		case "d":
			if (this.checkMovment(1, 0)) {
				this.current_map.getTile(this.position).removeContent();
				this.position[0] += 1;
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
			
}
