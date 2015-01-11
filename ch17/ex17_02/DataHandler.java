package ex17_02;

import java.io.File;
import java.lang.ref.WeakReference;

class DataHandler {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	byte[] readFile(final File file) {
		byte[] data;

		if (file == null) {
			throw new IllegalArgumentException("file is null");
		}

		File lastFileAsFile = null;
		if (lastFile != null) {
			lastFileAsFile= lastFile.get();
		}
		if (lastFileAsFile != null && file.equals(lastFileAsFile)) {
			data = lastData.get();
			if (data != null) {
				return data;
			}
		}

		data = readBytesFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	// ファイル名のバイトの配列表現を返す（実装しない）
	public static byte[] readBytesFromFile(File file) {
		String name = file.getName();
		return name.getBytes();
	}


	// 本当にGCされるかどうかが未定義なため、テストではなくmainで回収されているかを確認する。
	public static void main(String[] args) {
		File file = new File("C:/Users/ymt/Dropbox/java/eclipseworks/ch17/ex17_02/DataHandler.java");

		DataHandler handler = new DataHandler();
		byte[] data = handler.readFile(file);

		File nowFile = handler.lastFile.get();
		String nowName = nowFile != null ? nowFile.getName() : null;
		if (nowName != null) {
			System.out.println("GCの実行前 nowName : " + nowName);
		} else {
			System.out.println("GCの実行前ですが nowName が null です");
		}

		nowFile = null;
		nowName = null;

		file = null;
		Runtime.getRuntime().gc();

		nowFile = handler.lastFile.get();
		nowName =  nowFile != null ? nowFile.getName() : null;

		if (nowName != null) {
			System.out.println("GCを実行しましたが、オブジェクトは回収されていません");
		} else {
			System.out.println("GCを実行したところ、オブジェクトは回収されていました");
		}


	}

}