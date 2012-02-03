import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimerTest
{
	Timer monTimer;

	@Before
	public void init() throws TimerException
	{
		monTimer = new Timer(3, 3, 3);
	}

	@Test(expected = TimerException.class )
	public void testTimerPass() throws TimerException
	{
		monTimer = new Timer(3, -1, 3);
	}

	@Test
	public void testSelectRing() throws TimerException
	{
		assertEquals("Expected : 3  Actual : "+monTimer.ring ,3, monTimer.ring);

		monTimer.selectRing(2);
		assertEquals("Expected : 2  Actual : "+monTimer.ring ,2, monTimer.ring);
	}

	@Test
	public void testAddMin() throws TimerException
	{
		monTimer.addMin(2000);
		assertEquals(23,monTimer.min);
		assertEquals(12,monTimer.hour);
	}

	@Test
	public void testSetActive()
	{
		assertFalse(monTimer.active);
		monTimer.setActive(true);
		assertTrue(monTimer.active);
	}



}
