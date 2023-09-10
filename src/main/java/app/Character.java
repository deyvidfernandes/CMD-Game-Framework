package app;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import app.Game.CloseActionInputs;
import app.Game.directionInputs;
import app.xutility.*;
import app.action.UnitaryAction;

public class Character extends Entity {	
	private String character_type;
	private Map<CloseActionInputs, Function<UnitaryAction, Boolean>> behaviorMap = new HashMap<>();
	
	Character(GameMap map, String character_type, int[] position) {
		super(map, character_type, position);
		this.character_type = character_type;
	}

	Character(GameMap map, String character_type, int x, int y) {
		super(map, character_type, x, y);
		this.character_type = character_type;
	}
	
	public Boolean walk(directionInputs direction) throws IllegalArgumentException {
		return this.move(direction);
	}
			
}
