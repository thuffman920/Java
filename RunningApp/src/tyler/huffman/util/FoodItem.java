package tyler.huffman.util;

/**
 * @author Tyler Huffman
 */
public class FoodItem {

	public String serving;
	
	public String name;
	
	public double potassium;
	
	public double sodium;
	
	public double iron;
	
	public double calcium;
	
	public double protein;
	
	public double vitaminD;

	public FoodItem(String name, String serving) {
		this.name = name;
		this.serving = serving;
	}
	
	public String getServing() {
		return serving;
	}

	public void setServing(String serving) {
		this.serving = serving;
	}

	public String getName() {
		String result = "";
		if (name.indexOf(",") != -1) {
			result = name.substring(name.indexOf(",") + 2) + " " +
					name.substring(0, name.indexOf(","));
			return result;
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPotassium() {
		return potassium;
	}

	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public double getIron() {
		return iron;
	}

	public void setIron(double iron) {
		this.iron = iron;
	}

	public double getCalcium() {
		return calcium;
	}

	public void setCalcium(double calcium) {
		this.calcium = calcium;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(double vitaminD) {
		this.vitaminD = vitaminD;
	}
	
	public String toStringGUI() {
		return "" + this.getName() + "\n  Serving: " 
				+ serving + "\n\tSodium: " + sodium + " mg\n\tPotassium: "
				+ potassium + " mg\n\tProtein: " + protein + 
				" mg\n\tCalcium: " + calcium + " mg\n\tIron: " + 
				iron + " mg\n\tVitamin D: " + vitaminD + " mg";
	}
	
	public String toStringText() {
		return "" + name + " (" + serving + ") " + potassium + 
				" " + sodium + " " + iron + " " + calcium + 
				" " + protein + " " + vitaminD;
	}
}