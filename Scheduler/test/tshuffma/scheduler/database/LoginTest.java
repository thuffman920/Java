package tshuffma.scheduler.database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ability of this program to check
 * a certain user to access the program, without allowing
 * loopholes
 * @author Tyler Huffman
 */
public class LoginTest {

	/** This sets up the login checker to be tested later */
	public Login login;
	
	/**
	 * This sets up the more important parts, which will be
	 * used later to test the login checker
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		login = new Login();
	}

	/**
	 * This tests the ability of this program to allow the
	 * right people into this program
	 * Test method for {@link tshuffma.scheduler.database.Login#account(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAccount() {
		assertFalse(login.account("user", "pass"));
		assertTrue(login.account("admin", "pas5w0Rd"));
		try {
			assertFalse(login.account("tyler", "pass"));
			assertFalse(login.account("tyler", "pass"));
			assertFalse(login.account("tyler", "pass"));
			assertFalse(login.account("adm", "pas5w0Rd"));
			assertFalse(login.account("tyler", "pass"));
		} catch (IllegalArgumentException g) {
			assertEquals("Wait 30 seconds.", g.getMessage());
		}
	}

}
