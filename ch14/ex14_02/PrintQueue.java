package ex14_02;

import java.util.ArrayList;
import java.util.List;

class PrintQueue {

	List<PrintJob> queue = new ArrayList<PrintJob>();

	public void add(PrintJob job) {
		queue.add(job);
	}

	public PrintJob remove() {
		if (queue.size() >= 1) {
			return queue.remove(0);
		} else {
			return null;
		}
	}
}
