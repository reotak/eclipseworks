package ex21_01;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class SortListReaderTest {

	@Test
	public void sortTest() throws IOException {
		String targetFileName = "ex21_01/SortListReader.java";
		SortListReader slr = new SortListReader(new File(targetFileName));

		assertTrue(isSorted(slr.getList()));
	}

	private boolean isSorted(List<String> list) {

		int size = list.size();

		for (int i = 0; i < size - 2; i++) {
			if (list.get(i).compareTo(list.get(i + 1)) > 0) {
				return false;
			}
		}

		return true;
	}

	@Test(expected=FileNotFoundException.class)
	public void notFoundFileTest() throws IOException {
		String targetFileName = "notFoundFile.java";
		new SortListReader(new File(targetFileName));
	}
}
