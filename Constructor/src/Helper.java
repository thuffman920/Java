import java.util.Scanner;


public class Helper {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("What line? ");
		System.out.print(readingString(console.nextLine()));
		console.close();

	}
	
	public static String readingString(String nextLine) {
		Scanner lineScanner = new Scanner(nextLine);
		String id = "";
		String password = "";
		int maxCheckedOut = 0;
		int i = 0;
		while (lineScanner.hasNext()) {
			String next = lineScanner.next();
			if (i == 0) {
				id = next.substring(0, next.length() - 1);
			} else if (i == 1) {
				password = next.substring(0, next.length() - 1);
			} else {
				maxCheckedOut = Integer.parseInt(next);
			}
			i++;
		}
		lineScanner.close();
		return id + "\n" + password + "\n" + maxCheckedOut + "\n";
	}

}
