package com.example.database;

import java.util.ArrayList;
import com.example.contacts.Person;

/**
 * 
 * @author Tyler Huffman
 */
public class Contacts {

	/**  */
	private ArrayList<Person> contacts;
	
	/**
	 * 
	 */
	public Contacts() {
		contacts = new ArrayList<Person>();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean determineIsContact(String incomeNumber) {
		for (int i = 0; i < this.contacts.size(); i++) {
			if (contacts.get(i).getNumber().equals(incomeNumber)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This determine the list of all emergency
	 * contacts from the full list of all contacts
	 * @return the emergency contacts
	 */
	public ArrayList<Person> emergencyContact() {
		ArrayList<Person> emerContact = new ArrayList<Person>();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getIsEmerContact()) {
				emerContact.add(contacts.get(i));
			}
		}
		return emerContact;
	}
	
	/**
	 * 
	 */
	public Person get(int i) {
		return contacts.get(i);
	}
	
	/**
	 * 
	 * @param name
	 * @param number
	 * @param emer
	 */
	public void add(String name, String number, boolean emer) {
		Person next = new Person(number, name);
		if (emer)
			next.isEmerContact();
		this.alphabetize(next);
	}
	
	/**
	 * 
	 * @param next
	 */
	private void alphabetize(Person next) {
		String name = next.getPersonName();
		String other = "";
		boolean add = false;
		for (int i = 0; i < contacts.size(); i++) {
			other = contacts.get(i).getPersonName();
			int compareTo = name.compareTo(other);
			if (compareTo < 0) {
				contacts.add(i, next);
				add = true;
				break;
			}
		}
		if (!add)
			contacts.add(next);
	}
	
	/**
	 * 
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getIsEmerContact())
				result += "* ";
			result += contacts.get(i).toString() + "\n";
		}
		return result;
	}
}