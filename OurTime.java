/**   This class models a time.
 *	  Author:  Linda Crane
 *	  Data fields:  hour: int - values of 0-23 for 24 hour clock 
 *                  minute: int - values 0-59 
 *    Methods:  default constructor
 *              initial constructors
 *				getHour: int - return hour value
 *              getMinute: int - returns minute value
 *				setHour (int) - sets hour to parameter value
 *				setMinute (int) - sets minute to parameter value
 *				isEqual (Time): boolean - returns whether current time object has same time as parameter
 *          	isGreater (OurTime):boolean - compares two OurTime objects and returns true if current object (this) is 
 *                                            greater than the parameter object; else returns false
 *				toString:String - displays values of time to String
 *				inputTime(Scanner, String) - prompts user (if String parameter has first char of 'y') 
 *                                       to enter values for data fields from Scanner parameter
 */
import java.util.*;

public class OurTime {
	private int hour;
	private int minute;
	
	public OurTime() {
	}
	public OurTime (int hour, int minute){
		this.setHour(hour);
		this.setMinute(minute);
	}
	public OurTime(OurTime time){
		this.hour = time.hour;
		this.minute = time.minute;
	}
	
	// get methods
	public int getHour() { return hour; }
	public int getMinute() { return minute; }
	
	// set methods
	public void setHour (int hour){
		if (hour < 0 || hour > 23)
			this.hour = 0;
		else this.hour = hour;
	}
	public void setMinute (int minute){
		if (minute < 0 || minute > 59)
			this.minute = 0;
		else this.minute = minute;
	}
	
	// accessors and mutators
	public String toString () { return new String ("" + hour + ":" + minute); }
	
	public boolean inputTime(Scanner in, String prompt) {
		hour = -1;
		minute = -1;
		do {
			if (prompt.charAt(0) == 'y')
				System.out.print ("\nEnter hour (0-23): ");
		    if (in.hasNextInt())
		    	this.hour = in.nextInt();
		    else {
		    	System.out.println ("Invalid hour input");
		    	in.next();
				if (prompt.charAt(0) != 'y')
					return false;
		    }
		} while (this.hour< 0 || this.hour > 23);
		
		
		do {
			if (prompt.charAt(0) == 'y')
				System.out.print ("\nEnter minute (0-59): ");
		    if (in.hasNextInt())
		    	this.minute = in.nextInt();
		    else {
		    	System.out.println ("Invalid minute input");
		    	in.next();
				if (prompt.charAt(0) != 'y')
					return false;
		    }
		} while (this.minute< 0 || this.minute > 59);
		return true;
	}
	
	public boolean isEqual (OurTime time){
		if (this.hour == time.hour && this.minute == time.minute) return true;
		else return false;
	}
	public boolean isGreater (OurTime rhs) {
		if (this.hour > rhs.hour) return true;
		if (this.hour < rhs.hour) return false;
		if (this.minute > rhs.minute) return true;
		return false;		
	}
}
