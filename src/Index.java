import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Index {
	private HashMap <String, String> objectsDirectory;
	public Index()
	{
		objectsDirectory = new HashMap <String, String>();
	}
	
	public void init ()
	{
		new File("./testFile").mkdir();
		new File("./objects").mkdir();
		File emptyFile = new File ("index");
	}
	
	public void addBlob(String fileName) throws FileNotFoundException, IOException
	{
		Blob blobby = new Blob(fileName);
		String blobbySHA1 = blobby.getSHA1();
		objectsDirectory.put(fileName, blobbySHA1);
		PrintWriter printWriter = new PrintWriter ("./testFile/" + "index");
		printWriter.print(fileName + " : " + blobbySHA1);
		printWriter.close();
	}
	
	public void removeBlob (String fileName) throws FileNotFoundException
	{
		//System.out.println ("hashmap " + objectsDirectory);
		//System.out.println ("remove check" + objectsDirectory.get(fileName));
		File toDelete = new File ("./objects/" + objectsDirectory.get (fileName));
		toDelete.delete();
		if (objectsDirectory.containsKey(fileName))
			{
				objectsDirectory.remove(fileName);
			}
		updateIndex();
		//System.out.println ("hashmap " + objectsDirectory);
		
		
	}
	
	public void updateIndex() throws FileNotFoundException
	{
		File index2 = new File ("index");
		PrintWriter printWriter2 = new PrintWriter ("./testFile/" + "index");
		for (String name: objectsDirectory.keySet()) {
		    String key = name.toString();
		    String value = objectsDirectory.get(name).toString();
		    printWriter2.print(key + " : " + value);
		}
		printWriter2.close();
	}
	
	// object directory = hashmap
}
