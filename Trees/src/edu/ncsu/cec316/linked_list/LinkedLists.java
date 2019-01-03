/**
 * 
 */
package edu.ncsu.cec316.linked_list;

/**
 * @author thuff
 *
 */
public class LinkedLists {

	private Node head;
	
	public LinkedLists() {
		this.head = null;
	}
	
	private class Node {
		
		public String data;
		
		public Node next;
		
		public Node(String data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	public String lookUp(int i) {
		Node p = head;
		int k = 0;
		while (p != null && k < i) {
			p = p.next;
			k++;
		}
		return p.data;
	}
	
	public String removeAt(int i) {
		String data = "";
		if (i == 0) {
			data = head.data;
			head = head.next;
		} else {
			Node p = head;
			Node q = null;
			int k = 1;
			while (p != null && k < i) {
				q = p;
				p = p.next;
				k++;
			}
			if (p != null) {
				data = p.data;
				q.next = p.next;
			}
			return null;
		}
		return data;
	}
	
	public void removeAfter(String next) {
		removeAfter(head, next);
	}
	
	public void removeAfter(Node p, String next) {
		if (p.next != null && p.next.data.equals(next))
			p.next = p.next.next;
		else if (p.next != null)
			removeAfter(p.next, next);
	}
	
	public void insert(String next) {
		Node p = head;
		if (p == null) 
			head = new Node(next, null);
		else {
			p = head.next;
			Node q = head;
			while (p != null) {
				p = p.next;
				q = q.next;
			}
			q.next = new Node(next, null);
		}
	}
	
	public void insertBefore(int i, String next) {
		if (i == 0) {
			Node newHead = new Node(next, head);
			head = newHead;
		} else {
			Node p = head;
			int k = 1;
			while (p != null && k < i) {
				p = p.next;
				k++;
			}
			Node q = p.next;
			p.next = new Node(next, q);
		}
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	public int size() {
		Node p = head;
		int i = 0;
		while (p != null) {
			p = p.next;
			i++;
		}
		return i;
	}
	
	public String toString() {
		Node p = head;
		String result = "";
		while (p != null) {
			result += p.data + "\n";
			p = p.next;
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedLists list = new LinkedLists();
		System.out.println("Empty List: " + list.isEmpty());
		list.insert("Alex Brown");
		list.insert("Tyler Huffman");
		list.insertBefore(0, "Austin Amaral");
		list.insertBefore(2, "Daniel Klein");
		list.insertBefore(3, "Matthew Powell");
		System.out.println("List:\n" + list.toString() + "Size: " + list.size() + "\nEmpty List: " + list.isEmpty());
		list.removeAt(2);
		System.out.println("List:\n" + list.toString() + "Size: " + list.size() + "\n");
		list.removeAfter("Matthew Powell");
		System.out.println("List:\n" + list.toString() + "Size: " + list.size() + "\n");
		list.insert("Trista Huffman");
		System.out.println("List:\n" + list.toString() + "Size: " + list.size() + "\n");
		list.insertBefore(2, "Matthew Powell");
		System.out.println("List:\n" + list.toString() + "Size: " + list.size() + "\n");
	}
}