/**
 * 
 */
package tyler.huffman.list;

/**
 * @author Tyler Huffman
 */
public class List<E> {

	/** This is the front item in the list */
	private Node head;
	/** This is the rear item in the list */
	private Node rear;
	/** This is the size of the list */
	private int size;
	
	/**
	 * This initializes the list to be 
	 * constructed
	 */
	public List() {
		head = null;
	}
	
	/**
	 * 
	 * @author Tyler Huffman
	 */
	private class Node {
		
		/** This is the data of the node */
		private E data;
		/** This is the next node in the list */
		private Node next;
		
		/**
		 * This constructs a node for the list to
		 * be able to iterate through
		 * @param data the data of this node
		 * @param next the next node in the list
		 */
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * This adds the next item to the back of the list
	 * @param next the next item to be added to the list
	 */
	public void add(E next) {
		Node newNode = new Node(next, null);
		if (head == null) {
			head = newNode;
			rear = head;
		} else {
			rear.next = newNode;
			rear = rear.next;
		}
		size++;
	}
	
	/**
	 * This removes and returns the front item in the 
	 * list
	 * @param index the index of the object in the list
	 * @return the front element
	 */
	public E get(int index) {
		Node pointer = head;
		while (pointer != null && index > 0) {
			pointer = pointer.next;
			index--;
		}
		if (pointer != null)
			return pointer.data;
		return null;
	}
	
	/**
	 * This determines if the list is empty
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * This determines the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return this.size;
	}
	
	public void change(int index, E data) {
		Node pointer = head;
		while (pointer != null && index > 0) {
			pointer = pointer.next;
			index--;
		}
		if (pointer != null)
			pointer.data = data;
	}
	
	public String toString() {
		Node pointer = head;
		String result = "";
		while (pointer != null) {
			result += pointer.data + "\t";
			pointer = pointer.next;
		}
		return result;
	}
}