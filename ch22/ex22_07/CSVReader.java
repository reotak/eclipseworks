package ex22_07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
	public static List<String[]> readCSVTable(Readable source, int cellSize) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String expr = createCSVExpr(cellSize);
		Pattern pat = Pattern.compile(expr, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellSize];
				MatchResult match = in.match();
				for (int i = 0; i < cellSize; i++) {
					cells[i] = match.group(i + 1);
				}
				vals.add(cells);
				in.nextLine(); // 改行を読み飛ばし
			} else {
				throw new IOException("input format error");
			}
		}
		IOException ex = in.ioException();
		if (ex != null) {
			throw ex;
		}

		return vals;
	}

	private static String createCSVExpr(int cells) {
		if (cells <= 0) {
			throw new IllegalArgumentException("cells <= 0");
		}

		String expr = "^";
		for (int i = 0; i < cells; i++) {
			if (i != 0) {
				expr += ",";
			}

			expr += "(.*)";
		}

		return expr;
	}
}
