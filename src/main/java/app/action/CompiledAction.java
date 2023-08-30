package app.action;

import java.util.List;
import java.util.ArrayList;

public class CompiledAction {
	private ArrayList<UnitaryAction> basicActionsList = new ArrayList<UnitaryAction>();
	private int turnsToRun;
	
	public CompiledAction(ArrayList<UnitaryAction> basicActionsList) {
		this.basicActionsList = basicActionsList;
		this.turnsToRun = basicActionsList.size();
	}
	
	public CompiledAction(UnitaryAction basicAction) {
		this.basicActionsList = new ArrayList<UnitaryAction>();
		basicActionsList.add(basicAction);
		this.turnsToRun = 1;
	}
	
	public int getTurnsToRun() {
		return this.turnsToRun;
	}
	
	public UnitaryAction getAction(int index) {
		return this.basicActionsList.get(index);
	}
	
//	public String getString() {
//		String productString = "";
//		for (int i = 0; i < this.basicActionsList.size(); i++) {
//			BasicAction basicAction = basicActionsList.get(i);
//			productString += basicAction.action + basicAction.direction;
//			if (i < this.basicActionsList.size() - 1) {
//				productString += "-";
//			}
//		}
//		
//		return productString;
//	}
//	
}
