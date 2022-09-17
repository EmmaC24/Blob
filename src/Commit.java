
public class Commit {
	
	public Commit (String summary, String author, String date)
	{
		
	}
//	A Commit is primarily a node of a doubly linked list
//	A commit has a pointer to one other commit
//	A commit has a pointer to its parent
//	The pointers are initially NULL
//	A Commit also has a variable called 'pTree'
//	pTree will be null or will point to a Filename or Path or String of a SHA1 file inside the objects folder
//	pTree is initialized in the constructor
//	A Commit also has member variables
//	String called 'summary'
//	String called 'author'
//	String called 'date'
//	A commit has a constructor which takes in three Strings, and a pointer to a parent Commit
//	The first String is used to assign your pTree variable a value
//	The second String is a summary of recent changes
//	The third String is a author
//	A commit contains a method to generate a SHA1 String
//	The inputs for the SHA1 are a combination of the 
//	Has a method to creates a SHA1 String given..
//	String value of the Filename or Path or String of pTree
//	And the Summary String
//	Has a method getDate()
//	It gets the date as a String in whatever format you like
//	Has a method which writes out a file
//	With file contens: 
//	The file contents are the pTree value on the first line
//	2nd line is the file location
//	2nd line is the author
//	3rd line is the date
//	4th line is the Summary
//	The file will be written to in a folder named 'objects'
//	The file name will be a SHA1 generated as described in #6

}
