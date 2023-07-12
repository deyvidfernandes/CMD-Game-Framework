package game;


public class Tile {
	
	private Entity content;
	private String floor;
	private int[] position;
	private Map current_map;
	
	Tile(Map current_map, Entity content, String floor, int[] position) {
		this.current_map = current_map;
		this.content = content;
		this.floor = floor;
		this.position = position;
	}

	Tile(Map current_map, Entity content, String floor, int x, int y) {
		final int[] position = {x, y};
		this.current_map = current_map;
		this.content = content;
		this.floor = floor;
		this.position = position;
	}
	
	Tile(Map current_map, String floor, int[] position) {
		this.current_map = current_map;
		this.floor = floor;
		this.position = position;
	}

	Tile(Map current_map, String floor, int x, int y) {
		final int[] position = {x, y};
		this.current_map = current_map;
		this.floor = floor;
		this.position = position;
	}
	
	public void setContent(Entity content) {
		this.content = content;
	}

	public String getFloor() {
		return this.floor;
	}
	
	public Entity getContent() {
		return this.content;
	}

	public void removeContent() {
		this.content = null;
	}

	public boolean isEmpty() {
		if (content != null) {
			return false;
		} else {
			return true;
		}
	}
	
}
