package com.example.database;

import com.example.incoming.PhoneNumber;
import com.example.incoming.ReadMessage;

/**
 * 
 * @author Tyler Huffman
 */
public class Centrality {

	/**  */
	private Contacts contacts;
	/**  */
	private PhoneNumber incoming;
	/**  */
	private String bottom;
	/**  */
	private String top;
	/**  */
	private ReadMessage message;
	/**  */
	private String keyword;
	
	/**
	 * 
	 */
	public Centrality() {
		contacts = new Contacts();
		this.readContacts();
	}
	
	/**
	 * 
	 * @param number
	 * @param timestamp
	 * @return
	 */
	public boolean emerPhoneCall(String number, String timestamp) {
		incoming = new PhoneNumber(number, timestamp, contacts, bottom, top);
		return incoming.getIsEmerContact();
	}
	
	/**
	 * 
	 */
	private void readContacts() {
		
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public boolean emerTextMessage(String message) {
		this.message = new ReadMessage(message, keyword);
		return this.message.isEmergency();
	}
	
	/**
	 * 
	 * @param bottom
	 * @param top
	 */
	public void setRange(String bottom, String top) {
		this.bottom = bottom;
		this.top = top;
	}
	
	/**
	 * 
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword= keyword;
		
	}
}