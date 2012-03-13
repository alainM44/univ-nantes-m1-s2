/**
 * @author Baudry - Fleurey
 *
 */

package book;
import java.io.*;
import java.util.Hashtable;

public class BookSystem  {
	

	protected BufferedWriter output;
	protected Hashtable bookMap;
	
	public BookSystem() {
		output = new BufferedWriter(new OutputStreamWriter(System.out));
		bookMap = new Hashtable();
	}
	
	public void addBook(Book b) {
		bookMap.put(b.getTitle(), b);
	}
	
	public Book getBook(String title) {
		return (Book) bookMap.get(title);
	}
	
	public void processCommand(String cmd) {
		
		 java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(cmd);
		 
		 try {
			 String book, command = null;
			 book = tokenizer.nextToken(" ");
			 if (tokenizer.hasMoreTokens())
			 	command = tokenizer.nextToken(" ");
			 output.write(cmd + "\n");
			 output.flush();
			
			 Book b = (Book)bookMap.get(book);
			 if (b == null) {  output.write("invalid book : " + book + "\n"); output.flush(); return; }
			 else if (command!= null) b.execute(command, tokenizer);
			 else output.write(b.getTitle() + "\n");
			 output.write("System> OK\n");
			 output.flush();
			 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidActionException e) {
			try {
				output.write("System> "+ e.getMessage() +"\n"); output.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
