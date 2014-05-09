package demo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import demo.UserService.AuthenticatedResponse;
import static org.mockito.Mockito.*;
public class UserServiceTest {
	UserDao userDao;
	UserService service;
	@Before
	public void setup() {
		userDao = mock(UserDao.class);
		service = new UserService(userDao);
	}

	@Test
	public void testAuthenticate() {
		when(userDao.authenticate("u1", "p1")).thenReturn(true);
		AuthenticatedResponse response = service.authenticate("u1", "p1");
		assertEquals(true,response.isAuthenticated());

		when(userDao.authenticate("u2", "p2")).thenReturn(false);
		response = service.authenticate("u2", "p2");
		assertEquals(false,response.isAuthenticated());

	}
	@Test
	public void testAuthenticateErrors() {
		try {
			service.authenticate("u1",null);
		}catch (WebApplicationException e) {
			assertEquals(Status.BAD_REQUEST.getStatusCode(),e.getResponse().getStatus());
		}
		try {
			service.authenticate(null,"p1");
		}catch (WebApplicationException e) {
			assertEquals(Status.BAD_REQUEST.getStatusCode(),e.getResponse().getStatus());
		}
	}


	@Test
	public void testFindUsers() {
		List<User> expected = Arrays.asList(new User());
		when(userDao.findUserByGender("male")).thenReturn(expected);
		assertSame(expected,service.findUsers("male"));
	}

	@Test
	public void testFindUsersErrors() {
		try {
			service.findUsers(null);
		}catch (WebApplicationException e) {
			assertEquals(Status.BAD_REQUEST.getStatusCode(),e.getResponse().getStatus());
		}
	}

}
