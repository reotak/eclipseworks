package ex01_16;

import java.io.FileInputStream;
import java.io.IOException;

class MyUtilities {
	public double [] getDataSet(String setName)
		throws BadDataSetException
	{
		String file = setName + ".dest";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException("in", e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				;
			}
		}
	}

	private double[] readDataSet(FileInputStream in) {
		// 何もせずnullを返す
		return null;
	}
}