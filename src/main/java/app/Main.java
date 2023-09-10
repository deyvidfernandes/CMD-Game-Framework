package app;

public class Main {
	
	public static void main(String[] args) {
		while(true) {
			try {
				Game.draw();
				Game.updateLogic();
			} catch (Exception exc) {
				exc.printStackTrace();
				System.out.println("Erro");
			}
		}
	}
	
	
}
