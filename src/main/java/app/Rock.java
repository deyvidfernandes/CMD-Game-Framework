package app;

public class Rock extends Entity {
	
	Rock(GameMap map, int[] position) {
		super(map, "rock", position);
		this.health = 1;
	}

	Rock(GameMap map, int x, int y) {
		super(map, "rock", x, y);
		this.health = 1;

	}
	

}
