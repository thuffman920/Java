package com.example.incoming;

import java.util.ArrayList;
import com.example.contacts.Person;
import com.example.database.Contacts;

/**
 * 
 * @author Tyler Huffman
 */
public class PhoneNumber {

	/** The phone number of the incoming call */
	private String incomeNumber;
	/** The time stamp of the incoming car */
	private String timestamp;
	/** The list of all contacts on the phone */
	private Contacts contacts;
	/** True if incoming number is an emergency contact */
	private boolean isEmerContact;
	/** True if incoming number is a contact */
	private boolean isContact;
	
	/**
	 * 
	 * @param incomeNumber
	 * @param timestamp
	 * @param contact
	 */
	public PhoneNumber(String incomeNumber, String timestamp, Contacts contacts,
			String bottom, String top) {
		this.incomeNumber = incomeNumber;
		this.timestamp = timestamp;
		this.contacts = contacts;
		isContact = contacts.determineIsContact(incomeNumber);
		if (isContact)
			this.determineIsEmerContact();
		if(isContact && !isEmerContact)
			this.determineIsInRange(bottom, top);
	}
	
	/**
	 * 
	 * @param bottom
	 * @param top
	 */
	public void determineIsInRange(String bottom, String top) {
		timestamp.equals(bottom);
		
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIsContact() {
		return isContact;
	}
	
	public boolean getIsEmerContact() {
		return isEmerContact;
	}
	
	
	
	/**
	 * Determines if the incoming contact is 
	 * an emergency from the inputted emergency contacts
	 */
	public void determineIsEmerContact() {
		ArrayList<Person> emerContact = contacts.emergencyContact();
		isEmerContact = false;
		for (int i = 0; i < emerContact.size(); i++) {
			if (emerContact.get(i).getNumber().equals(incomeNumber)) {
				isEmerContact = true;
				break;
			}
		}
	}
}