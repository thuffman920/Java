package pattern_creator.tyler.huffman.linked_list;

/**
 * @author Tyler Huffman
 */
public class LinkedList<E> {

	private Node head;
	private int size;
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	private class Node {
		
		public Node next;
		public E item;
		
		public Node(Node next, E item) {
			this.item = item;
			this.next = next;
		}
	}
	
	public void addEnd(E next) {
		if (head == null)
			head = new Node(null, next);
		else {
			Node pointer = head;
			while(pointer.next != null)
				pointer = pointer.next;
			pointer.next = new Node(null, next);
		}
		size++;
	}
	
	public E getIndex(int index) {
		int i = 0;
		Node pointer = head;
		while (i < index) {
			pointer = pointer.next;
			i++;
		}
		return pointer.item;
	}
	
	public int getIndexByItem(E item) {
		int i = 0;
		Node pointer = head;
		while (!pointer.item.equals(item)) {
			pointer = pointer.next;
			i++;
		}
		return i;
	}
	
	public String toString() {
		String result = "";
		Node pointer = head;
		while (pointer != null) {
			result += "" + pointer.item.toString() + "\t";
			pointer = pointer.next;
		}
		return result;
	}
	
	public int getSize() {
		return this.size;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedList other = (LinkedList) obj;
		Node pointer1 = head;
		Node pointer2 = other.head;
		while(pointer1 != null) {
			if (pointer2 == null)
				return false;
			else if (!pointer1.item.equals(pointer2.item))
				return false;
			pointer1 = pointer1.next;
			pointer2 = pointer2.next;
		}
		if (pointer2 != null)
			return false;
		return true;
	}
}
