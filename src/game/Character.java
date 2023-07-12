package game;
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
	
	public Boolean walk(String dir) throws xutility.exceptions.invalidMethodInput {
		return this.move(dir);
	}
			
}
