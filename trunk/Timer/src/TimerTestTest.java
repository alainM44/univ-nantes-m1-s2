import static org.junit.Assert.*;

import java.awt.geom.GeneralPath;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class TimerTestTest
{
	Timer monTimer;
	GregorianCalendar mockedCalendar;

	@Before
	public void init() throws TimerException
	{
		mockedCalendar = mock(GregorianCalendar.class);
	 monTimer = new Timer(3, 3, 3, mockedCalendar);

	}

	@Test(expected = TimerException.class)
	public void testTimerPass() throws TimerException
	{
		monTimer = new Timer(3, -1, 3, new GregorianCalendar());
	}

	@Test
	public void testSelectRing() throws TimerException
	{

		assertEquals("Expected : 3  Actual : " + monTimer.ring, 3,
				monTimer.ring);

		monTimer.selectRing(2);
		assertEquals("Expected : 2  Actual : " + monTimer.ring, 2,
				monTimer.ring);
	}

	@Test
	public void testAddMin() throws TimerException
	{
		monTimer.addMin(2000);
		assertEquals(23, monTimer.min);
		assertEquals(12, monTimer.hour);
	}

	@Test
	public void testSetActive() throws TimerException
	{

		monTimer = new Timer(3, 3, 3, mockedCalendar);
		when(mockedCalendar.get(GregorianCalendar.HOUR)).thenReturn(3);

		when(mockedCalendar.get(GregorianCalendar.MINUTE)).thenReturn(3);
		// assertFalse(monTimer.active);
		monTimer.setActive(true);
		assertTrue(monTimer.active);
		assertTrue(monTimer.ringing);
	}

	@Test
	public void testSetActiveNotRinging() throws TimerException
	{
		mockedCalendar = mock(GregorianCalendar.class);
		monTimer = new Timer(3, 3, 3, mockedCalendar);
		when(mockedCalendar.get(GregorianCalendar.HOUR)).thenReturn(3);
		when(mockedCalendar.get(GregorianCalendar.MINUTE)).thenReturn(2);
		monTimer.setActive(true);
		assertTrue(monTimer.active);
		assertFalse(monTimer.ringing);
	}
	@Test
	public void testSetDesactive() throws TimerException
	{
		mockedCalendar = mock(GregorianCalendar.class);
		monTimer = new Timer(3, 3, 3, mockedCalendar);
		when(mockedCalendar.get(GregorianCalendar.HOUR)).thenReturn(3);
		when(mockedCalendar.get(GregorianCalendar.MINUTE)).thenReturn(2);
		monTimer.setActive(false);
		assertFalse(monTimer.active);
		assertFalse(monTimer.ringing);
	}
	
	@Test
	public void testSetDesactiveNotRinging() throws TimerException
	{
		mockedCalendar = mock(GregorianCalendar.class);
		monTimer = new Timer(3, 3, 3, mockedCalendar);
		when(mockedCalendar.get(GregorianCalendar.HOUR)).thenReturn(3);
		when(mockedCalendar.get(GregorianCalendar.MINUTE)).thenReturn(3);
		monTimer.setActive(false);
		assertFalse(monTimer.active);
		assertFalse(monTimer.ringing);
	}


}
