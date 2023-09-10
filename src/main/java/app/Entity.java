package app;

import app.Game.directionInputs;
import app.xutility.Xarray;

public abstract class Entity {
	protected int health;
	protected String name;
	protected int[] position = new int[2];
	protected GameMap current_map;

	Entity(GameMap map, String name, int[] position) {
		this.current_map = map;
		this.name = name;
		this.position = position;
		this.current_map.getTile(position).setContent(this);
	}

	Entity(GameMap map, String name, int x, int y) {
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
	
	
	private int[] convertDirToXY(directionInputs direction) throws IllegalArgumentException {
		switch (direction) {
			case  W:
				return new int[] {0, -1};
			case S:
				return new int[] {0, 1};
			case A:
				return new int[] {-1, 0};
			case D:
				return new int[] {1, 0};
			default:
				throw new IllegalArgumentException(direction.name() + " is not a valid argument for convertDirToXY(directionInputs direction)");
		}
	}
	
	private boolean checkMovment(int[] positionVariation) {
		try {
			return this.current_map.getTile(this.position[0] + positionVariation[0], this.position[1] + positionVariation[1]).isEmpty();
		}
		catch (ArrayIndexOutOfBoundsException error) {
		   return false;
		}
	}
	
	public boolean move(directionInputs direction) throws IllegalArgumentException {
		int[] positionVariation = this.convertDirToXY(direction);
		Tile current_tile = this.current_map.getTile(this.position);
		
		if (this.checkMovment(positionVariation)) {
			current_tile.removeContent();
			this.position = Xarray.sumInt(this.position, positionVariation);
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
