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
		PrintWriter printWriter = new PrintWriter ("./Test/" + "index");
		printWriter.print(fileName + " : " + blobbySHA1);
		printWriter.close();
	}
	
	public void removeBlob (String fileName)
	{
		System.out.println ("remove check" + objectsDirectory.get(fileName));
		File toDelete = new File ("./objects/" + objectsDirectory.get (fileName));
		//try removing the dot if it doesn't work
		toDelete.delete();
		objectsDirectory.remove(fileName);
		
		
		
	}
	
	// object directory = hashmap
}
