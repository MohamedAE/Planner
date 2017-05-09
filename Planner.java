import java.util.Scanner;
import java.util.ArrayList;

/**   This class models an planner that will keep track of events.
 *	  Author:  Linda Crane
 *    Assignment 3 update - Added methods for adding to/deleting from event linked list.
 *
 *	  Data fields:   activities - ArrayList of Events
 *    Methods:  default constructor
 *				inputEvent(Scanner)- adds an Event to the activities array if there is room and if there is no other activity for that date and time
 *						parameter indicates what type of Event will be added to the array
 *              binarySearch (Event): int - returns index of where Event parameter is found in activities structure
 *                      otherwise returns -1 (not found)
 *              findIndex (Event): int - returns index of first object greater than parameter in the activities structure
 *				displayOneDay(Date) - looks through array and displays all events for the parameter date
 *				displaySevenDays (Date) - displays events for the seven days starting at parameter date
 *				deleteEvent (Date, Time) - looks through array for an event at parameter date and time, and deletes it (if found)
 *			  **addNotesToEvent (Scanner, String) - adds to link list an element containing note
 *			  **deleteNotesFromEvent (Scanner, string) - searches link for element; removes it if found
 *			  **findEvent(): int - searches arraylist to find matching event; returns index of event, -1 if not found
 */

public class Planner {
	// final private int MAXEVENTS = 1000; no longer needed
	final private int MEETING = 1;
	final private int SCHOOL = 2;
	final private int WORK = 3;
	final private int SOCIAL = 4;

	private  ArrayList<Event> activities = new ArrayList<Event>();

	public Planner () {
	}

	public void inputEvent(Scanner in, String prompt) {

		int eventType = 0;

		while (eventType < MEETING || eventType > SOCIAL ) {
			if (prompt.charAt(0) == 'y') {
				System.out.print ("Enter the event type to add:  ");
			    System.out.print ("\n 1 for a meeting event");
			    System.out.print ("\n 2 for a school event");
			    System.out.print ("\n 3 for a work event");
			    System.out.println ("\n 4 for a social event");
			}
			if (in.hasNextInt()) {
				eventType = in.nextInt();
			} else {
				System.out.println ("Invalid event type....reenter");
				in.next();
			}
		}

		Event temp;
		switch (eventType) {
			case MEETING:  temp= new Meeting(); break;
			case SCHOOL: temp = new School(); break;
			case WORK: temp = new Work(); break;
			case SOCIAL:  temp = new Social();
			default:   temp = new Event();
		}
		if (!temp.inputEvent(in,prompt))
			return;

		//check if there is already an activity at that date and time
		int found = binarySearch (temp);

		if (found != -1) {
			  System.out.println ("You already have an activity for that date and time...cannot be entered");
		}else  {
			found = findIndex(temp);
			activities.add( found, temp);
		} // end else
	}

	public int binarySearch (Event temp) {
		int found = -1;

		int high = activities.size()-1;
		int low = 0;

		while (low <= high) {
			int middle = (high+low)/2;
			if (activities.get(middle).isEqual(temp)) {
					found = middle;
					break;
			}
			else if (activities.get(middle).isGreater(temp))
				high = middle-1;
			else low = middle+1;
		}
		return found;
	}

	public int findIndex(Event temp) {
		if (activities.size() == 0)
				return 0;
		for (int i = 0; i < activities.size(); i++) {
		    if (activities.get(i).isGreater (temp))
		    	return i;
		}
		return activities.size();


	}
	public String toString() {
		String out = new String();
		for (int i=0; i < activities.size(); i++) {
			out += activities.get(i).toString() + "\n";
		}
		return out;
	}

	public void displayOneDay (OurDate displayDate) {
		System.out.println ("Your activities for " + displayDate + " are: ");
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getDate().isEqual(displayDate))
				System.out.println (activities.get(i));
		    if (activities.get(i).getDate().isGreater (displayDate))
		    	break;
		}
	}

	public void displaySevenDays(OurDate displayDate) {
		System.out.println ("Your activities for the week starting " + displayDate + " are: ");
		OurDate lastDate = new OurDate(displayDate);
		for (int j = 0; j < 6; j++) {
			lastDate.addOne();
		}
		System.out.println (lastDate);
		int i=0;
		for(i = 0; i < activities.size(); i++) {
			if (activities.get(i).getDate().isGreater (lastDate))
		    	break;
			if (activities.get(i).getDate().isEqual(displayDate) || activities.get(i).getDate().isGreater (displayDate) )
				System.out.println (activities.get(i));

		}
	}

	public void deleteEvent(Scanner in, String prompt) {
		int index = findEvent(in, prompt);

		if (index != -1){
				//Event to delete has been found - move last event in array to this position
				activities.remove(index);
				System.out.println ("Event deleted");
		} else {
			System.out.println("There is no activity for that date and time...cannot be deleted");
		}
	}

	public void addNotesToEvent(Scanner in, String prompt) {
		if (activities.isEmpty()) {
			System.out.println("Planner is currently empty. Please add events.");
			return;
		}

		int index = findEvent(in, prompt);

		if (index != -1) {
			System.out.println("Enter note to add: ");
			String note = in.next();
			activities.get(index).addNote(note);
		} else {
			System.out.println("Event not found. No notes were added.");
		}
	}

	public void deleteNotesFromEvent(Scanner in, String prompt) {
		if (activities.isEmpty()) {
			System.out.println("Planner is currently empty. Please add events.");
			return;
		}

		int index = findEvent(in, prompt);

		if (index != -1) {
			activities.get(index).deleteNote(in);
		} else {
			System.out.println("Event not found. No notes were deleted.");
		}
	}

	public int findEvent(Scanner in, String prompt) {
		OurDate date = new OurDate();
		OurTime time = new OurTime();
		boolean isOk = false;

		System.out.println("\nEnter date and time of event: ");

		do {
			isOk = date.inputDate(in, prompt);
		} while(!isOk);
		isOk = false;
		do {
			isOk = time.inputTime(in, prompt);
		} while (!isOk);

		Event temp = new Event(date, time, "");
		int found = binarySearch(temp);

		return found;
	}

}












