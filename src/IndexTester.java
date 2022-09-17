import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexTester {
	
	public static void main (String [] args) throws FileNotFoundException, IOException
	{
		Index first = new Index();
		first.init();
		Path p1 = Paths.get("blob1.txt");
        try {
            Files.writeString(p1, "blobby", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p2 = Paths.get("blob2.txt");
        try {
            Files.writeString(p2, "some content", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
              e.printStackTrace();
        }
        
        Path p3 = Paths.get("blob3.txt");
        try {
              Files.writeString(p3, "blob blob blobby blob", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
		first.addBlob("blob1.txt");
		first.addBlob("blob2.txt");
		first.addBlob("blob3.txt");
		//first.removeBlob("hello");
		first.removeBlob("blob2.txt");
		//first.removeBlob("goodbye");
		
	}
}

        
