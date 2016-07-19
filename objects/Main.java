package objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.*;
import java.util.regex.Pattern;

public class Main extends HotelQueue {
	Main(int size) {
		super(size);

	}

	// Holds the value for the room number
	protected static int roomNum = 0;

	private static void initialisePointers() {
		// This method runs in order to set the front and rear pointers to 0
		for (int i = 1; i < getfPointer().length; i++) {
			getfPointer()[i] = 0;
			getrPointer()[i] = 0;
		}
	}

	public static void main(String[] args) {
		// Below code creates 10 queues for the 10 rooms and assigns them to an
		// object array
		for (int i = 1; i < getHotel().length; i++) {
			getHotel()[i] = new HotelQueue(7);
		}
		initialiseQueue();
		initialisePointers();
		while (true) {
			try {
				// main program body
				runProgram(getHotel());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static void runProgram(HotelQueue[] hotel) throws IOException {
		// create room name variable
		String roomName = null;
		Scanner sc = new Scanner(System.in);
		// Option select menu
		System.out.println("Select option: ");
		System.out.println("D: Delete customer from room");
		System.out
				.println("S: Store program array data into a plain text file");
		System.out
				.println("L: Load program data back from the file into the array");
		System.out.println("A: Add a person to room.");
		System.out.println("V: Veiw room details.");
		System.out
				.println("3: Display the names of the first 3 customers who have been allocated to a room");
		System.out.println("Q : Exit program");
		// get input from user
		String input = sc.next();
		if ((input.equals("Q")) || (input.equals("q"))) {
			System.exit(0);// quit
		} else if ((input.equals("D")) || (input.equals("d"))) {
			deleteCustomer(hotel);// delete customer
		} else if ((input.equals("S")) || (input.equals("s"))) {
			storeArray(hotel);// store array in file
		} else if ((input.equals("L")) || (input.equals("l"))) {
			loadArray(hotel, roomNum);// load to array from file
		} else if ((input.equals("A")) || (input.equals("a"))) {
			addCustomer(hotel, roomNum, roomName);// add customer
		} else if ((input.equals("V")) || (input.equals("v"))) {
			veiwRooms(hotel);// view array
		} else if (input.equals("3")) {
			try {
				// convert the string to integer
				roomNum = Integer.parseInt(input);
			} catch (InputMismatchException e) {
			}
			System.out.println("Input room for customers to be veiwed from");
			try {
				roomNum = sc.nextInt();
				// Validation check
				if (roomNum <= 0 || roomNum > 10) {
					System.err.println("Enter only values within 1 - 10");
					runProgram(getHotel());
				}
			} catch (InputMismatchException e) {
				System.err.println("Only enter numbers from 1 - 10");
				runProgram(getHotel());
			}
			extractTopOfQueue(roomNum);// Veiw top of queue
		} else {
			System.err.println("Please enter only character from above list");
		}// ensure that invalid input isn't entered

	}

	private static void veiwRooms(HotelQueue[] hotel) {

		showQueue(hotel);

		try {
			runProgram(hotel);// go back to main body
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addCustomer(HotelQueue[] hotel, int roomNum,
			String roomName) {
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out
					.println("Press 11 to exit program and 0 to go back to menu");
			System.out
					.println("Enter room number (1-10) for customer to be added");
			try {
				roomNum = input.nextInt();// get room number
			} catch (InputMismatchException e) {// exception catch
				System.err
						.println("Please do not enter anything outside of numbers in valid range");
				addCustomer(hotel, roomNum, roomName);

			}

			try {
				if (roomNum == 11) {
					System.exit(0);// exit
				} else if (roomNum == 0) {
					try {
						runProgram(hotel);// go to main body
					} catch (IOException e) {

						e.printStackTrace();
					}
				} else if ((roomNum > 11) || (roomNum < 0)) {// error condition
					System.err
							.println("Please enter number within valid range");
					addCustomer(hotel, roomNum, roomName);// call itself
				}
				System.out.println("Enter name for room " + roomNum + " :");
				roomName = input.next();// get room name
				boolean alpha = isAlpha(roomName);
				if (alpha != true) {// validation
					System.err.println("Enter only letters");
					addCustomer(hotel, roomNum, roomName);
				}
				// Below code adds customer to queue according to the room
				// chosen
				switch (roomNum) {
				case 1:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 2:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 3:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 4:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 5:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 6:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 7:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 8:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 9:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				case 10:
					hotel[roomNum].addToQueue(roomName, roomNum);
					break;
				}

			} catch (InputMismatchException e) {// exception catch
				System.err
						.println("Please do not enter anything outside of numbers in valid range");
				addCustomer(hotel, roomNum, roomName);

			}
		}
	}

	private static void deleteCustomer(HotelQueue[] hotel) {// delete customer
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out
					.println("Press 11 to exit program and 0 to go back to menu");
			System.out
					.println("Enter room that needs customer to be removed from");
			try {
				int num = sc.nextInt();// get input
				if (num == 11) {
					System.exit(0);// exit
				} else if (num == 0) {
					runProgram(hotel);// main body
				} else if ((num < 0) || (num > 11)) {// error condition
					System.err
							.println("Please only enter numbers in valid range");
					deleteCustomer(hotel);// call itself
				}

				deleteFromQueue(num);
			} catch (InputMismatchException e) {// exception handling
				System.err
						.println("Please do not enter anything outside of numbers in valid range");
				deleteCustomer(hotel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void loadArray(HotelQueue[] hotel, int roomNum)
			throws IOException {
		// load from file to array
		Vector lineArray = new Vector();// create vector array
		String lineContents = null;
		String[] temp = new String[70];
		try {// read data from file
			BufferedReader br = new BufferedReader(new FileReader(
					"roomdata.txt"));
			while ((lineContents = br.readLine()) != null) {
				// add data to array
				lineArray.add(lineContents);
			}
		} catch (FileNotFoundException fne) {// exception handling
			fne.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		for (int i = 0; i < lineArray.size(); i++) {
			temp[i] = (String) (lineArray.get(i));// transfer to temp array
			String[] parts = temp[i].split(Pattern.quote("|"));// split string
			int rNum = Integer.parseInt(parts[1]);
			// The values in the temporary array are added to the hotel array
			// using the addToQueue method
			hotel[rNum].addToQueue(parts[3], rNum);

		}
		runProgram(hotel);
	}

	private static void storeArray(HotelQueue[] hotel) throws IOException {
		// Store array in a text file
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("roomdata.txt"), "utf-8"))) {// create file
			for (int n = 1; n < hotel.length; n++) {
				for (int j = 0; j < getSizeOfQueue(); j++) {
					// If the queue element is empty, it doesn't get recorded in
					// the file
					if (getQueueArray()[n][j].equalsIgnoreCase("e")) {
						continue;
					}
					// write to file
					writer.write("Room |" + n + "| is occupied by |"
							+ getQueueArray()[n][j]);
					((BufferedWriter) writer).newLine();// next line
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		runProgram(hotel);
	}

	public static boolean isAlpha(String name) {
		// Check to ensure string is in alphabetical order
		return name.matches("[a-zA-Z]+");
	}
}
