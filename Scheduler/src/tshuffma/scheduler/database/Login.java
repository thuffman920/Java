package tshuffma.scheduler.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This sets up a login checker, which houses the correct
 * username and passwords that are allowed to access this 
 * program
 * @author Tyler Huffman
 */
public class Login {

	/** These are the usernames associated with this program */
	private String[] usernames = {"admin"};
	/** These are the passwords associated with the usernames */
	private String[] passwords = {"pas5w0Rd"};
	/** This keeps track of how many attempts  */
	private int count;
	
	/**
	 * This initializes the login checker, in order to
	 * determine if the right people log into this program
	 */
	public Login() {
		count = 0;
	}
	
	/**
	 * This checks the inputted username and password with
	 * the database of predetermined accounts
	 * @param user the next input username
	 * @param pass the next input password
	 * @return true if the username and password are correct
	 */
	public boolean account(String user, String pass) {
		try {
			Scanner read = new Scanner(new File("logging.log"));
			ArrayList<String> lines = new ArrayList<String>();
			while (read.hasNextLine())
				lines.add(read.nextLine());
			read.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter("logging.log"));
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			for (int i = 0; i < lines.size(); i++) {
				writer.write(lines.get(i));
				writer.newLine();
			}
			writer.write(dateFormat.format(date) + "\tTried accessing as " + user);
			writer.newLine();
			boolean isRight = false;
			for (int i = 0; i < usernames.length; i++) {
				if (usernames[i].equals(user)) {
					if (passwords[i].equals(pass))
						isRight = true;
					break;
				}
			}
			count++;
			if (isRight) {
				count = 0;
				Date date1 = new Date();
				writer.write(dateFormat.format(date1) + "\tSuccessfully logged in as " + user);
				writer.newLine();
			}

			if (count % 5 == 0 && count != 0) {
				int wait = (count / 5) * 30;
				Date dat2 = new Date();
				writer.write(dateFormat.format(dat2) + "\tToo many attempts " + user + " has to wait " + wait + " seconds");
				writer.close();
				throw new IllegalArgumentException("Wait " + wait + " seconds.");
			}
			writer.close();
			return isRight;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
