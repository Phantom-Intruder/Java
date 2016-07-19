package arrays;

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

public class Main {
	public static void main(String[] args) {
		String[] hotel = new String[11];// Create hotel array
		initialise(hotel);// initialise hotel array
		while (true) {
			try {
				runProgram(hotel);// main program body
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void runProgram(String[] hotel) throws IOException {
		String roomName = null;// create room name variable
		int roomNum = 0;// create room number variable
		Scanner sc = new Scanner(System.in);
		System.out.println("Select option: ");// Option select menu
		System.out.println("E: Display Empty rooms");
		System.out.println("D: Delete customer from room");
		System.out.println("F: Find room from customer name");
		System.out
				.println("S: Store program array data into a plain text file");
		System.out
				.println("L: Load program data back from the file into the array");
		System.out.println("O: View rooms Ordered alphabetically by name.");
		System.out.println("A: Add a person to room.");
		System.out.println("V: Veiw room details.");
		System.out.println("Q : Exit program");
		String input = sc.next();// get input from user
		if ((input.equals("Q")) || (input.equals("q"))) {
			System.exit(0);// quit
		} else if ((input.equals("E")) || (input.equals("e"))) {
			displayEmpty(hotel);// display empty rooms
		} else if ((input.equals("D")) || (input.equals("d"))) {
			deleteCustomer(hotel);// delete customer
		} else if ((input.equals("F")) || (input.equals("f"))) {
			findRoom(hotel);// find customer
		} else if ((input.equals("S")) || (input.equals("s"))) {
			storeArray(hotel);// store array in file
		} else if ((input.equals("L")) || (input.equals("l"))) {
			loadArray(hotel);// load to array from file
		} else if ((input.equals("O")) || (input.equals("o"))) {
			sortArray(hotel);// sort array
			veiwRooms(hotel);// view array
		} else if ((input.equals("A")) || (input.equals("a"))) {
			addCustomer(hotel, roomNum, roomName);// add customer
		} else if ((input.equals("V")) || (input.equals("v"))) {
			veiwRooms(hotel);// view array (unsorted)
		} else {
			System.err.println("Please enter only character from above list");
		}// ensure that invalid input isn't entered

	}

	private static void initialise(String hotel[]) {// initialise array
		for (int x = 0; x < hotel.length; x++)
			hotel[x] = "e";
		
	}

	private static void findRoom(String[] hotel) {// search array
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out
					.println("Press Q to exit program and W to go back to menu");
			System.out.println("Enter name of customer that needs to be found");
			String name = sc.nextLine();// get input
			boolean alpha = isAlpha(name);// validate
			if (alpha != true) {// if validation failed
				System.err.println("Enter only letters");
				findRoom(hotel);// method calls itself and loops
			}
			if (name.equalsIgnoreCase("q")) {
				System.exit(0);// exit
			} else if (name.equalsIgnoreCase("w")) {
				try {
					runProgram(hotel);// goes back to main program body
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			boolean found = false;// search flag
			for (int i = 1; i < hotel.length; i++) {
				if (hotel[i].equalsIgnoreCase(name)) {// search
					System.out.println("Room number of " + name + " is " + i);
					found = true;// if found
					break;// exit loop
				}
			}
			if (found == false) {// if not found
				System.out
						.println("Such a customer does not exist. Please enter valid name");
			}
		}
	}

	private static void veiwRooms(String[] hotel) {
		for (int x = 1; x < hotel.length; x++) {// loop and display
			System.out.println("room " + x + " occupied by " + hotel[x]);
		}
		try {
			runProgram(hotel);// go back to main body
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addCustomer(String[] hotel, int roomNum, String roomName) {
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
				hotel[roomNum] = roomName;// add to array
			} catch (InputMismatchException e) {// exception catch
				System.err
						.println("Please do not enter anything outside of numbers in valid range");
				addCustomer(hotel, roomNum, roomName);

			}
		}
	}

	private static void deleteCustomer(String[] hotel) {// delete customer
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

				hotel[num] = "e";// delete record
			} catch (InputMismatchException e) {// exception handling
				System.err
						.println("Please do not enter anything outside of numbers in valid range");
				deleteCustomer(hotel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void displayEmpty(String[] hotel) throws IOException {
		// display empty rooms
		for (int x = 1; x < hotel.length; x++) {
			if (hotel[x].equals("e")) {// check if empty
				System.out.println("room " + x + " is empty");
			}
		}
		runProgram(hotel);
	}

	private static void storeArray(String[] hotel) throws IOException {// store array in text file
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("roomdata.txt"), "utf-8"))) {// create file
			for (int i = 1; i < hotel.length; i++) {
				// write to file
				writer.write("Room " + i + " occupied by |" + hotel[i]);
				((BufferedWriter) writer).newLine();// next line
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

	private static void loadArray(String[] hotel) throws IOException {
		// load from file to array
		Vector lineArray = new Vector();// create vector array
		String lineContents = null;
		String[] temp = new String[10];
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
			hotel[i + 1] = parts[1];// load to hotel array
		}
		runProgram(hotel);
	}

	private static void sortArray(String[] hotel) {
		String[] names=new String[11];
		int [] numbers=new int[11];
		String temp = "";
		int tempRoom=0;
		
		for(int x=1;x<11;x++){
			names[x]=hotel[x];
			numbers[x]=x;
		}
		
		for (int i = 1; i < names.length; i++) {
			for (int j = i + 1; j < names.length; j++) {
				if (names[i].compareTo(names[j]) > 0) {
					// compare and swap values to sort array
					temp = names[i];
					tempRoom=i;
					names[i] = names[j];
					numbers[i]=numbers[j];
					names[j] = temp;
					numbers[j]=tempRoom;
				}
			}
		}
		
		for(int x=1;x<11;x++){
			System.out.println("Room number "+numbers[x] + " is occupied by " + names[x]);
		}
		
	}

	public static boolean isAlpha(String name) {
		// Check to ensure string is in alphabetical order
		return name.matches("[a-zA-Z]+");
	}
}
