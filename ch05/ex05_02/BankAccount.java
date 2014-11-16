package ex05_02;

import java.util.LinkedList;
import java.util.List;

public class BankAccount {
	private long number; // 口座番号
	private long blance; // 現在の残高（単位は、セント）
	private History hist; // 処理の履歴

	public class Action implements Cloneable {
		private String act;
		private long amount;

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		public String toString() {
			// identify our enclosing account
			return number + ": " + act + " " + amount;
		}

		public Action clone() {
			try {
				return (Action)super.clone();
			} catch (Exception e) {
				throw new InternalError(e);
			}
		}
	}

	public class History implements Cloneable {
		private List<Action> acts;
		private int maxSize = 10;
		public History() {
			acts = new LinkedList<Action>();
		}

		public void add(Action action) {
			if (acts.size() < maxSize) {
				acts.add(action);
			} else {
				acts.remove(0);
				acts.add(action);
			}
		}

		public Action next() {
			if (acts.size() == 0) {
				return null;
			}

			Action act = acts.get(0);
			acts.remove(0);
			return act;

		}

		public History clone() {
			try {
				History clone = (History)super.clone();
				clone.acts = new LinkedList<Action>();

				for (Action act : acts) {
					clone.acts.add(act);
				}
				return clone;
			} catch (Exception e) {
				throw new InternalError(e);
			}
		}
	}

	public BankAccount(long number, long blance) {
		this.number = number;
		this.blance = blance;
		this.hist = new History();
	}

	public void deposit(long amount) {
		blance += amount;
		hist.add(new Action("deposit", amount));
	}

	public void withdraw(long amount) {
		blance -= amount;
		hist.add(new Action("withdraw", amount));
	}

	public History history() {
		return hist.clone();
	}
	// ...

	public String toString() {
		String str = number + " : " + blance + "\n";

		History tmp = hist.clone();

		for (Action act = tmp.next(); act != null; act = tmp.next()) {
			str += "  <- " + act.toString() + "\n";
		}
		return str;
	}

	public static void main(String[] args) {
		BankAccount acc = new BankAccount(1L, 1000L);

		acc.deposit(100);
		System.out.println(acc);

		acc.withdraw(50);
		System.out.println(acc);

	}
}

