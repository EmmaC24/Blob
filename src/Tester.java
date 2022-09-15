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
	private static String actualSha;
	private static String upper;
	private static File shaFile;
	private static File upperCase;
	
	@BeforeAll
	static void setUp () {
		Tester.writeFile ("cookie", "chocolate chip, oatmeal, oreos, and gingersnap");
		actualSha = "f43b2dc13be66d493ab6cf99c0c5b24acd744233";
		upper = actualSha.toUpperCase();
		shaFile = new File ("./objects/"+actualSha);
		upperCase = new File ("./objects/"+upper);
	}
	
	@AfterAll
	static void tearDown () {
		File cookie = new File ("cookie");
		cookie.delete();
	}
	
	@Test
	void testBlobFileLocationName () throws FileNotFoundException, IOException {
		Blob b = new Blob ("./cookie");
		assertTrue (b.getSHA1().equalsIgnoreCase("f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
		assertTrue (shaFile.exists()||upperCase.exists());
	}
	
	@Test
	void testBlobContent () throws IOException {
		Blob b = new Blob ("./cookie");
		String words = "chocolate chip, oatmeal, oreos, and gingersnap";
		if (shaFile.exists()) {
			String content = this.content(shaFile.getCanonicalPath());
			assertTrue (words.equals(content));
		}
		else {
			String ifUpper = this.content(upperCase.getCanonicalPath());
			assertTrue (words.equals(ifUpper));
		}
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
		String indexContent = this.content("./testFile/index");
		assertTrue (shaFile.exists() || upperCase.exists());
		assertTrue (indexContent.contains("cookie : " + actualSha) || indexContent.contains("cookie : " + upperCase));
	}
	
	@Test
	void testRemoveBlob () throws IOException {
		Index obj = new Index ();
		obj.init ();
		obj.addBlob("cookie");
		obj.removeBlob("cookie"); 
		String indexContent = this.content("./testFile/index");
		assertTrue (!shaFile.exists() && !upperCase.exists());
		assertTrue (!indexContent.contains("cookie : " + actualSha) && !indexContent.contains("cookie : " + upperCase));
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
		
		//tests the creation of these three blobs and made it case insensitive; is it efficient? most likely yes. do I know how to fix it? no...
		File test1 = new File ("./objects/1fa5bfacc1096a3798801db3bd0ca4fa3bdb2587");
		String upperCase1 = "1fa5bfacc1096a3798801db3bd0ca4fa3bdb2587".toUpperCase();
		File upper1 = new File ("./objects/" + upperCase1);
		assertTrue (test1.exists()||upper1.exists());
		File test2 = new File ("./objects/0e0a7ceb2dc997ac932d99723abc7695c6586255");
		String upperCase2 = "0e0a7ceb2dc997ac932d99723abc7695c6586255".toUpperCase();
		File upper2 = new File ("./objects/" + upperCase2);
		assertTrue (test2.exists()||upper2.exists());
		assertTrue (shaFile.exists()||upperCase.exists());
		String indexContent = this.content("./testFile/index");
		assertTrue ((indexContent.contains("test1 : 1fa5bfacc1096a3798801db3bd0ca4fa3bdb2587") || indexContent.contains("test1 : " + upperCase1)) && (indexContent.contains("test2 : 0e0a7ceb2dc997ac932d99723abc7695c6586255") || indexContent.contains("test2 : " + upperCase2)) && (indexContent.contains("cookies : " + actualSha) || indexContent.contains("test1 : " + upperCase1)));
		
		//checks removeBlob -- does not execute because above tests failed
		obj.removeBlob("cookie");
		assertTrue (!shaFile.exists() && !upperCase.exists());
		indexContent = this.content("./testFile/index");
		assertTrue (!indexContent.contains("cookie : " + actualSha) && !indexContent.contains("cookie : " + upperCase));
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
