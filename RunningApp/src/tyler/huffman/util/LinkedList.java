package tyler.huffman.util;

/**
 * @author thuff
 *
 */
public class LinkedList<E> {

	private Node head;
	
	private int size;
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	private class Node {
		
		public E data;
		
		public Node next;
		
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	public void add(int index, E next) {
		Node newNode = new Node(next, null);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
			size++;
			return;
		}
		Node pointer = head;
		Node previous = head;
		while(pointer.next != null && index > 0) {
			previous = pointer;
			pointer = pointer.next;
			index--;
		}
		if (pointer != null) {
			newNode.next = pointer;
			previous.next = newNode;
			size++;
		}
	}
	
	public E get(int index) {
		if (index == 0)
			return head.data;
		else {
			Node pointer = head;
			while (pointer != null && index > 0) {
				pointer = pointer.next;
				index--;
			}
			if (pointer != null)
				return pointer.data;
			return null;
		}
	}
	
	public void remove(int index) {
		if (index == 0) {
			head = head.next;
			size--;
		} else {
			Node pointer = head;
			Node previous = head;
			while (pointer.next != null && index > 0) {
				previous = pointer;
				pointer = pointer.next;
				index--;
			}
			if (pointer != null) {
				previous.next = pointer.next;
				size--;
			}
		}
	}
	
	public void addSort(E next) {
		Record newRecord = (Record)(next);
		if (size == 0)
			head = new Node(next, null);
		else if (newRecord.compareTo((Record)(head.data)) == -1)
			head = new Node(next, head);
		else {
			Node pointer = head;
			Node previous = head;
			while (pointer != null) {
				if (newRecord.compareTo((Record)(pointer.data)) == -1)
					break;
				previous = pointer;
				pointer = pointer.next;
			}
			if (pointer == null)
				previous.next = new Node(next, null);
			else
				previous.next = new Node(next, pointer);
		}
		size++;
	}
	
	public int size() {
		return size;
	}

	public String toStringRecord() {
		String result = "";
		Node pointer = head;
		for (int i = 0; i < size; i++) {
			result += pointer.data.toString() + "\n";
			pointer = pointer.next;
		}
		return result;
	}
	
	public String toStringActivity() {
		String result = "";
		Node pointer = head;
		for (int i = 0; i < size; i++) {
			result += "Activity " + i + ": " + pointer.data.toString() + "\n";
			pointer = pointer.next;
		}
		return result;
	}
}