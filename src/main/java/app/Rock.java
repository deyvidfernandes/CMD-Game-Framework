package app;

public class Rock extends Entity {
	
	Rock(Map map, int[] position) {
		super(map, "rock", position);
		this.health = 1;
	}

	Rock(Map map, int x, int y) {
		super(map, "rock", x, y);
		this.health = 1;

	}
	

}
