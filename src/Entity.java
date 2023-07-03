
public abstract class Entity {
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
	
}
