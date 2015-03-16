package ex22_15;

import static org.junit.Assert.*;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void test() throws OperationNotSupportedException {
		assertEquals(new Double(55), new Double(Calculator.calc("0 1 2 3 4 5 6 7 8 9 10 + + + + + + + + + +")));
		assertEquals(new Double(0), new Double(Calculator.calc("0 0 -")));
		assertEquals(new Double(4), new Double(Calculator.calc("2 2 pow")));
		assertEquals(new Double(2), new Double(Calculator.calc("2 2 pow sqrt")));
	}

	@Test(expected=IllegalArgumentException.class)
	public void illegalExprTest() throws OperationNotSupportedException {
		Calculator.calc("0 1 2 3 4 5 6 7 8 9 10");
	}

	@Test(expected=IllegalArgumentException.class)
	public void noNumberTest() throws OperationNotSupportedException {
		Calculator.calc("+ 1 2 +");
	}

	@Test(expected=OperationNotSupportedException.class)
	public void nonDefinedOperationTest() throws OperationNotSupportedException {
		Calculator.calc("1 2 ^");
	}
}
