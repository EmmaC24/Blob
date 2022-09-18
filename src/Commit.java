import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class Commit {
	
	private String author = "";
	private Commit parent = null;
	private Commit next = null;
	//can we always assume that this is null?
	private String fileLocation = "";
	public String pTree = "";
	public String date = "";
	public String summary = "";
	//private LinkedList<Commit> list;
	public Commit (String tree, String sum, String auth, Commit par)
	{
		
		//do we need to create the objects folder?
		pTree = tree;
		summary = sum;
		author = auth;
		parent = par;
		date = getDate();
		fileLocation = getLocation();
		createFile();
		
	}
	
	public String getTree()
	{
		return pTree;
	}
	
	public String getLocation()
	{
		String loc = "objects/" + generateSHA1(getFileContents());
		return loc;
	}
	
	public String getFileContents()
	{
		String contents = "";
		contents += pTree + "\n";
		if (parent != null)
		{
			contents += parent.getLocation();
		}
		else
		{
			contents += "\n";
		}
		
		if (next != null)
		{
			contents += next.getLocation();
		}
		else
		{
			contents += "\n";
		}
		contents += author + "\n";
		contents += date + "\n";
		contents += summary;
		
		return contents;
	}
	
	public void createFile()
	{
		Path p = Paths.get("objects/" + generateSHA1 (getFileContents()));
        try {
            Files.writeString(p, getFileContents(), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public String getDate ()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		String d = dateFormat.format(cal.getTime());
		return d;
	}
	public String generateSHA1 (String input)
	{
		try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
 
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
 
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
 
            // return the HashText
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}

}
