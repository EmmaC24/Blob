
public class CommitTester {
		public static void main (String [] args)
		{
			Commit original = new Commit ("i'm tree", "here's the summary", "Emma Miller", null);
			Commit second = new Commit ("i'm a cool tree","same summary", "Emma Miller",original);
		}
}
