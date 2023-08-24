package app;
import java.util.Arrays;

import app.Game.directionInputs;
import app.xutility.*;

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
	
	public Boolean walk(directionInputs direction) throws IllegalArgumentException {
		return this.move(direction);
	}
			
}
