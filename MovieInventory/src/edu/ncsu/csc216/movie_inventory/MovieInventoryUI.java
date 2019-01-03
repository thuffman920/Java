package edu.ncsu.csc216.movie_inventory;

import java.util.Scanner;

/**
 * This creates a user friendly program
 * which creates the movie inventory and
 * asks for input
 * @author Tyler Huffman
 */
public class MovieInventoryUI {

	/** This creates the MovieInventory */
	private MovieInventory inventory;
	/** This creates a private class wide scanner */
	private Scanner in;
	
	/**
	 * This creates the MovieInventoryUI which
	 * sets the MovieInventory and the Scanner
	 */
	public MovieInventoryUI() {
		in = new Scanner(System.in);
		inventory = new MovieInventory();
	}
	
	/**
	 * This is used to prompt the user for input
	 * and determines the option that you want
	 */
	public void userInterface() {
		System.out.println("MovieInventory menu:");
		System.out.println("1. List Movies");
		System.out.println("2. Add Movie");
		System.out.println("3. Remove Movie by Title");
		System.out.println("4. Quit");
		System.out.println();
		while(true) {
			System.out.print("Entry? ");
			int n = 0;
			String input = in.nextLine();
			try {
				n = Integer.parseInt(input);
				if (n <= 0 || n > 4) {
					System.out.println("Invalid command");
					System.out.println();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid command");
				System.out.println();
				continue;
			}
			if (n == 1) {
				listMovies();
				System.out.println();
			} else if (n == 2) {
				promptForMovie();
				System.out.println();
			} else if (n == 3) {
				removeMovie();
				System.out.println();
			} else {
				break;
			}
		}
	}
	
	/**
	 * This reports if a movie was successfully
	 * removed from the collection
	 */
	public void removeMovie() {
		
		System.out.print("Title to remove? ");
		String title = in.nextLine();
		if (inventory.removeMovie(title)) {
			System.out.println("Movie removed from the collection");
		} else {
			System.out.println("Movie cannot be removed from the collection");
		}
	}

	/**
	 * This lists all of the movies in the
	 * collection by number order
	 */
	public void listMovies() {
		
		System.out.print(inventory.listMovies());
		
	}

	/**
	 * This will add a book to the collection
	 */
	public void promptForMovie() {
		
		System.out.print("\nTitle? ");
		String title = in.nextLine();
		System.out.print("Release Year? ");
		while(!in.hasNextInt()) {
			System.out.println("Invalid release year, required int");
			in.nextLine();
			System.out.print("Release Year? ");
		}
		int releaseYear = in.nextInt();
		in.nextLine(); //throw away rest of line
		System.out.print("Genre? ");
		String genre = in.nextLine();
		System.out.print("Rating? ");
		String rating = in.nextLine();
		try {
			Movie m = new Movie(title, releaseYear, genre, rating);
			if (inventory.addMovie(m)){
				System.out.println("Movie added to collection.");
			} else {
				System.out.println("Movie cannot be added to the collection.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid release year.");
		}
	}
	
	/**
	 * This starts the program
	 * @param args a command line of arguments
	 */
	public static void main(String[] args) {
		MovieInventoryUI ui = new MovieInventoryUI();
		ui.userInterface();
	}
}
