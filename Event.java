/**   This class models an event.
 *	  Author:  Linda Crane
 *    Assignment 2 update - added isGreater method
 *    Assignment 3 update - add LinkedList
 *
 *	  Data fields:	date:  OurDate - the day/month/year of the event
 *                	time: Time - the hour/minute that event starts at
 *					description: String - a description of event
 *				  **notes: LinkedList - a linked list containing notes as strings
 *
 *    Methods:	default constructor
 *				initial constructors
 *              getDate: OurDate - returns date of event
 *				getTime: Time - returns time of event
 *
 *              toString: String - displays event to a String
 *              inputEvent (Scanner, String) - prompts (if String parameter starts with 'y') input
 *                                             from Scanner parameter  for all data fields
 *              isEqual (Event): boolean - compares date and time in two objects and returns true/false if they are equal
 *          	isGreater (Event): boolean - compares date and time in two objects and returns true if object in class (this)
 *                                           is greater than parameter object; else returns false
 *			  **addNote (String) - adds note containing given data to link list
 *			  **deleteNote (int) - removes note containing given data from link list
 *			  **displayNotes() - prints notes
 */
import java.util.*;

public class Event {
	private OurDate date = new OurDate();
	private OurTime time = new OurTime();
	private String description = new String();
	private LinkedList<String> notes = new LinkedList<String>();

	public Event() {
	}
	public Event (OurDate date, OurTime time, String description){
		this.date = new OurDate(date);
		this.time = new OurTime (time);
		this.description = new String (description);
	}
	public Event (int day, int month, int year, int hour, int minute, String description){
		this.date = new OurDate (day, month, year);
		this.time = new OurTime (hour, minute);
		this.description = new String (description);
	}

	// get methods
	public OurDate getDate() { return date;}
	public OurTime getTime() { return time;}



	// accessor methods and mutators
	public String toString() {
		ListIterator<String> iterator = notes.listIterator();

		String str = "\t" + date + " " + time + " " + description + "\n"
				+ "\t\t[";

		while (iterator.hasNext()) {
			str += iterator.next() + ", ";
		}

		str += "]";

		return str;
	}

	public boolean inputEvent(Scanner in, String prompt) {
		if (!date.inputDate(in, prompt)) {
			return false;
		}
		if (!time.inputTime(in,prompt)) {
			return false;
		}

		if (prompt.charAt(0) == 'y') {
			System.out.print("Enter event description: ");
			this.description = in.next();

			System.out.println("How many notes would you like to add");

			int numNotes = -1;

			do {
				if (in.hasNextInt()) {
					numNotes = in.nextInt();
				} else {
					in.next();
					System.out.println("Invalid choice. Select again: ");
				}
			} while (numNotes < 0);

			if (numNotes > 0) {
				for (int i = 0; i < numNotes; i++) {
					System.out.println("Enter note: ");
					String note = in.next();
					notes.add(note);
				}
			}

		} else if (prompt.charAt(0) == 'n') {
			this.description = in.next();

			int numNotes = 0;
			if (in.hasNextInt()) {
				numNotes = in.nextInt();

				if (numNotes > 0) {
					for (int i = 0; i < numNotes; i++) {
						notes.add(in.next());
					}
				}
			} else {
				return false;
			}

		}

		return true;
	}

	public boolean isEqual (Event rhs) {
		return (this.date.isEqual(rhs.date) && this.time.isEqual(rhs.time));
	}

	public boolean isGreater (Event rhs) {
		if (this.date.isGreater (rhs.date))
			return true;
		else if (this.date.isEqual(rhs.date) && this.time.isGreater (rhs.time))
			return true;
		return false;

	}

	public void addNote(String str) {
		notes.add(str);
	}

	public void deleteNote(Scanner in) {
		if (notes.size() == 0) {
			System.out.println("This event has no notes. Nothing was deleted.");
			return;
		} else {
			displayNotes();
			System.out.println("Enter which note to delete: ");
			int indexToDelete = -1;

			do {
				if (in.hasNextInt()) {
					indexToDelete = in.nextInt();
				} else {
					in.next();
					System.out.println("Invalid index value. Select again: ");
					indexToDelete = -1;
				}
			} while(indexToDelete < 0 || indexToDelete > notes.size() - 1);

			notes.remove(indexToDelete);
			System.out.println("Note deleted.");
		}
	}

	public void displayNotes() {
		ListIterator<String> iterator = notes.listIterator();

		System.out.println("Current notes for this event are: \n");
		int index = 0;

		while (iterator.hasNext()) {
			System.out.println(index + ": " + iterator.next());
			index++;
		}
	}

}































