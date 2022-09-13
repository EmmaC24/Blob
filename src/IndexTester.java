import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

public class IndexTester {
	
	public static void main (String [] args) throws FileNotFoundException, IOException
	{
		Index first = new Index();
		first.init();
		first.addBlob("hello");
		first.addBlob("goodbye");
		first.addBlob("cookie");
		//first.removeBlob("hello");
		first.removeBlob("cookie");
		//first.removeBlob("goodbye");
		
	}
}
