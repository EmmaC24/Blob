import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class Tester {

// write methods to write text into file
	
	@Test
//	void test() {
//		
//		
//		fail("Not yet implemented");
//	}
	
	void testBlob () throws FileNotFoundException, IOException {
		Blob b = new Blob ("./cookie");
//		File shaFile = new File ("./Blob/objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233");
//		assertTrue (shaFile.exists());
		assertTrue (b.getSHA1().equals("f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
	}
	
	void testInit () {
		Index obj = new Index ();
		obj.init();
	}
	
//	private void writeFile () {
//		 Path p = Paths.get("test.txt");
//	        try {
//	            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
//	        } catch (IOException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }
//	}
}
