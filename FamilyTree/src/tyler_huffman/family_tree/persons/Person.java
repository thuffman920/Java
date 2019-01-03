package tyler_huffman.family_tree.persons;

/**
 * 
 * @author Tyler Huffman
 */
public class Person {

	/** This is the name of the person */
	private String name;
	/** This is the birth date of this person */
	private String birth;
	/** This is the death date of this person */
	private String death;
	/** This is the names of the children in which this person beared */
	private String[] children;
	
	/**
	 * 
	 * @param name
	 * @param birth
	 * @param death
	 * @param children
	 */
	public Person(String name, String birth, String death, String[] children) {
		this.name = name;
		this.birth = birth;
		this.death = death;
		this.children = children;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBirth() {
		return this.birth;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDeath() {
		return this.death;
	}
	
	/**
	 * 
	 * @param birth
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	/**
	 * 
	 * @param death
	 */
	public void setDeath(String death) {
		this.death = death;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getChildren() {
		return this.children;
	}
	
	/**
	 * 
	 * @param children
	 */
	public void setChildren(String[] children) {
		this.children = children;
	}
	
	/**
	 * 
	 */
	public String toString() {
		String result = "" + this.name + "; Born: " + this.birth + "; Died: " + this.death;
		if (children != null && children.length != 0) {
			result += "; Children: ";
			for (int i = 0; i < children.length - 1; i++) {
				result += children[i] + ", ";
			}
			result += children[children.length - 1] + "\n";
		} else 
			result += "\n";
		return result;
	}
}