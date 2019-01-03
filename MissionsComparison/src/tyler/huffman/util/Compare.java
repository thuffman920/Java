/**
 * 
 */
package tyler.huffman.util;

import tyler.huffman.list.List;

/**
 * @author Tyler Huffman
 */
public class Compare {

	private List<List<String>> diff1;
	
	private List<List<String>> diff2;
	
	public Compare(List<List<String>> list1, List<List<String>> list2) {
		int min = Math.min(list1.size(), list2.size());
		diff1 = new List<List<String>>();
		diff2 = new List<List<String>>();
		diff1.add(list1.get(0));
		diff2.add(list2.get(0));
		for (int i = 1; i < min; i++) {
			int comp = this.compareTo(list1.get(i), list2.get(i));
			if (comp != 0) {
				diff1.add(list1.get(i));
				diff2.add(list2.get(i));
			}
		}
		if (list2.size() != min) {
			diff2 = this.leftOver(diff2, list2, min);
			diff1 = this.addNulls(diff1, list2.size() - min, min + 1);
		} else if (list1.size() != min) {
			diff1 = this.leftOver(diff1, list1, min);
			diff2 = this.addNulls(diff2, list1.size() - min, min + 1);
		}
	}
	
	public int compareTo(List<String> list1, List<String> list2) {
		int value = 0;
		if (list1.size() != list2.size())
			value = 1;
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i)))
				value = 1;
		}
		return value;
	}
	
	public List<List<String>> leftOver(List<List<String>> list, List<List<String>> parts, int min) {
		while (min < parts.size()) {
			list.add(parts.get(min));
			min++;
		}
		return list;
	}
	
	public List<List<String>> addNulls(List<List<String>> list, int number, int min) {
		while (number > 0) {
			List<String> dud = new List<String>();
			dud.add("" + min);
			for (int i = 0; i < list.get(0).size() - 1; i++)
				dud.add("");
			list.add(dud);
			number--;
			min++;
		}
		return list;
	}
	
	public List<List<String>> getFirstDifference() {
		return diff1;
	}
	
	public List<List<String>> getSecondDifference() {
		return diff2;
	}
	
	public int getMax() {
		int max = 0;
		int maxest = 0;
		for (int i = 0; i < diff1.size(); i++) {
			if (diff1.get(i).toString().length() >= diff2.get(i).toString().length()) {
				if (diff1.get(i).toString().length() > max) {
					maxest = 1;
					max = diff1.get(i).toString().length();
				}
			} else {
				if (diff2.get(i).toString().length() > max) {
					maxest = 2;
					max = diff2.get(i).toString().length();
				}
			}
		}
		return maxest;
	}
}