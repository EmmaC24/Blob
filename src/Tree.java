import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Tree {
	private HashMap <String, String> map;
	private File indexF;
	private String hash;
	private String content;
	
	public Tree (HashMap <String, String> hashmap) throws IOException, NoSuchAlgorithmException {
		map = hashmap;
		indexF = new File ("./indexF");
		this.createIndexFromMap();
		hash = this.createHash("./indexF");
		content = this.content("./indexF");
		File obj = new File ("./objects");
		obj.mkdir();
		this.createsNewFile();
	}
	
	private void createIndexFromMap () throws IOException {
		BufferedWriter bf = new BufferedWriter(new FileWriter(indexF));

		// iterate map entries
		for (Map.Entry<String, String> entry :
			map.entrySet()) {

			// put key and value separated by a colon
			bf.write(entry.getKey() + " : "
					+ entry.getValue());

			// new line
			bf.newLine();
		}
		bf.close();
	}
	
	private String createHash (String filePath) throws IOException, NoSuchAlgorithmException {
		//https://gist.github.com/zeroleaf/6809843
		FileInputStream fileInputStream = new FileInputStream(filePath);
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
		byte[] bytes = new byte[1024];
		// read all file content
		while (digestInputStream.read(bytes) > 0);

		// digest = digestInputStream.getMessageDigest();
		byte[] resultByteArry = digest.digest();
		hash = bytesToHexString(resultByteArry);
		return hash;
		
	}
	
	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			int value = b & 0xFF;
			if (value < 16) {
				// if value less than 16, then it's hex String will be only
				// one character, so we need to append a character of '0'
				sb.append("0");
			}
			sb.append(Integer.toHexString(value).toUpperCase());
		}
		return sb.toString();
	}
	
	private String content (String filepath) throws IOException {
		File file = new File (filepath);
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		StringBuilder sb = new StringBuilder(); 
		String line = br.readLine(); 
		while (line != null) { 
			sb.append(line).append("\n"); 
			line = br.readLine(); 
		} 
		String fileAsString = sb.toString();
		return fileAsString;
	} 
	
	private String createsNewFile () throws IOException {
		File f = new File ("objects/" + hash);
		String path = f.getAbsolutePath();
		FileWriter writer = new FileWriter(path);
		
		writer.write (content);
		writer.close();
		return path;
	}
}
