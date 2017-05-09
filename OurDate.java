/**   This class models a date.
 *	  Author:  Linda Crane
 *    Assignment 2 update - added isGreater method
 *    
 *	  Data fields:  day: int - values of 1-31 
 *                  month: int - values 1-12 for January - December
 *                  year: int - value for year
 *    Methods:  default constructor
 *              initial constructors
 *				getDay: int - return day value
 *              getMonth: int - returns month value
 *              getYear: int - returns year value
 *				setDay (int) - sets day to parameter value
 *				setMonth (int) - sets month to parameter value
 *				setYear (int) - sets year to parameter value
 *				isEqual (OurDate): boolean - returns whether current date object has same date as parameter
 *          	isGreater (OurDate):boolean - compares two OurDate objects and returns true if current object (this) is 
 *                                            greater than the parameter object; else returns false
 *				toString:String - displays values of date to String
 *				inputDate (Scanner, String) - prompts user (if String parameter starts with 'y') to enter values 
 *                                            for data fields from Scanner parameter
 *				addOne - adds one day to the current date object
 */
import java.util.*;

public class OurDate {
	private int day = 1;
	private int month = 1;
	private int year = 2017;
	
	public OurDate() {
	}
	
	public OurDate (int day, int month, int year){
		setMonth (month);
		setYear (year);
		setDay(day);
	}
	
	public OurDate (OurDate date){
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
	}
	
	// get methods
	public int getDay () { return day; }
	public int getMonth() { return month;}
	public int getYear() { return year; }
	
	// set methods
	public void setDay (int day){
		if (day <= 0 || day > 31) 
			this.day = 1;
		else if (this.month == 2 && day > 29) 
			this.day = 1;
		else if (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) )
			this.day = 1;
		else this.day = day;
	}
	public void setMonth (int month){
		if (month < 0 || month > 12 )
			this.month = 1;
		else this.month = month;
	}
	public void setYear (int year){
		if (year < 0)
			this.year = 2010;
		else this.year = year;
	}
	
	// accessors and mutators
	public boolean isEqual (OurDate date){
		if (this.year == date.year && this.month == date.month && this.day == date.day)
			return true;
		else return false;
	}
	public boolean isGreater (OurDate rhs) {
		if (this.year > rhs.year) return true;
		if (this.year < rhs.year) return false;
		if (this.month > rhs.month) return true;
		if (this.month < rhs.month)return false;
		if (this.day > rhs.day) return true;
		return false;		
	}
	public String toString() {   return new String ("" + year + "/" + month + "/" + day);
	}
	
	public boolean inputDate(Scanner in, String prompt) {
		month = 0;
		day = 0; 
		year = 0;
		do {
			if (prompt.charAt(0) == 'y')	
				System.out.print ("\nEnter month - between 1 and 12: ");
			if (in.hasNextInt())
				this.month = in.nextInt();
			else {
				System.out.println ("Invalid month input");
				in.next();
				if (prompt.charAt(0) != 'y')
					return false;
			}
		} while (this.month <= 0 || this.month > 12);
		
		do {
			if (prompt.charAt(0) == 'y')
				System.out.print ("Enter day - between 1 and 31: ");
			if (in.hasNextInt())
				this.day = in.nextInt();
			else {
				System.out.println ("Invalid day input");
				in.next();
				if (prompt.charAt(0) != 'y')
					return false;
			}
		} while (this.day <= 0 || this.day > 31 || (this.month == 2 && this.day > 29) || (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ) );
		
		do {
			if (prompt.charAt(0) == 'y')
				System.out.print ("Enter year: ");
			if (in.hasNextInt())
				this.year = in.nextInt();
			else {
				System.out.println ("Invalid day input");
				in.next();
				if (prompt.charAt(0) != 'y')
					return false;
			}
		} while (this.year <= 0);
			
		return true;		
	}
	
	public void addOne (){
		this.day++;
		if (this.day > 31 || (this.month == 2 && this.day > 29) || (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ) ){ 
			this.day = 1;
			this.month ++;
			if (this.month > 12) {
				this.month = 1;
				this.year++;
			}
		}
	}

}
