package tyler.huffman.util;

/**
 * @author Tyler Huffman
 */
public class Activity {

	/** The three letter names for each month */
	public String[] monthName = {"Jan", "Feb", "Mar", "Apr",
			"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	/** The day that this activity occurred */
	public int day;
	/** The month that this activity occurred */ 
	public int month;
	/** The year that this activity occurred */
	public int year;
	/** The potassium that was ingested for this
	 * activity */
	public double potassium;
	/** The sodium that was ingested for this
	 * activity */
	public double sodium;
	/** The iron that was ingested for this
	 * activity */
	public double iron;
	/** The calcium that was ingested for this
	 * activity */
	public double calcium;
	/** The protein that was ingested for this
	 * activity */
	public double protein;
	/** The vitamin D that was ingested for this
	 * activity */
	public double vitaminD;
	
	/**
	 * This creates an activity
	 * @param month the month of this activity
	 * @param day the day of this activity
	 * @param year the year of this activity
	 */
	public Activity(int month, int day, int year) {
		if (month <= 0 || month > 12)
			throw new IllegalArgumentException("Month is out of bounds");
		if (day <= 0)
			throw new IllegalArgumentException("Day is out of bounds");
		else if ((month == 1 || month == 3 || month == 5 | month == 7
				|| month == 8 || month == 10 | month == 12) && day > 31)
			throw new IllegalArgumentException("Day is out of bounds");
		else if ((month == 4 || month == 6 || month == 9 || month == 11)
				&& day > 30)
			throw new IllegalArgumentException("Day is out of bounds");
		else if (month == 2 && day > 29)
			throw new IllegalArgumentException("Day is out of bounds");
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * This determines the full date of this certain
	 * activity
	 * @return the date of this event
	 */
	public String getDate() {
		String result = "";
		if (month < 10)
			result += "0" + month;
		else 
			result += month;
		if (day < 10)
			result += "/0" + day;
		else 
			result += "/" + day;
		return result + "/" + year;
	}
	
	/**
	 * This sets the date of this activity
	 * @param date the date of this activity
	 */
	public void setDate(String date) {
		this.month = Integer.parseInt(date.substring(0, 2));
		this.day = Integer.parseInt(date.substring(3, 5));
		this.year = Integer.parseInt(date.substring(6, date.length()));
	}
	
	/**
	 * The returns the amount of potassium ingested during
	 * this event
	 * @return the amount of potassium
	 */
	public double getPotassium() {
		return potassium;
	}

	/**
	 * This sets the amount of potassium ingested during
	 * this event
	 * @param potassium the amount of potassium
	 */
	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}

	/**
	 * 
	 * @return
	 */
	public double getSodium() {
		return sodium;
	}

	/**
	 * 
	 * @param sodium
	 */
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	/**
	 * 
	 * @return
	 */
	public double getIron() {
		return iron;
	}

	/**
	 * 
	 * @param iron
	 */
	public void setIron(double iron) {
		this.iron = iron;
	}

	/**
	 * 
	 * @return
	 */
	public double getCalcium() {
		return calcium;
	}

	/**
	 * 
	 * @param calcium
	 */
	public void setCalcium(double calcium) {
		this.calcium = calcium;
	}

	/**
	 * 
	 * @return
	 */
	public double getProtein() {
		return protein;
	}

	/**
	 * 
	 * @param protein
	 */
	public void setProtein(double protein) {
		this.protein = protein;
	}

	/**
	 * 
	 * @return
	 */
	public double getVitaminD() {
		return vitaminD;
	}

	/**
	 * 
	 * @param vitaminD
	 */
	public void setVitaminD(double vitaminD) {
		this.vitaminD = vitaminD;
	}
	
	/**
	 * 
	 * @return the data for this certain activity
	 */
	public String toString() {
		return day + " " + this.monthName[month - 1] + " " + year + " " 
				+ potassium + " " + sodium + " " + iron + " " 
				+ calcium + " " + protein + " "  + vitaminD;
	}
}