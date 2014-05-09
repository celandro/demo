package demo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class UserDao {
	private final Datastore ds;

	public UserDao(Morphia morphia, Mongo mongo, String database) {
		morphia.map(User.class);
		this.ds = morphia.createDatastore(mongo, database);
	}

	public UserDao(Datastore ds) {
		this.ds = ds;
	}

	public boolean authenticate(String userName, String password) {
		User u = ds.find(User.class, "userName =", userName).get();
		if (u == null)
			return false;
		return u.getPassword().equals(password);
	}

	public List<User> findUserByGender(String gender) {
		return ds.find(User.class, "gender = ", gender).order("userName")
				.asList();
	}

	public boolean isUp() {
		DB db = ds.getDB();
		DBObject ping = new BasicDBObject("ping", "1");
		try {
			db.command(ping);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}
}
