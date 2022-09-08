

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Blob {
	private String fileContents;
	private String sha1;
	
	public Blob (String path) throws FileNotFoundException, IOException
	{
//		Path path = Paths.get(textPath);
		fileContents = readFile (path, StandardCharsets.ISO_8859_1);
		sha1 = readAndConvertFileToSHA1 (path, StandardCharsets.ISO_8859_1);
		File sha1File = new File ("./objects/" + sha1);
		PrintWriter printWriter = new PrintWriter (sha1File);
		printWriter.print(fileContents);
		printWriter.close();
		
		
////		StringBuilder sb = new StringBuilder();
////		sb.append("Test String");
////
////		File f = new File("d:\\test.zip");
////		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
////		ZipEntry e = new ZipEntry("mytext.txt");
////		out.putNextEntry(e);
////
////		byte[] data = sb.toString().getBytes();
////		out.write(data, 0, data.length);
////		out.closeEntry();
//
//		out.close();
		// /Users/emmamiller/eclipse-workspace/Blob/objects
	}

	public String getSHA1()
	{
		return sha1;
	}
	
	public static String readAndConvertFileToSHA1(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        System.out.println ("bytearray" + Files.readAllBytes(Paths.get(path)));
        System.out.println ("filename" + byteArrayToHexString (encoded));
        return  byteArrayToHexString (encoded);
    }
	
	public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
	
	
	public static String byteArrayToHexString(byte[] b) 
	{
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
	}

	
	
	
}
