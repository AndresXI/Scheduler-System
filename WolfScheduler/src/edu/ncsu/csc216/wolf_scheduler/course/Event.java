
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event Class.
 * @author andres
 **/
public class Event extends Activity {
  
  /** Number of weeks event should be repeated. **/
  private int weeklyRepeat; 
  /** The event details. **/
  private String eventDetails;
  
  /**
   * Event constructor.
   * @param title event title
   * @param meetingDays event meeting days
   * @param startTime event start time
   * @param endTime event endTime
   * @param weeklyRepeat number of weeks event should be repeated.
   * @param eventDetails event details
   */
  public Event(final String title, final String meetingDays, final int startTime, final int endTime,
      final int weeklyRepeat, final String eventDetails) {
    super(title, meetingDays, startTime, endTime);
    setWeeklyRepeat(weeklyRepeat); 
    setEventDetails(eventDetails); 
  } 

  /** Sets the event weekly repeat.
   *  @param weeklyRepeat number of weeks event to be repeated.
   **/
  public void setWeeklyRepeat(int weeklyRepeat) {
    
    if (weeklyRepeat < 1 || weeklyRepeat > 4) {
      throw new IllegalArgumentException("Invalid weekly repeat"); 
    } else {
      this.weeklyRepeat = weeklyRepeat; 
    }
    
  } 
  
  /** Sets the event details.
   * @param eventDetails the event details  
   **/
  public void setEventDetails(final String eventDetails) {
    if (eventDetails == null) {
      throw new IllegalArgumentException("Invalid event details"); 
    }
    this.eventDetails = eventDetails; 
  }
  
  /** Gets the weekly repeat number. 
   * @return returns the weekly repeat. 
   **/
  public int getWeeklyRepeat() {
    return weeklyRepeat; 
  }
  
  /** Gets the event details.
   * @return event details 
   **/ 
  public String getEventDetails() {
    return eventDetails; 
  }
  
  /** Gets a short display.  
   * @return short display array
   **/
  public String[] getShortDisplayArray() {
    String[] shortDisplay = new String[4]; 
    
    shortDisplay[0] = ""; 
    shortDisplay[1] = ""; 
    shortDisplay[2] = getTitle(); 
    shortDisplay[3] = getMeetingString(); 
    
    return shortDisplay; 
    
  }
  
  /** 
   * Gets the long display.
   * @return long display array.
   **/
  public String[] getLongDisplayArray() {
    String[] longDisplay = new String[7]; 
    
    longDisplay[0] = ""; 
    longDisplay[1] = ""; 
    longDisplay[2] = getTitle(); 
    longDisplay[3] = ""; 
    longDisplay[4] = ""; 
    longDisplay[5] = getMeetingString(); 
    longDisplay[6] = eventDetails; 
    
    return longDisplay; 
    
  }
  
  
  @Override
  /** Sets the meeting days. **/
  public void setMeetingDays(String meetingDays) {
    if (meetingDays == null || meetingDays.matches(".*[ABCDEGIJKLNOPQRVXYZ].*")) {
      throw new IllegalArgumentException(); 
    }
    super.setMeetingDays(meetingDays);
  }

 
  @Override
  /** 
   * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#getMeetingString().
   * Get the meeting string from the super class.
   * @return meeting string
   **/
  public String getMeetingString() {
    return super.getMeetingString(weeklyRepeat);
  }

  /**
   * Overrides toString method.
   * @see java.lang.Object#toString()
   **/
  @Override
  public String toString() {
    return this.getTitle() + "," + this.getMeetingDays() + "," + this.getStartTime() + "," 
        + this.getEndTime() + "," + this.getWeeklyRepeat() + "," + this.getEventDetails(); 
  }


  @Override
  /** Checks if the activity object is an Event object. **/
  public boolean isDuplicate(Activity activity) {
    // first check to see if it is an instance of Event
    if (activity instanceof Event) {
      // if it is cast it to an Event object
      Event eventCasted = (Event) activity; 
      // return true if a duplicate course is found 
      if (this.getTitle().equals(eventCasted.getTitle())) {
        return true; 
      }
    }
    
    return false; 
  }
  
  
  
} // End event class.
