package demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;

@Component
@Path("/1.0/status")
public class StatusService {
	@InjectParam
	UserDao userDao;
	public StatusService()	{
		
	}
	public StatusService(UserDao userDao) {
		this.userDao = userDao;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public StatusResponse isUp() {
		StatusResponse response = new StatusResponse(userDao.isUp());
		return response;
	}
	public class StatusResponse{
		final private boolean userServiceUp;
		public StatusResponse(boolean userServiceUp) {
			this.userServiceUp = userServiceUp;
		}
		public boolean isUserServiceUp() {
			return userServiceUp;
		}
	}
}
