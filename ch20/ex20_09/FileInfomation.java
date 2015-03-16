package ex20_09;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileInfomation {

	public static void printFilesInfomation(List<File>files) {
		int fileCount = 1;
		for (File f : files) {
			System.out.println("--- " + fileCount++ + "つめのファイルの情報 ---");
			if (!f.exists()) {
				System.out.println("存在していないファイルです");
				continue;
			}

			System.out.println("名前 ： " + f.getName());
			System.out.println("パス ： " + f.getPath());
			System.out.println("読み込み可能か ： " + f.canRead());
			System.out.println("書き込み可能か ： " + f.canWrite());
			System.out.println("ファイルか : " + f.isFile());
			System.out.println("ディレクトリか : " + f.isDirectory());
			System.out.println("絶対パスか :" + f.isAbsolute());
			System.out.println("隠しファイルか : " + f.isHidden());
		}
	}

	public static void main(String[] args) {
		List<File> files = new ArrayList<File>();
		files.add(new File("ex20_09"));
		files.add(new File("ex20_09/FileInfomation.java"));

		printFilesInfomation(files);
	}
}
