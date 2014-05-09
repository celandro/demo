package demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/1.0/files")
public class FileService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public List<String> findFiles(@QueryParam("filePath") String filePath){
		File f = new File(filePath);
		if (f.isDirectory()) {
			File [] files = f.listFiles();
			List<String> fileNames = new ArrayList<String>(files.length);
			for (File file:files) {
				fileNames.add(file.getName());
			}
			return fileNames;
		} else {
			return new ArrayList<String>();
		}
	}

}
