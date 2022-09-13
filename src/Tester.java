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

import org.junit.jupiter.api.Test;

class Tester {
	
	@Test
	void testBlob () throws FileNotFoundException, IOException {
		this.writeFile("cookie", "chocolate chip, oatmeal, oreos, and gingersnap");
		Blob b = new Blob ("./cookie");
		File shaFile = new File ("./objects/f43b2dc13be66d493ab6cf99c0c5b24acd744233"); // should we make it case insensitive?
		assertTrue (shaFile.exists());
		assertTrue (b.getSHA1().equals("f43b2dc13be66d493ab6cf99c0c5b24acd744233"));
	}
	
	@Test
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
	
	@Test
	void testAddAndRemoveBlob () throws FileNotFoundException, IOException {
		Index obj = new Index ();
		obj.init();
		this.writeFile("goodbye", "goodbye!");
		obj.addBlob ("goodbye");
		File blob = new File ("./objects/1019c6b371c727c6b7ac7b855f3822d64d795b1c");
		String indexContent = this.content("./testFile/index");
		assertTrue (blob.exists());
		assertTrue (indexContent.contains("goodbye : 1019c6b371c727c6b7ac7b855f3822d64d795b1"));
	}
	
	@Test
	void testRemoveBlob () throws IOException {
		Index obj = new Index ();
		obj.init ();
		this.writeFile("hello", "hello!");
		obj.addBlob("hello");
		obj.removeBlob("hello"); 
		String indexContent = this.content("./testFile/index");
		File blob = new File ("./objects/8f7d88e901a5ad3a05d8cc0de93313fd76028f8c");
		assertTrue (!blob.exists());
		assertTrue (!indexContent.contains("hello : 8f7d88e901a5ad3a05d8cc0de93313fd76028f8c"));
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
