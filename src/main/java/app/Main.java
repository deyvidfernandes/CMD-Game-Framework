package app;

import app.input.ActionInput;

public class Main {
	
	public static void main(String[] args) {
		
//		try {
//		System.out.println(ActionInput.translateComplexActionScript_to_actionScript("5(hd2-(ww))"));
//		System.out.println("XXXXXXXXXXXXXX");
//		} catch (InvalidUserInput e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getId());
//			//e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//System.out.println(ActionInput.translateComplexActionScript_to_actionScript("2(ws2-hd3-wa3)-9(hs-hd2-wd-3(wd1-wa2-hw2)))"));
		while(true) {
			try {
				Game.draw();
				Game.processInput();
				Game.updateLogic();
			} catch (Exception exc) {
				exc.printStackTrace();
				System.out.println("Erro");
			}
		}
	}
	
	
}
