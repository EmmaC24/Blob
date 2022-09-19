import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ObjectTreeTester {
	static ArrayList<String> sampleList;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sampleList = new ArrayList<String>();
		sampleList.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		sampleList.add("blob : siwnwiog28dkw0h2r982hefu2rh2ufihnwfefwf8");
		sampleList.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File toDelete = new File ("objects/106a49dcd9322ff2fa9d20a3a4375449a657e66c");
		toDelete.delete();
	}

	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		
		//IMPORTANT: your code is perfect but you accidentally print an extra line into your file after the last blob/tree is printed
		//this makes the sha1 just slightly off
		Tree tree1 = new Tree (sampleList);
		File treeFile = new File ("objects/106a49dcd9322ff2fa9d20a3a4375449a657e66c");
		assertTrue (treeFile.exists());
		
		BufferedReader buffy = new BufferedReader (new FileReader (treeFile));

		assertEquals (buffy.readLine(), "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		assertEquals (buffy.readLine(), "blob : siwnwiog28dkw0h2r982hefu2rh2ufihnwfefwf8");
		assertEquals (buffy.readLine(), "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		
		buffy.close();
	}


}
