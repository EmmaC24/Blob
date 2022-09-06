

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Blob {
	private String fileContents;
	
	public Blob (String path) throws FileNotFoundException, IOException
	{
//		Path path = Paths.get(textPath);
		fileContents = readFile (path, StandardCharsets.UTF_8);
		File sha1File = new File ("./objects/" + readAndConvertFileToSHA1 (path, StandardCharsets.UTF_8));
		PrintWriter printWriter = new PrintWriter (sha1File);
		printWriter.print(fileContents);
		printWriter.close();
		// /Users/emmamiller/eclipse-workspace/Blob/objects
	}

	
	public static String readAndConvertFileToSHA1(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return toSHA1 (encoded);
    }
	
	public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
	
	
	public static String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(md.digest(convertme));
	}
	
	
	
}
