package edu.ncsu.csc216.movie_inventory;

/**
 * This creates a Movie object that contains 
 * the title, the released year, the genre, and
 * the rating for the movie
 * @author Tyler Huffman
 */
public class Movie {
	
	/** The released year of the Movie quantity*/
	private int releaseYear;
	/** The genre of the Movie quantity */
	private String genre;
	/** The rating of the Movie quantity */
	private String rating;
	/** The title of the Movie quantity */
	private String title;
	
	/**
	 * This outputs the title to the inventory
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This outputs the release year to the inventory
	 * @return the releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}
	
	/**
	 * This outputs the genre to the inventory
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * This outputs the rating to the inventory
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	
	/**
	 * 
	 * @param title the title of the Movie
	 * @param releaseYear the released year of the Movie
	 * @param genre the genre of the Movie
	 * @param rating the rating of the Movie
	 */
	public Movie(String title, int releaseYear, String genre, String rating) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}
	
	/**
	 * This creates a string of the description of
	 * the current Movie
	 * @return the Movie's details into a String
	 */
	public String toString() {
		String movieString = "";
		try {
			if (this.title != null) {
				movieString = "Movie [title=" + this.title +", releaseYear="
		            + this.releaseYear + ", genre=" + this.genre + ", rating="
		            + this.rating + "]";
			}
		} catch (NullPointerException e) {
			movieString = "Empty";
		}
		return movieString;
	}
}
