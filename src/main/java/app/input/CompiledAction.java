package app.input;

import java.util.List;
import java.util.ArrayList;

public class CompiledAction {
	private ArrayList<BasicAction> basicActionsList = new ArrayList<BasicAction>();
	private String representation; 
	private int turnsToRun;
	
	public CompiledAction(ArrayList<BasicAction> basicActionsList, String Representation) {
		this.basicActionsList = basicActionsList;
		this.representation = representation;
		this.turnsToRun = basicActionsList.size();
	}
	
	public CompiledAction(BasicAction basicAction, String Representation) {
		this.basicActionsList = new ArrayList<BasicAction>();
		basicActionsList.add(basicAction);
		this.turnsToRun = 1;
		this.representation = representation;
	}
	
	public CompiledAction(ArrayList<BasicAction> basicActionsList) {
		this.basicActionsList = basicActionsList;
		this.turnsToRun = basicActionsList.size();
	}
	
	public CompiledAction(BasicAction basicAction) {
		this.basicActionsList = new ArrayList<BasicAction>();
		basicActionsList.add(basicAction);
		this.turnsToRun = 1;
	}
	
	public int getTurnsToRun() {
		return this.turnsToRun;
	}
	
	public BasicAction getAction(int index) {
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
