package ex20_10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
	public static HashMap<String, Integer> createWordCountMap(File f) throws IOException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		StreamTokenizer in = new StreamTokenizer(new FileReader(f));

		in.slashSlashComments(false);
		in.slashStarComments(false);
		in.whitespaceChars('.', '.');
		in.whitespaceChars(',', ',');

		in.wordChars('a', 'z');
		in.wordChars('A', 'Z');

		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.ttype == StreamTokenizer.TT_WORD) {
				if (map.containsKey(in.sval)) {
					int count = map.get(in.sval);
					map.put(in.sval, count + 1);
				} else {
					map.put(in.sval, 0);
				}
			}
		}

		return map;
	}

	public static void main(String[] args) {
		if (args.length != 1) {

			usage();
			System.exit(1);
		}

		HashMap<String, Integer> map = null;
		try {
			map = createWordCountMap(new File(args[0]));
		} catch (IOException e) {
			System.out.println("ファイルが開けませんでした ： " + e.toString());
			System.exit(1);
		}

		for (Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue().toString());
		}
	}

	public static void usage() {
		System.out.println("java WordConter FILE_PASS");
	}
}
