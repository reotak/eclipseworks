package ex20_05;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FindWord {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new IllegalArgumentException("need word and file");
		}

		String match = args[0];
		FileReader fileIn = new FileReader(args[1]);
		LineNumberReader in = new LineNumberReader(fileIn);

		int ch;
		String line = "";
		int nLine = 0;
		while ((ch = in.read()) != -1) {
			if (nLine != in.getLineNumber()) {
				if (line.indexOf(match) != -1) {
					System.out.println(nLine + " : " + line);
				}
				nLine = in.getLineNumber();
				line = "";
			} else {
				line += (char)ch;
			}
		}

		in.close();
	}
}
