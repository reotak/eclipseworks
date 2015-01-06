package ex14_08;

class Friendly2 {
	private Friendly2 partner;
	private String name;

	public Friendly2(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		System.out.println(Thread.currentThread().getName() +
				" in " + name + ".hug() trying to invoke " +
				partner.name + ".hugBack()");

		Thread.yield();
		partner.hugBack();
	}

	private synchronized void hugBack() {
		Thread.yield();
		System.out.println(Thread.currentThread().getName() +
				" in " + name + ".hugBack()");
	}

	public void becomeFriend(Friendly2 partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final Friendly2 jareth = new Friendly2("jareth");
		final Friendly2 cory = new Friendly2("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(new Runnable() {
			public void run() { jareth.hug(); }
		}, "Thread1").start();

		new Thread(new Runnable() {
			public void run() { cory.hug(); }
		}, "Thread2").start();
	}
}
