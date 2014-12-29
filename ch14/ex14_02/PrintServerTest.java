package ex14_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrintServerTest {

	@Test(expected = RuntimeException.class)
	public void test_run_IllegalCalled() {
		PrintServer server = new PrintServer();
		server.run();

		fail();
	}

}
