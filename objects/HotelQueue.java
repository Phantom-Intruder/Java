package objects;

import java.io.IOException;
import java.util.Arrays;

public class HotelQueue {

	private static int sizeOfQueue;
	// for the size of queue
	private static int[] fPointer = new int[11];
	// to hold front pointers
	private static int[] rPointer = new int[11];
	// to hold rear pointers
	private static HotelQueue[] hotel = new HotelQueue[11];
	// This will hold the queues to all the rooms
	private static String[][] queueArray = new String[11][7];
	// This will be used to access the queues inside the arrays
	private int timesRun;
	// Records the first time a queue is accessed
	private static int queueOccupieed = 0;

	// Used to signal whether queue is empty or not

	HotelQueue(int size) {

		setSizeOfQueue(size);
		// Sets the size of queue
	}

	public static void initialiseQueue() {
		// Initializes the queue by placing "e" into every array element
		for (int n = 1; n < getHotel().length; n++) {
			for (int j = 0; j < getSizeOfQueue(); j++) {
				getQueueArray()[n][j] = "e";

			}
		}

	}

	public void addToQueue(String input, int roomNum) {

		if (getrPointer()[roomNum] == 7) {
			// Checks if rear pointer is at end of queue
			if (!getQueueArray()[getfPointer()[roomNum]].equals("e")) {
				// If the rear pointer is at end of queue then send rear pointer
				// to front of queue
				getrPointer()[roomNum] = 0;
				// rear pointer set to 0
			} else {
				// If all the rooms are full and rear pointer can't go to the
				// front
				System.out.println("Queue full");
				return;
			}

		}

		if (timesRun == 0) {

			timesRun++;
			// when the program is run for the first time, the front pointer and
			// rear pointer are the same. In order to ensure that the program
			// doesn't mistake it for a full queue.
		} else {
			// If front = rear that means queue is full
			if (getfPointer()[roomNum] == getrPointer()[roomNum]) {
				System.err.println("queue is full");
				System.out.println();
				// displays oldest queue item
				System.out.println("Oldest queue item is:"
						+ getQueueArray()[roomNum][getfPointer()[roomNum]]);
				return;
			}
		}
		// add the value to the queue
		getQueueArray()[roomNum][getrPointer()[roomNum]] = input;
		// Send the rear pointer to the next space in the queue
		getrPointer()[roomNum]++;

		queueOccupieed++;

		System.out.println("Customer " + input + " was added to room\n");

	}

	public static void deleteFromQueue(int roomNum) {
		// Check whether the queue is empty
		if (queueOccupieed > 0) {

			System.out.println(getQueueArray()[roomNum][getfPointer()[roomNum]]
					+ " was deleted from the queue\n");
			// Setting value to be deleted as "e" which signals that the element
			// is empty
			getQueueArray()[roomNum][getfPointer()[roomNum]] = "e";

			// Increment the front pointer in order allow that space to be taken
			// by the rear pointer which will add values if needed
			getfPointer()[roomNum]++;

			queueOccupieed--;

		} else {

			System.out.println("Room queue is empty");

		}

	}

	public static void extractTopOfQueue(int roomNum) {
		int i = 0;
		while (i < 3) {
			// If the queue is empty then there are no values to display
			if (getQueueArray()[roomNum][getfPointer()[roomNum] + i]
					.equals("e")) {
				System.out.println("No more elements in the queue");
				try {
					Main.runProgram(getHotel());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			// Display front pointer value (three top most of queue)
			System.out.println("The First three Customers to be added are: "
					+ getQueueArray()[roomNum][getfPointer()[roomNum] + i]);
			i++;
		}
		try {
			Main.runProgram(getHotel());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showQueue(HotelQueue[] hotel) {

		for (int n = 1; n < hotel.length; n++) {
			// Below line used to improve visibility
			System.out.println("---------------");
			for (int j = 0; j < getSizeOfQueue(); j++) {
				// display elements
				System.out.println(getQueueArray()[n][j]);

			}
		}
	}

	// Below are the get and set methods for the variables.
	// As encapsulation was used these are required.

	public static int[] getfPointer() {
		return fPointer;
	}

	public static void setfPointer(int[] fPointer) {
		HotelQueue.fPointer = fPointer;
	}

	public static int[] getrPointer() {
		return rPointer;
	}

	public static void setrPointer(int[] rPointer) {
		HotelQueue.rPointer = rPointer;
	}

	public static HotelQueue[] getHotel() {
		return hotel;
	}

	public static void setHotel(HotelQueue[] hotel) {
		HotelQueue.hotel = hotel;
	}

	public static String[][] getQueueArray() {
		return queueArray;
	}

	public static void setQueueArray(String[][] queueArray) {
		HotelQueue.queueArray = queueArray;
	}

	public static int getSizeOfQueue() {
		return sizeOfQueue;
	}

	public static void setSizeOfQueue(int sizeOfQueue) {
		HotelQueue.sizeOfQueue = sizeOfQueue;
	}

}
