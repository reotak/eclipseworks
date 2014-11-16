package ex01_16;

import java.io.IOException;

class BadDataSetException extends Exception{
	String name;
	IOException e;

	BadDataSetException(String name, IOException e) {
		this.name = name;
		this.e = e;
	}
}

