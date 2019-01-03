package tyler_huffman.family_tree.tree_list;
import tyler_huffman.family_tree.persons.Person;
import tyler_huffman.family_tree.exceptions.ConflictingNodesException;

/**
 * 
 */

/**
 * @author thuff
 *
 */
public class TreeConstructor {

	private Node root;
	
	private int total;
	
	public TreeConstructor(int total) {
		this.root = null;
		this.total = total;
	}
	
	private class Node {
		
		public Node left;
		
		public Node right;
		
		public Person data;
		
		public int key;
		
		public Node(Node left, Person data, int key, Node right) {
			this.left = left;
			this.right = right;
			this.data = data;
			this.key = key;
		}
	}
	
	public void insertPerson(int key, Person next) throws ConflictingNodesException {
		Node p = root;
		Node q = null;
		while (p != null && key != p.key) {
			if (key < p.key) {
				q = p;
				p = p.left;
			} else {
				q = p;
				p = p.right;
			}
		}
		if (p == root && root == null) {
			root = new Node(null, next, this.total / 2 + 1, null);
			root.left = new Node(null, null, root.key / 2, null);
			root.right = new Node(null, null, 3 * root.key / 2, null);
		} else if (p.data != null) {
			String current = p.data.getName();
			String newPerson = next.getName();
			throw new ConflictingNodesException("You are trying to " + newPerson + "; however, " + current + 
					" has been added to this node.\n");
		} else if (p == q.right && key == p.key) {
			p.data = next;
			p.left = new Node(null, null, 5 * p.key / 6, null);
			p.right = new Node(null, null, 7 * p.key / 6, null);
		} else {
			p.data = next;
			p.left = new Node(null, null, p.key / 2, null);
			p.right = new Node(null, null, 3 * p.key / 2, null);
		} 
	}
	
	public void print(Node p) {
		if (p.left.data == null && p.right.data == null)
			System.out.print(p.data.toString());
		else {
			System.out.print("" + p.data.toString());
			print(p.left);
			print(p.right);
		}
		
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeConstructor tree = new TreeConstructor(7);
		Person me = new Person("Tyler Shane Huffman", "January 29, 1995", "", null);
		String[] children = {"Tyler Shane Huffman", "Trista Danielle Huffman"};
		Person dad = new Person("Kenneth Ray Huffman", "July 5, 1964", "", children);
		Person mom = new Person("Janet Carol Martin", "February 25, 1958", "", children);
		String[] children1 = {"Kenneth Ray Huffman", "Bobbie Huffman"};
		Person grandDad1 = new Person("Kenneth Clarence Huffman", "April 11, 1944", "", children1);
		Person grandMom1 = new Person("Bobbie Jean Buckner", "October 22, 1944", "", children1);
		String[] children2 = {"Janet Carol Huffman", "Angelina Jones"};
		Person grandDad2 = new Person("Lester Mallie Martin", "April 4, 1922", "January 24, 2007", children2);
		Person grandMom2 = new Person("Thedis Monzella Tackett", "December 24, 1944", "October 13, 2008", children2);
		try {
			tree.insertPerson(4, me);
			tree.insertPerson(2, dad);
			tree.insertPerson(6, mom);
			tree.insertPerson(1, grandDad1);
			tree.insertPerson(3, grandMom1);			
			tree.insertPerson(5, grandDad2);
			tree.insertPerson(7, grandMom2);
		} catch (ConflictingNodesException e) {
			System.out.println("" + e.getMessage());
		}
		tree.print(tree.getRoot());
	}

}
