import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IndexTester {
	
	public static void main (String [] args) throws FileNotFoundException, IOException
	{
		Index first = new Index();
		File hello = new File ("hello");
		first.addBlob("hello");
		first.removeBlob("hello");
		
	}
}
