package demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileServiceTest {
	FileService service;
	@Before
	public void setup() {
		service = new FileService();
	}

	@Test
	public void testFindFiles() {
		List<String> files = service.findFiles("src/test/resources/dummy");
		assertEquals(1,files.size());
		assertEquals("file1.txt", files.get(0));
	}
	@Test
	public void testFindFilesNotFound() {
		List<String> files = service.findFiles("src/test/resources/missing");
		assertEquals(0,files.size());
	}

}
