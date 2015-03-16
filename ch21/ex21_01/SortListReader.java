package ex21_01;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SortListReader {
	private List<String> list;

	public SortListReader(File f) throws IOException {
		list = new LinkedList<String>();

		FileReader in = new FileReader(f);

		int inputChar;
		String line = "";
		while ((inputChar = in.read()) != -1) {
			char c = (char)inputChar;
			if (c == '\n' || c == '\r') {
				addList(line);
				line = "";
			} else {
				line += c;
			}
		}

		in.close();
	}

	public List<String> getList() {
		List<String> clone = new LinkedList<String>();

		for (String elem : list) {
			clone.add(elem);
		}
		return clone;
	}

	private void addList(String s) {
		int l = 0;
		int r = list.size();

		for (;;) {
			int c = (l + r) / 2;
			if (r <= l) {
				list.add(c, s);
				break;
			}

			if (s.compareTo(list.get(c)) > 0) {
				l = c + 1;
			} else {
				r = c;
			}
		}
	}

	public String toString() {
		String result = "";
		for (String s : list) {
			result += s + "\n";
		}

		return result;
	}
}