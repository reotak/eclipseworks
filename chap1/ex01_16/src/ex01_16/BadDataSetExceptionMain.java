package ex01_16;

public class BadDataSetExceptionMain {

	public static void main(String[] args) {
		MyUtilities mu = new MyUtilities();

		try {
			double[] data = mu.getDataSet("nofile");
		} catch(BadDataSetException e) {
			System.out.println(e.name);
			System.out.println(e.e);
		}

	}

}
