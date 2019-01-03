
/**
 * Running on the PuTTY system;
 * To compile, do: javac Sleeper.java
 *                 javac Coordinate.java
 *                 javac Rider.java
 *                 java Rider
 * @author Tyler Huffman
 */
public class Rider extends Thread {

	private int riderId;
	private Coordinator coordinate;
	
	public Rider(int riderId, Coordinator coordinate){
		this.riderId = riderId;
		this.coordinate = coordinate;
	}
	
	public void run() {
		while (true) {
			System.out.println("Rider " + riderId + " is walking around the park.");
			Sleeper.walkAround();
			int carId = coordinate.getInLine();
			System.out.println("Rider " + riderId + " is now riding in car " + carId + ".");
			Sleeper.rideTime();
			coordinate.returnCar(carId);
			System.out.println("Rider " + riderId + " returned car " + carId + ".");
		}
	}
	
	public static void main(String [] args) {
		int bumperCars = Integer.parseInt(args[0]);
		int no_of_threads = Integer.parseInt(args[1]);
		Coordinator coordinate = new Coordinator(bumperCars, no_of_threads);
		
		System.out.println("" + args[0] + " " + args[1] + " " + args[2]);

		Rider[] list = new Rider[no_of_threads];
		for (int i = 0; i < no_of_threads; i++) {
			list[i] = new Rider(i, coordinate);
			list[i].start();
		}
		try {
			Thread.sleep(Integer.parseInt(args[2])*1000);
			System.exit(0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
