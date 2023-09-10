package app.userInput;

import app.userInput.InputHandler;
import app.userInput.command.CommandType;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InputHandlerTest {

	@Test
	void testIdentifyCommandType() {
		assertEquals(InputHandler.identifyCommandType("--wa2"), CommandType.META_COMMAND);
		assertEquals( InputHandler.identifyCommandType("wa2"), CommandType.GAME_COMMAND);
	}

}
