package ex22_12;

import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AttributeReader {
	public static Attributed readAttr(Reader source) {
		Scanner in = new Scanner(source);
		String numAttrExpr = "(.+)=([1-9]\\d*|0)(\\.\\d+)?$";
		Pattern numAttrPat = Pattern.compile(numAttrExpr, Pattern.MULTILINE);
		String strAttrExpr = "(.+)=(.+)";
		Pattern strAttrPat = Pattern.compile(strAttrExpr, Pattern.MULTILINE);

		while (in.hasNextLine()) {
			// if hoge=number
			String line = in.findInLine(numAttrPat);
			if (line != null) {
				String[] ss = line.split("=");
				return new AttributedImpl(ss[0], new Double(ss[1]));
			}

			// if hoge=string
			line = in.findInLine(strAttrPat);
			if (line != null) {
				String[] ss = line.split("=");
				return new AttributedImpl(ss[0], ss[1]);
			}

			// else
			in.nextLine();
		}

		return null;
	}


}
