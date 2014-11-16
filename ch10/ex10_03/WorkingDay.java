package ex10_03;
import static ex10_03.Week.*;

public class WorkingDay {

	public static boolean isWorkingDayIfVer(final Week day) {
		if (day == MON) {
			return true;
		} else if (day == TUE) {
			return true;
		} else if (day == WED) {
			return true;
		} else if (day == THU) {
			return true;
		} else if (day == FRI) {
			return true;
		} else if (day == SAT) {
			return false;
		} else if (day == SUN) {
			return false;
		} else {
			throw new IllegalArgumentException("Weekは拡張されているかもしれません");
		}
	}

	public static boolean isWorkingDayIfVer2(final Week day) {
		if (day == MON || day == TUE || day == WED ||
			day == THU || day == FRI) {
			return true;
		} else if (day == SAT || day == SUN) {
			return false;
		} else {
			throw new IllegalArgumentException("Weekは拡張されているかもしれません");
		}
	}

	public static boolean isWorkingDaySwitchVer(final Week day) {
		switch (day) {
		case MON : case TUE : case WED :
		case THU : case FRI :
			return true;
		case SAT : case SUN :
			return false;
		default :
			throw new IllegalArgumentException("Weekは拡張されているかもしれません");
		}
	}
}
