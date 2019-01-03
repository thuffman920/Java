/**
 * Compilation: javac DiningThread.java
 * 				javac Barrier.java
 * 				java DiningThread
 * Note: Was constructed on Eclipse
 * @author Tyler Huffman
 */

public class DiningThread extends Thread {
	
	private Barrier barrier;
	private String  task; //e.g. "Task A"
	private int     id; 
	
	public DiningThread (Barrier b, String work, int threadid) {
		barrier = b;
		task    = work;
		id      = threadid;
	}

	public void run ( ) {
		
		System.out.println("I am thread " + id + ", working on task "
				+ task + ", and I am waiting to eat.");
		
		barrier.waitForOthers();
		System.out.println("I am thread " + id + " and I am eating.");
	}

	public static void main (String [] args)throws InterruptedException {

		Barrier b = new Barrier (4); //4 threads must be synchronized

		// create DiningThread objects
		Thread[] list_threads = new Thread[4];
		list_threads[0] = new DiningThread(b, "A", 1);
		list_threads[1] = new DiningThread(b, "B", 2);
		list_threads[2] = new DiningThread(b, "C", 3);
		list_threads[3] = new DiningThread(b, "D", 4);
		
		// start them
		for (int i = 0; i < 4; i++)
			list_threads[i].start();
		
		// join them
		for (int i = 3; i >= 0; i--)
			list_threads[i].join();
	}
}