/**
 * 
 */
package edu.ncsu.cec316.stacks;

import java.util.EmptyStackException;

import edu.ncsu.cec316.exceptions.FullStackException;

/**
 * @author thuff
 *
 */
public class Stacks {

	private String[] S;
	
	private int top;

	private int N;
	
	public Stacks(int N) {
		top = 0;
		this.N = N;
		S = new String[this.N];
	}
	
	public int size() {
		if (top == 0)
			return top;
		return top + 1;
	}
	
	public String pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			top--;
			return S[top + 1];
		}
	}
	
	public void push(String next) throws FullStackException {
		if (size() == N) 
			throw new FullStackException();
		else { 
			top++;
			S[top] = next;
		}
	}
	
	public String peek() {
		return S[top];
	}
	
	public boolean isEmpty() {
		return (top == 0);
	}
	
	public String toString() {
		int p = 1;
		String result = "";
		while (p <= top) {
			result += S[p] + "\n";
			p++;
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stacks stack = new Stacks(5);
		System.out.println("Size: " + stack.size() + ", Empty List: " + stack.isEmpty());
		try {
			stack.push("Alex Brown");
			stack.push("Austin Amaral");
			stack.push("Tyler Huffman");
		} catch (FullStackException e) {
			e.printStackTrace();
		}
		System.out.println("List:\n" + stack.toString() + "Size: " + stack.size());
		stack.pop();
		System.out.println("List:\n" + stack.toString() + "Size: " + stack.size());
		try {
			stack.push("Tyler Huffman");
			stack.push("Daniel Klein");
			stack.push("Matthew Powell");
			stack.push("Trista Huffman");
		} catch (FullStackException e) {
			e.printStackTrace();
		}
		System.out.println("Top: " + stack.peek());
	}
}