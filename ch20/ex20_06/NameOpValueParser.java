package ex20_06;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class NameOpValueParser {
	private final StreamTokenizer in;

	private final HashMap<String, Double> map;

	public NameOpValueParser(StreamTokenizer in) throws IOException, ParseException {
		if (in == null) {
			throw new IllegalArgumentException("in is null");
		}
		this.in = in;
		this.map = new HashMap<String, Double>();

		in.wordChars('a', 'z');
		in.wordChars('A', 'Z');

		run();
	}

	private void run() throws IOException, ParseException {
		String preWord = "";
		char preOp = 0;

		while (in.nextToken() != StreamTokenizer.TT_EOF) {

			if (in.ttype == '='
					|| in.ttype == '+'
					|| in.ttype == '-') {
				preOp = (char)in.ttype;
			}
			else if (in.ttype == StreamTokenizer.TT_WORD) {
				preWord = in.sval;
			}
			else if (in.ttype == StreamTokenizer.TT_NUMBER) {
				if (preOp == '=') {
					map.put(preWord, in.nval);
				} else if (preOp == '+') {
					if (!map.containsKey(preWord)) {
						throw new ParseException("未定義なnameに + 演算子は使えません", in.lineno());
					}

					double value = map.get(preWord);
					value += in.nval;
					map.put(preWord, value);

				} else if (preOp == '-') {
					if (!map.containsKey(preWord)) {
						throw new ParseException("未定義なnameに - 演算子は使えません", in.lineno());
					}

					double value = map.get(preWord);
					value -= in.nval;
					map.put(preWord, value);
				} else {
					throw new ParseException("オペランドが指定されていません", in.lineno());
				}
				preWord = "";
				preOp = 0;
			}
		}
	}

	public HashMap<String, Double> getResult() {
		return map;
	}

	public String toString() {
		String result = "";

		for (Map.Entry<String, Double> e : map.entrySet()) {
			result += e.getKey() + " : " + e.getValue().toString() + "\n";
		}
		return result;
	}


	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		String filename = "sample.dat";
		FileReader reader = new FileReader(filename);
		NameOpValueParser parser = new NameOpValueParser(new StreamTokenizer(reader));
		System.out.println(parser.toString());
		reader.close();
	}
}
