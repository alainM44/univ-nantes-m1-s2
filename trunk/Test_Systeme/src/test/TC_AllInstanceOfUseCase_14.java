package test;

import junit.framework.TestCase;
import book.*;

/** 
 * Test case TC_AllInstanceOfUseCase_14 for Book system 
 */

public class TC_AllInstanceOfUseCase_14 extends TestCase {

	protected BookSystem bookSystem;

	// constructor
	public TC_AllInstanceOfUseCase_14(String name) {
		super(name);
	}
	
	// initialization
	public void setUp() {
		bookSystem = new BookSystem();
		Book b = new Book("book1");
		bookSystem.addBook(b);
		b = new Book("book2");
		bookSystem.addBook(b);
	}
	
	// cleanup
	public void tearDown() {
		bookSystem = null;
	}

	public void testTC_AllInstanceOfUseCase_14() {
// begin deliver(b : Book)
{
	bookSystem.processCommand("book2 DELIVER");
}
// end deliver(b : Book)

// begin borrow(b:Book; u:User)
{

	bookSystem.processCommand("book2 BORROW user1");

}
// end borrow(b:Book; u:User)

// begin setdamaged(b:Book)
{
	Book b = bookSystem.getBook("book2");
	bookSystem.processCommand("book2 SETDAMAGED");
	assertTrue(b.isDamaged());
	assertTrue(b.getCurrent_state() instanceof book.Borrowed);
}
// end setdamaged(b:Book) 

// begin deliver(b : Book)
{
	bookSystem.processCommand("book1 DELIVER");
}
// end deliver(b : Book)


	}

	public static void main(String[] args) {
		junit.swingui.TestRunner.run(TC_AllInstanceOfUseCase_14.class);
	}

}