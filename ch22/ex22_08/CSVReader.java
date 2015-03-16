package ex22_08;

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

		String illegalExpr = createCSVIllegalExpr(cellSize);
		Pattern illegalPat = Pattern.compile(illegalExpr, Pattern.MULTILINE);

		String nonEmptyExpr = "^.*";
		Pattern nonEmptyPat = Pattern.compile(nonEmptyExpr, Pattern.MULTILINE);

		while (in.hasNextLine()) {

			// 長すぎる行の判定
			String illegalLine = in.findInLine(illegalPat);
			if (illegalLine != null && illegalLine.length() >= 1) {
				throw new IOException("セル数が多すぎる行があります : " + illegalLine);
			}

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
				String nonEmptyLine = in.findInLine(nonEmptyPat);
				if (nonEmptyLine != null) {
					throw new IOException("input format error");
				} else {
					// 空行の場合
					in.nextLine();
					continue;
				}
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

	private static String createCSVIllegalExpr(int cells) {
		return createCSVExpr(cells) + ",";
	}
}
