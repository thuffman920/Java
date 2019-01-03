/**
 * 
 */
package tyler.huffman.util;

import java.time.LocalDateTime;

/**
 * @author Tyler Huffman
 */
public class Record {

	private long startTime;
	
	private long elapseTime;
	
	private String endTime;
	
	private String input;
	
	private boolean isCurrent;
	
	public Record(String input) {
		this.input = input;
		if (input.charAt(2) == '/') {
			endTime = input.substring(0, 23);
			input = input.substring(24);
			double elapse = Double.parseDouble(input.substring(0, input.indexOf(" ") + 1));
			elapseTime = (int)(1000 * elapse);
			input = input.substring(input.indexOf(" ") + 1);
			this.input = input;
			isCurrent = false;
		} else {
			startTime = System.currentTimeMillis();
			isCurrent = true;
		}
	}
	
	public void setEndTime() {
		LocalDateTime date = LocalDateTime.now();
		String time = date.toString();
		endTime = "" + time.substring(5, 7) + "/";
		endTime += time.substring(8, 10) + "/";
		endTime += time.substring(0, 4) + " ";
		endTime += LocalDateTime.now().toString().substring(11);
		elapseTime = System.currentTimeMillis() - startTime;
	}
	
	public double getElapseTime() { 
		return elapseTime / 1000.0;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public String toString() {
		if (isCurrent)
			this.setEndTime();
		return this.getEndTime() + " " + this.getElapseTime() +
				" " + input;
	}
	
	public boolean isCurrent() {
		return isCurrent;
	}

	public int compareTo(Record record) {
		if (isCurrent)
			setEndTime();
		if (record.isCurrent())
			record.setEndTime();
		int min = Math.min(record.getEndTime().length(),
				this.getEndTime().length());
		for (int i = 0; i < min; i++) {
			if (endTime.charAt(i) < record.getEndTime().charAt(i))
				return -1;
			else if (endTime.charAt(i) > record.getEndTime().charAt(i))
				return 1;
		}
		return 0;
	}
}