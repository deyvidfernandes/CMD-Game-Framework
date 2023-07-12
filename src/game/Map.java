package game;


public class Map {
	
	private int size;
	private Tile[][] data;
	private Player player;
	
	Map(int size) {
		data = new Tile[size][size];
		this.size = size;
		for (int y = 0; y < size; y++) {
			
			for (int x = 0; x < size; x++) {
				data[y][x] = new Tile(this, "rock", x, y);
//				Rock rock = new Rock(this, x, y);
//				this.addEntity(rock);
			}
			
		}
		
	}
	
	public boolean addEntity(Entity entity) { 
		int[] entityPos = entity.getPos();
		if (!data[entityPos[1]][entityPos[0]].isEmpty()) {
			data[entityPos[1]][entityPos[0]].setContent(entity);
			return true;
		} else {
			return false;
		}
	}

	public void setPlayer(Player player) { 
		this.player = player;
	}

	public Player getPlayer() { 
		return this.player;
	}
	
	public Tile getTile(int[] position) {
		return data[position[1]][position[0]];
	}
	
	public Tile getTile(int x, int y) {
		return data[y][x];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String draw() {
		String render_map = "";
		
		for (int y = 0; y < size; y++) {
				
			render_map += "\t│";

			for (int x = 0; x < size; x++) {
				if (this.data[y][x].isEmpty()) {
					render_map += " ·";
				} else {
					switch(this.data[y][x].getContent().getName()) {
						case "rock":
							render_map += " #";
							break;
						case "player":
							render_map += " @";
							break;
						default:
							render_map += " X";
							break;
					}
				}
			}
			render_map += " │";
			if (y != size - 1) {
				render_map += "\n";
			}
		}
		
		return render_map;
	}

}
