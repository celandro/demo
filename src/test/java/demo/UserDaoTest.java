package demo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class UserDaoTest {
	UserDao userDao;
	Datastore ds;
	@Before
	public void setup() {
		ds = mock(Datastore.class);
		userDao = new UserDao(ds);
	}

	@Test
	public void testAuthenticateMissing() {
		Query<User> q = mock(Query.class);
		when(ds.find(User.class, "userName =", "u1")).thenReturn(q);
		when(q.get()).thenReturn(null);
		assertFalse(userDao.authenticate("u1", "p1"));
	}
	@Test
	public void testAuthenticate() {
		Query<User> q = mock(Query.class);
		when(ds.find(User.class, "userName =", "u1")).thenReturn(q);
		User user = new User();
		user.setPassword("p2");
		when(q.get()).thenReturn(user);
		assertFalse(userDao.authenticate("u1", "p1"));
		assertTrue(userDao.authenticate("u1", "p2"));
		
	}

	@Test
	public void testFindUserByGender() {
		List<User> expected = Arrays.asList(new User());
		Query<User> q = mock(Query.class);
		Query<User> q2 = mock(Query.class);
		when(ds.find(User.class, "gender = ", "male")).thenReturn(q);
		when(q.order("userName")).thenReturn(q2);
		when(q2.asList()).thenReturn(expected);
		assertSame(expected,userDao.findUserByGender("male"));
	}

	@Test
	public void testIsUp() {
		DB db = mock(DB.class);
		when(ds.getDB()).thenReturn(db);
		DBObject ping = new BasicDBObject("ping", "1");
		when(db.command(ping)).thenReturn(null);
		assertEquals(true,userDao.isUp());

		when(db.command(ping)).thenThrow(new MongoException("dummy"));
		assertEquals(false,userDao.isUp());
		
	}

}
