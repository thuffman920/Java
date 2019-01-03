package com.example.incoming;

import java.util.Scanner;

/**
 * This determines if in an incoming message is
 * an emergency based upon the keyword
 * @author Tyler Huffman
 */
public class ReadMessage {

	/** The incoming text message */
	private String message;
	/** Determines if the incoming message is an emergency */
	private boolean isEmergency;
	
	/**
	 * This sets up the incoming message and
	 * determines if the message is an emergency
	 * @param message
	 */
	public ReadMessage(String message, String keyword) {
		this.message = message;
		this.determineEmer(keyword);
	}
	
	/**
	 * This returns if the message is an emergency
	 * @return true if the message is an emergency
	 */
	public boolean isEmergency() {
		return this.isEmergency;
	}
	
	/**
	 * This determines if the message is an emergency
	 * based upon the inputted keyword
	 * @param keyword the key emergency word
	 */
	private void determineEmer(String keyword) {
		Scanner reader = new Scanner(this.message);
		while(reader.hasNext()) {
			String line = reader.next();
			if (line.equals(keyword)) {
				isEmergency = true;
				break;
			}
		}
		reader.close();
	}
	
	/**
	 * This returns the incoming message
	 * @return the incoming message
	 */
	public String getMessage() {
		return this.message;
	}
}