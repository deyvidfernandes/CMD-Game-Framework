
public class Rock extends Entity {
	Rock(Map map, int[] position) {
		super(map, "rock", position);
	}

	Rock(Map map, int x, int y) {
		super(map, "rock", x, y);
	}
}
