import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Index {
	private HashMap <String, String> objectsDirectory;
	public Index()
	{
		File emptyFile = new File ("index");
		objectsDirectory = new HashMap <String, String>();
	}
	
	public void addBlob(String fileName) throws FileNotFoundException, IOException
	{
		Blob blobby = new Blob(fileName);
		String blobbySHA1 = blobby.getSHA1();
		objectsDirectory.put(fileName, blobbySHA1);
		PrintWriter printWriter = new PrintWriter ("index");
		printWriter.print(fileName + " : " + blobbySHA1);	
	}
	
	public void removeBlob (String fileName)
	{
		objectsDirectory.remove(fileName);
		File toDelete = new File ("./objects/" + fileName);
		//try removing the dot if it doesn't work
		toDelete.delete();
		
	}
	
	// object directory = hashmap
}
