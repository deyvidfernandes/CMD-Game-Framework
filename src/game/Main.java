package game;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		
		while(true) {
			try {
				Game.draw();
				Game.processInput();
				Game.updateLogic();
			} catch (Exception exc) {
				System.out.println("Erro");
			}
		}
	}
	
	
}
