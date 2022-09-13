import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
//import org.junit.rules.ErrorCollector;

class Tester {

// write methods to write text into file
//	public ErrorCollector collector  = new ErrorCollector ();
	
	@Test
//	void test() {
//		
//		
//		fail("Not yet implemented");
//	}
	
	// how to make it continue testing even after failing
	void testBlob () throws FileNotFoundException, IOException {
		this.writeFile("cookie", "chocolate chip, oatmeal, oreos, and gingersnap");
		Blob b = new Blob ("./cookie");
		File shaFile = new File ("./objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233"); // should we make it case insensitive?
		assertTrue (shaFile.exists());
		assertTrue (b.getSHA1().equals("f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
	}
	
	void testInit () {
		File index = new File ("./testFile/index");
		File obj = new File ("./objects");
		index.delete();
		if (obj.exists()) {
			File[] contents = obj.listFiles();
			for (File f : contents) {
	            f.delete();
	        }
		}
		
		Index git = new Index ();
		git.init();
		File newObjects = new File ("./objects");
		File newIndex = new File ("./index");
		assertTrue (newObjects.exists());
		assertTrue (newIndex.exists());
	}
	
	void testAddAndRemoveBlob () throws FileNotFoundException, IOException {
		Index obj = new Index ();
		obj.init();
		this.writeFile("goodbye", "goodbye!");
		obj.addBlob ("goodbye");
		File blob = new File ("./objects");
//		assertTrue ();//how to check if it has stuff in it?? and that it's the right hash
		
		
		obj.removeBlob("goodbye"); 
		File goodbye = new File ("./goodbye");
		assertTrue (!goodbye.exists());
		// how do we check index without just looking in it manually
	}
	
	
	private void writeFile (String fileName, String content) {
		 Path p = Paths.get(fileName);
	        try {
	            Files.writeString(p, content, StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
}
