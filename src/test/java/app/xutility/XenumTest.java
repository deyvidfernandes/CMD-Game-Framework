package app.xutility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class XenumTest {
	
	enum testEnum { A, B, C }

	@Test
	void testContainsStringElement() {
		assert(Xenum.containsStringElement(testEnum.values(), "A"));
	}

	@Test
	void testConvertToConstant() {
		assertEquals(Xenum.convertToConstant(testEnum.values(), "A"), testEnum.A);
	}

}
