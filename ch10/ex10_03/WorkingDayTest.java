package ex10_03;

import static ex10_03.Week.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class WorkingDayTest {

	@Test
	public void test() {
		Week day = MON;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == true
				&& WorkingDay.isWorkingDayIfVer2(day) == true
				&& WorkingDay.isWorkingDaySwitchVer(day) == true);
		day = TUE;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == true
				&& WorkingDay.isWorkingDayIfVer2(day) == true
				&& WorkingDay.isWorkingDaySwitchVer(day) == true);
		day = WED;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == true
				&& WorkingDay.isWorkingDayIfVer2(day) == true
				&& WorkingDay.isWorkingDaySwitchVer(day) == true);
		day = THU;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == true
				&& WorkingDay.isWorkingDayIfVer2(day) == true
				&& WorkingDay.isWorkingDaySwitchVer(day) == true);
		day = FRI;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == true
				&& WorkingDay.isWorkingDayIfVer2(day) == true
				&& WorkingDay.isWorkingDaySwitchVer(day) == true);
		day = SAT;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == false
				&& WorkingDay.isWorkingDayIfVer2(day) == false
				&& WorkingDay.isWorkingDaySwitchVer(day) == false);
		day = SUN;
		assertTrue(
				WorkingDay.isWorkingDayIfVer(day) == false
				&& WorkingDay.isWorkingDayIfVer2(day) == false
				&& WorkingDay.isWorkingDaySwitchVer(day) == false);
	}

}
