package ex05_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void testToString() {
		BankAccount acc = new BankAccount(1, 1000);

		acc.deposit(100);

		String expect = "1 : 1100\n";
		expect += "  <- 1: deposit 100\n";

		assertEquals(expect, acc.toString());

		acc.withdraw(50);

		expect = "1 : 1050\n";
		expect += "  <- 1: deposit 100\n";
		expect += "  <- 1: withdraw 50\n";
		assertEquals(expect, acc.toString());
	}

	@Test
	public void testHistory() {
		BankAccount acc = new BankAccount(1, 1000);
		for (long num = 0; num <= 20; num++) {
			acc.deposit(num);
		}

		BankAccount.History hist = acc.history();

		for (long num = 11; num <= 20; num++) {
			String expect = "1: deposit " + num;
			if (!hist.next().toString().equals(expect)) {
				fail("履歴が規定件数を超えても正しく削除されていない可能性があります");
			}
		}
	}
}
