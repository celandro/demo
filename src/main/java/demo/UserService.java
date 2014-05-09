package demo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

@Component
@Path("/1.0/users")
public class UserService {
	@InjectParam
	private UserDao userDao;
	//default constructor required for jersey
	public UserService() {
		
	}
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("authenticate")
	public AuthenticatedResponse authenticate(
			@QueryParam("userName") String userName,
			@QueryParam("password") String password) {
		if (userName == null || password == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		boolean authenticated = userDao.authenticate(userName, password);
		AuthenticatedResponse response = new AuthenticatedResponse(authenticated);
		return response;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public List<User> findUsers(@QueryParam("gender") String gender){
		if (gender == null ) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		return userDao.findUserByGender(gender);
		
	}

	public class AuthenticatedResponse {
		private final boolean authenticated;

		private AuthenticatedResponse(boolean authenticated) {
			this.authenticated = authenticated;
		}

		public boolean isAuthenticated() {
			return authenticated;
		}

	}
}
