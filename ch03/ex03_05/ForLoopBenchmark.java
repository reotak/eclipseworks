package ex03_05;

class ForLoopBenchmark extends Benchmark {
	private final int count;
	
	public ForLoopBenchmark() {
		this(1);
	}
	
	public ForLoopBenchmark(int count) {
		this.count = count;
	}
	
	void benchmark() {
		for (int i = 0; i < count; i++) {
			; 
		}
	}
	
	public static void main(String[] args) {
		Benchmark bench = new ForLoopBenchmark(10000);
		
		System.out.println(bench.repoeat(1000) / 1000);
	}

}
