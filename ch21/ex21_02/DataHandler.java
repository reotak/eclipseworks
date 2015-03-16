package ex21_02;

import java.io.File;
import java.util.WeakHashMap;

class DataHandler {
	private WeakHashMap<File, byte[]> fileMap = new WeakHashMap<File, byte[]>();

	byte[] readFile(final File file) {
		byte[] data;

		if (file == null) {
			throw new IllegalArgumentException("file is null");
		}

		// データを記録していたら、そのデータを返す
		if ((data = fileMap.get(file)) != null) {
			return data;
		}

		// 記録していないので保持する
		data = readBytesFromFile(file);
		fileMap.put(file, data);

		return data;
	}

	// ファイル名のバイトの配列表現を返す（実装しない）
	public static byte[] readBytesFromFile(File file) {
		String name = file.getName();
		return name.getBytes();
	}

	public static void main(String[] args) {
		File file = new File("ch21/ex21_02/DataHandler.java");

		DataHandler handler = new DataHandler();
		byte[] data = handler.readFile(file);

		data = handler.fileMap.get(file);

		if (handler.fileMap.size() != 0) {
			System.out.println("GCの実行前なので、オブジェクトは回収されていません");
		} else {
			System.out.println("GCの実行前ですが、オブジェクトが回収されています");
		}


		file = null;
		Runtime.getRuntime().gc();

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (handler.fileMap.size() != 0) {
			System.out.println("GCを実行しましたが、オブジェクトは回収されていません");
		} else {
			System.out.println("GCを実行したところ、オブジェクトは回収されていました");
		}


	}

}
