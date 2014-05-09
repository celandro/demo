package demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class StatusServiceTest {
	UserDao userDao;
	StatusService service;
	@Before
	public void setup() {
		userDao = mock(UserDao.class);
		service = new StatusService(userDao);
	}
	@Test
	public void testIsUp() {
		when(userDao.isUp()).thenReturn(true);
		assertEquals(true,service.isUp().isUserServiceUp());

		when(userDao.isUp()).thenReturn(false);
		assertEquals(false,service.isUp().isUserServiceUp());
	}

}
