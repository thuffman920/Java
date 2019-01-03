package edu.ncsu.csc216.movie_inventory;

/**
 * @author Tyler Huffman
 *
 */
public class MovieInventory {
	
	/** The size of the collection */
	private static final int INVENTORY_SIZE = 10;
	/** The collection of Movie's */
	private Movie [] movies;
	
	/** 
	 * This creates a collection of Movie's
	 */
	public MovieInventory() {
		movies = new Movie[INVENTORY_SIZE];
	}
	
	/**
	 * Returns true if the Movie can be added to the inventory. If the
	 * Movie is a duplicate or if there is no more space, the method
	 * returns false
	 * @param m Movie o add to the inventory
	 * @return true if the Movie can be added to the inventory
	 */
	public boolean addMovie(Movie m) {
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null && movies[i].equals(m)){
				return false; //movie already exists
			}
		}
		boolean added = false; //flag
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] == null) { //the movie slot is empty
				movies[i] = m;
				added = true;
				break; //if we added the movie, break the loop
			}
		}
		return added; //return the flag
	}
	
	/**
	 * This creates a String of all of the details 
	 * of the movies in the collection
	 * @return a String of the movies
	 */
	public String listMovies() {
		String list = "";
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null) {
				list = list + (i+1) + ". " + movies[i].toString() + "\n";
			} else {
				list = list + (i+1) + ". Empty\n";
			}
		}
		return list;
	}
	
	/**
	 * This removes a movie by its title from
	 * the collection of Movies
	 * @param title the title of the movie you want to remove
	 * @return if the movie was removed, then it returns true
	 */
	public boolean removeMovie(String title) {
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null) {
				if (movies[i].getTitle().equalsIgnoreCase(title)) {
					movies[i] = null;
					return true;
				}
			}
		}
		return false;
	}
}
