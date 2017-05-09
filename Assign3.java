/**
 * 		Author: 		Linda Crane
 * 		Contributor:	Mohamed Elmekki - 040847947
 * 		Topic: CST 8130 Assignment 3 - Planner
 *		Purpose: To create a planner to which a user can add events, with unique
 *		characteristics. Each event can hold a series of notes. The user can also
 *		manipulate the planner (events and notes) as they see fit.
 * 		Assignment 3 update:
 * 			Added a LinkedList (generic collection class) to Event class.
 * 			Holds list of notes (Strings).
 * 			Added menu option to add notes to existing event.
 * 			Added menu option to display notes for specific event.
 * 			Now reads notes from a text file.
 */

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assign3 {
	public static void main(String[] args) {


		Scanner in = new Scanner (System.in);
		Scanner inFile = null;
		Planner planner = new Planner();
		OurDate displayDate = new OurDate();

		int menuChoice = 1;


		while (menuChoice !=0 ) {
			// loop to enter valid MenuChoice...
			do {

				System.out.print("\nEnter 1 to add an event to planner from keyboard;  ");
				System.out.print("\n2 to display events for a day;  ");
				System.out.print("\n3 to display events for a week;  ");
				System.out.print("\n4 to delete an event; ");
				System.out.print("\n5 to add events to planner from a file;  ");
				System.out.print("\n6 to add a note to an event;  ");
				System.out.print("\n7 to delete a note from an event;  ");
				System.out.print( "\n0 to quit: ");
				if (in. hasNextInt())
					menuChoice = in.nextInt();
				else {
				    in.next();
					System.out.println("Invalid menu choice....reenter: ");
					menuChoice = -1;
				}
			} while (menuChoice < 0 || menuChoice > 7);


			if (menuChoice == 1) {
				planner.inputEvent(in, "yes");
				System.out.println (planner);
			} else if (menuChoice == 2) {
				System.out.println ("Enter a date to display: ");
				if (displayDate.inputDate(in, "yes"))
					planner.displayOneDay(displayDate);
			} else if (menuChoice == 3) {
				System.out.println ("Enter a date to display: ");
				if (displayDate.inputDate(in, "yes"))
					planner.displaySevenDays(displayDate);
			} else if (menuChoice == 4) {
				planner.deleteEvent(in, "yes");
			} else if (menuChoice == 5) {
				inFile = openFile(in);
				if (inFile != null) {
					while (inFile.hasNext())
						planner.inputEvent(inFile, "no");
					System.out.println (planner);
				}
			} else if (menuChoice == 6) {
				planner.addNotesToEvent(in, "yes");
			} else if (menuChoice == 7) {
				planner.deleteNotesFromEvent(in, "yes");
			}

		}


	}

	//********************openFile*********************************
		public static Scanner openFile(Scanner in) {
			String fileName = new String();
			Scanner inFile = null;

			System.out.print("\n\nEnter name of file to process: ");
			fileName = in.next();

			File file = new File(fileName);
			try {
				if (file.exists()) {
					inFile = new Scanner(file);
				}
				else System.out.println ("File does not exist.....");
				return inFile;
			} catch (IOException e) {
				System.out.println("Could not open file...." + fileName + "exiting");
				return null;
			}
		}// end openFile method

}
