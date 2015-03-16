package ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String> {
	private ListIterator<String> strings; // 元の文字列
	private final int maxLen;
	private String nextShort; // 次が不明ならばnull
	private String prevShort; // 前が不明ならばnull

	public ShortStrings(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		this.nextShort = null;
		this.prevShort = null;
	}

	private boolean elementFilter(String s) {
		return s.length() <= maxLen;
	}

	@Override
	public boolean hasNext() {
		if (nextShort != null) // すでに見つけている
			return true;

		// prevをすでに見つけている場合
		if (prevShort != null) {
			nextShort = strings.next();
			prevShort = null;
		}

		while (strings.hasNext()) {
			nextShort = strings.next();
			if (elementFilter(nextShort)) {
				return true;
			}
		}
		nextShort = null; // 見つからなかった
		return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext())
			throw new NoSuchElementException();

		String n = nextShort;
		nextShort = null;
		prevShort = null;
		return n;
	}

	@Override
	public boolean hasPrevious() {
		if (prevShort != null) // すでに見つけている
			return true;

		// nextをすでに見つけている場合
		if (nextShort != null) {
			prevShort = strings.previous();
			nextShort = null;
		}

		while (strings.hasPrevious()) {
			prevShort = strings.previous();
			if (elementFilter(prevShort)) {
				return true;
			}
		}
		prevShort = null; // 見つからなかった

		return false;
	}

	@Override
	public String previous() {
		if (prevShort == null && !hasPrevious())
			throw new NoSuchElementException();

		String p = prevShort;
		prevShort = null;
		nextShort = null;
		return p;
	}

	@Override
	public int nextIndex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int previousIndex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(String e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(String e) {
		throw new UnsupportedOperationException();
	}

}
