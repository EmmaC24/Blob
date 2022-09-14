import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TreeTester {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		String [] arr = new String [3];
		arr[0] = "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f";
		arr[1] = "blob : 01d82591292494afd1602d175e165f94992f6f5f";
		arr[2] = "blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83";
		Tree t = new Tree (arr);
	}

}
