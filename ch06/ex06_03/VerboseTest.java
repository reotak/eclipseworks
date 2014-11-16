package ex06_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerboseTest {

	@Test
	public void test() {
		Verbose impl = new Verbose() {
			Level level = Verbose.Level.NORMAL;
			public void setVerbosity(Verbose.Level level) {
				this.level = level;
			}
			public Level getVerbosity() {
				return level;
			}
		};
		
		impl.setVerbosity(Verbose.Level.SILENT);
		assertEquals(Verbose.Level.SILENT, impl.getVerbosity());
		
		impl.setVerbosity(Verbose.Level.VERBOSE);
		assertEquals(Verbose.Level.VERBOSE, impl.getVerbosity());
	}

}
