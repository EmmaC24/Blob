import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Tester {
	
	@BeforeAll
	static void setUp () {
		Tester.writeFile ("cookie", "chocolate chip, oatmeal, oreos, and gingersnap");
	}
	
	@AfterAll
	static void tearDown () {
		File cookie = new File ("cookie");
		cookie.delete();
	}
	
	@Test
	void testBlobFileLocationName () throws FileNotFoundException, IOException {
		Blob b = new Blob ("./cookie");
		File shaFile = new File ("./objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233"); // should we make it case insensitive?
		assertTrue (b.getSHA1().equalsIgnoreCase("f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
		assertTrue (shaFile.exists());
	}
	
	@Test
	void testBlobContent () throws IOException {
		Blob b = new Blob ("./cookie");
		String words = "chocolate chip, oatmeal, oreos, and gingersnap";
		String content = this.content("./objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233");
		assertTrue (words.equals(content));
	}
	
	@Test
	void testInit () {
		Index git = new Index ();
		git.init();
		File newObjects = new File ("./objects");
		File newIndex = new File ("./testFile/index");
		assertTrue (newObjects.exists());
		assertTrue (newIndex.exists());
	}
	
	@Test
	void testAddBlob () throws FileNotFoundException, IOException {
		Index obj = new Index ();
		obj.init();
		obj.addBlob ("cookie");
		File blob = new File ("./objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233");
		String indexContent = this.content("./testFile/index");
		assertTrue (blob.exists());
		assertTrue (indexContent.contains("cookie : f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
	}
	
	@Test
	void testRemoveBlob () throws IOException {
		Index obj = new Index ();
		obj.init ();
		obj.addBlob("cookie");
		obj.removeBlob("cookie"); 
		String indexContent = this.content("./testFile/index");
		File blob = new File ("./objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233");
		assertTrue (!blob.exists());
		assertTrue (!indexContent.contains("cookie : f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
	}
	
	@Test
	void testMultipleAdds () throws FileNotFoundException, IOException {
		Index obj = new Index ();
		obj.init ();
		Tester.writeFile("test1", "This is test 1");
		Tester.writeFile ("test2", "This is test 2");
		obj.addBlob("test1");
		obj.addBlob("test2");
		obj.addBlob("cookie");
		obj.removeBlob("test1");
		File removed = new File ("./objects/1fa5bfacc1096a3798801db3bd0ca4fa3bdb2587");
		assertTrue (!removed.exists());
		String indexContent = this.content("./testFile/index");
		assertTrue (!indexContent.contains("test1 : 1fa5bfacc1096a3798801db3bd0ca4fa3bdb2587"));
		File t2 = new File ("test2");
		t2.delete();
		File t1 = new File ("test1");
		t1.delete();
	}
	
	
	private static void writeFile (String fileName, String content) {
		 Path p = Paths.get(fileName);
	        try {
	            Files.writeString(p, content, StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	private String content (String filepath) throws IOException {
		File file = new File (filepath);
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		StringBuilder sb = new StringBuilder(); 
		String line = br.readLine(); 
		while (line != null) { 
			sb.append(line).append("\n"); 
			line = br.readLine(); 
		} 
		String fileAsString = sb.toString();
		return fileAsString;
	}
}
