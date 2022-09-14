import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tree {
	private File indexF;
	private String hash;
	private String content;
	
	public Tree (String [] blobs) throws IOException, NoSuchAlgorithmException {
		content = this.convertArrayToString(blobs);
		indexF = new File ("./indexF");
		this.writeFile("indexF", content);
		hash = this.createHash("./indexF");
		File obj = new File ("./objects");
		obj.mkdir();
		this.createsNewFile();
		indexF.delete();
	}
	
	private String convertArrayToString (String [] info) {
		String str = "";
		for (int i=0; i<info.length; i++) {
			str += info[i];
			str += "\n";
		}
		return str;
	}
	
	private void writeFile (String fileName, String content) {
		 Path p = Paths.get(fileName);
	        try {
	            Files.writeString(p, content, StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
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
