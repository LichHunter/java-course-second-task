package com.second.task;

import junit.framework.TestCase;
import org.junit.Test;

public class MathTest extends TestCase {

	@Test
	public void testAdd() {
		Math math = new Math();
		assertEquals(3, math.add(1, 2));
	}

	@Test
	public void testMultiply() {
		Math math = new Math();
		assertEquals(6, math.multiply(2, 3));
	}
}