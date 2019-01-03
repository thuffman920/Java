public class Coordinator {
	
	public int bumperCars;
	public int maxBumperCars;
	public int[] store;
	
	public Coordinator(int bumperCars, int size) {
		this.bumperCars = 0;
		this.maxBumperCars = bumperCars;
		store = new int[bumperCars];
	}
	
	public synchronized int getInLine() {
		if (this.bumperCars == this.maxBumperCars)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		bumperCars++;
		int carId = 0;
		for (int i = 0; i < maxBumperCars; i++) {
			if (store[i] == 0) {
				carId = i + 1;
				store[i] = 1;
				break;
			}
		}
		return carId;
	}
	
	public synchronized void returnCar(int carId) {
		store[carId - 1] = 0;
		bumperCars--;
	}
}