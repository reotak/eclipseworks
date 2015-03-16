package ex20_11;

import java.io.File;
import java.io.FilenameFilter;

public class DirExtensionFilter implements FilenameFilter {
	private final String extension;

	public DirExtensionFilter(String extension) {
		if (extension == null || extension.length() == 0) {
			throw new IllegalArgumentException("extension is null or empty");
		}

		if (extension.charAt(0) == '.') {
			extension = extension.substring(1);
		}

		this.extension = extension;
	}

	public boolean accept(File dir, String name) {
		return getExtension(name).equals(extension);
	}

	private String getExtension(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			return filename.substring(index + 1);
		} else {
			return filename;
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
			System.exit(1);
		}

		String dirName = args[0];
		File dir = new File(dirName);
		if (!dir.isDirectory()) {
			usage();
			System.exit(1);
		}

		String extension = args[1];

		String[] files = dir.list(new DirExtensionFilter(extension));
		for (String file : files) {
			System.out.println(file);
		}
	}

	private static void usage() {
		System.out.println("usage : java DirExtensionFilter DIR_NAME EXTENSION");
	}

}
