package com.example.contacts;

/**
 * This creates the contact information from the
 * contact list on the phone, it has name, phone 
 * number, and if they are an emergency contact
 * @author Tyler Huffman
 */
public class Person {

	/** The contact's phone number */
	private String number;
	/** The contact's name */
	private String personName;
	/** The contact is an emergency contact or not */
	private boolean isEmerContact;
	
	/**
	 * This creates the object for this contact's
	 * name and phone number
	 * @param number the contact's number
	 * @param personName the contact' name
	 * @throws IllegalArgumentException if the number is too long or too
	 * 		short
	 */
	public Person(String number, String personName) {
		if (number.length() < 10 || number.length() > 11)
			throw new IllegalArgumentException("Exception: Incorrect number!");
		this.number = number;
		this.personName = personName;
		isEmerContact = false;
	}
	
	/**
	 * This returns if this person is an emergency
	 * contact or not
	 * @return true if the person is an emergency
	 * 		contact
	 */
	public boolean getIsEmerContact() {
		return isEmerContact;
	}
	
	/**
	 * This sets this person to be an emergency
	 * contact
	 */
	public void isEmerContact() {
		isEmerContact = true;
	}
	
	/**
	 * This returns this contacts phone number
	 * @return the phone number of this contact
	 */
	public String getNumber() {
		return this.number;
	}
	
	/**
	 * This returns the name of this contact's
	 * name
	 * @return the contact's name
	 */
	public String getPersonName() {
		return this.personName;
	}
	
	/**
	 * This creates a string of this contact's
	 * information: name followed by phone number
	 * @return the string result
	 */
	public String toString() {
		String result = "";
		result = result + this.getPersonName() + " ";
		String no = this.getNumber();
		if (no.length() == 10)
			result = result + "[(" + no.substring(0,3)+ ") " + no.substring(3,6)
			+ "-" + no.substring(6,10) + "]";
		else
			result = result + "[(" + no.substring(1,4)+ ") " + no.substring(4,7)
			+ "-" + no.substring(7,11) + "]";			
		return result;
	}
}