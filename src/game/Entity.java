package game;

public abstract class Entity {
	protected int health;
	protected String name;
	protected int[] position = new int[2];
	protected Map current_map;

	Entity(Map map, String name, int[] position) {
		this.current_map = map;
		this.name = name;
		this.position = position;
		this.current_map.getTile(position).setContent(this);
	}

	Entity(Map map, String name, int x, int y) {
		this.current_map = map;
		this.name = name;
		this.position[0] = x;
		this.position[1] = y;
		this.current_map.getTile(position).setContent(this);
	}
	
	public int[] getPos() {
		return this.position;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	private int[] convertDirToXY(String dir) throws xutility.exceptions.invalidMethodInput {
		switch (dir) {
			case "w":
				return new int[] {0, -1};
			case "s":
				return new int[] {0, 1};
			case "a":
				return new int[] {-1, 0};
			case "d":
				return new int[] {1, 0};
			default:
				throw new xutility.exceptions.invalidMethodInput("convertDirToXY", dir);
		}
	}
	
	private boolean checkMovment(int[] positionVariation) {
		try {
			return this.current_map.getTile(this.position[0] + positionVariation[0], this.position[1] + positionVariation[1]).isEmpty();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException error) {
		   return false;
		}
	}
	
	public boolean move(String dir) throws xutility.exceptions.invalidMethodInput {
		int[] positionVariation = this.convertDirToXY(dir);
		Tile current_tile = this.current_map.getTile(this.position);
		
		if (this.checkMovment(positionVariation)) {
			current_tile.removeContent();
			this.position = xutility.vector.sumInt(this.position, positionVariation);
			Tile new_tile = this.current_map.getTile(this.position);
			new_tile.setContent(this);
			return true;
		} else {
			return false;
		}
	}

	public void receiveDamage(int dmg) {
		this.health -= dmg;
		if (health < 0) {
			this.current_map.getTile(this.getPos()).setContent(null);;
		}
	}
	
}
