package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class ReadWriteTest extends TestCase{

	@Test
	public void test() {
		int a = 2;
		int b = 3;
		assertTrue(a + b == 5);
	}

}
