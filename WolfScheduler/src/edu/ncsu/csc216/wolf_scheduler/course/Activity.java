package edu.ncsu.csc216.wolf_scheduler.course;


/**
 * Super class Activity.
 * @author andres
 **/
public abstract class Activity implements Conflict {

  /** Upper Time limit. **/
  private static final int UPPER_TIME = 2400;
  /** Maximum hour allowed.  **/
  private static final int UPPER_HOUR = 60;
  /** Course's title. */
  private String title;
  /** Course's meeting days. */
  private String meetingDays;
  /** Course's starting time. */
  private int startTime;
  /** Course's ending time. */
  private int endTime;
  
  /** Abstract method that returns a short array display.
   * @return returns a short display string array.
   **/
  public abstract String[] getShortDisplayArray();
  
  /** Abstract method that returns a long array display.
   * @return returns a long display string array.
   **/
  public abstract String[] getLongDisplayArray(); 

  /** Constructor for the class Activity. 
   * @param title the title 
   * @param meetingDays the meeting days 
   * @param startTime start time for event
   * @param endTime ending time for event
   *  */
  public Activity(final String title, final String meetingDays, final 
      int startTime, final int endTime) {
    setTitle(title); 
    setMeetingDays(meetingDays); 
    setActivityTime(startTime, endTime);
   
  }

  /** 
   * Sets the course title.
   * @param title sets the course title.
   * */
  public final void setTitle(final String title) {
    if (title == null || title.equals("")) {
      throw new IllegalArgumentException(); 
    }
    this.title = title;
  }
   

  /** sets the course's meeting days.
   * @param meetingDays valid meeting days   
   */
  public void setMeetingDays(String meetingDays) { 
    if (meetingDays == null || meetingDays.equals("")) {
      throw new IllegalArgumentException(); 
    }
    this.meetingDays = meetingDays;
  }

  /** method that sets the course starting time and ending time be in 
   valid military time.
   * @param startTime the staring time 
   * @param endTime the ending time
   */
  public void setActivityTime(int startTime, int endTime) {
    if (startTime == UPPER_TIME || startTime == 1360 || startTime == -1 
        || endTime == UPPER_TIME || endTime == 1360 || endTime == -1 
        || startTime > endTime || startTime % 100 > 59 || endTime % 100 > UPPER_HOUR) {
      throw new IllegalArgumentException(); 
    }
    this.startTime = startTime; 
    this.endTime = endTime; 
  }

  /** returns the course's meeting days. 
   * @return returns the meeting days. 
   * */
  public String getMeetingDays() {
    return meetingDays;
  }

  /** gets the course's starting time. 
   * @return returns the course's starting time 
   * */
  public final int getStartTime() {
    return startTime;
  }

  /** gets the course's ending time. 
   * @return returns the course ending time 
   * */
  public final int getEndTime() {
    return endTime;
  }

  /** Returns the course's title. 
   * @return returns the title 
   * */
  public String getTitle() {
    return title;
  }

  /** get military time in normal time. 
   * @param milTime accepts a military time to convert to normal time
   * @return returns the normal time 
   * */
  public String militaryTimeToNormalTime(int milTime) {
    /*Declare my variables*/
    String timeString = "";
    boolean pmTime;
        
    /*determine AM or PM and convert over 1200 into a clock's digits */
    if (milTime < 1200) {
      pmTime = false;
    } else {
      pmTime = true;
      milTime -= 1200;
    }
  
    /*figure out my digits*/
    final int fourthDigit = milTime % 10;
    milTime = milTime / 10;
    int thirdDigit = milTime % 10;
    milTime = milTime / 10;
    int secondDigit = milTime % 10;
    milTime = milTime / 10;
    int firstDigit = milTime % 10;
  
    /*build each side of the colon*/
    final String hoursString = thirdDigit + "" + fourthDigit;
    String minutesString = firstDigit + "" + secondDigit;
    

    /*determine if the first digit is zero and if so, omit it*/
    if (firstDigit == 0) {
      minutesString = "" + secondDigit;
    }
    if (firstDigit == 0 && secondDigit == 0) {
      minutesString = "12";
    }
  
     
    /*build the total string and return the result with AM or PM based on
     *conditional boolean.*/
    if (pmTime) {
      timeString = (minutesString + ':' + hoursString + "PM");
    }
    if (!pmTime) {
      timeString = (minutesString + ':' + hoursString + "AM");
    }
    
    return timeString; 
  }
  

  

  /** this methods returns the normal time by concatenating the strings. 
   * @return return the meeting string 
   * */
  public String getMeetingString() {
     
    this.startTime = getStartTime(); 
    this.endTime = getEndTime(); 
    
    String normalStartTime = militaryTimeToNormalTime(startTime); 
    String normalEndTime = militaryTimeToNormalTime(endTime); 
    
    if (this.meetingDays.equals("A")) {
      return "Arranged"; 
    } 

    return getMeetingDays() + " " + normalStartTime + "-" + normalEndTime; 
  }
  
  

  /** 
   * Returns the meeting string with weekly repeat.
   * @param weeklyRepeat number of weeks event should be repeated 
   * @return returns the meeting string with weekly repeat data
   **/
  public String getMeetingString(int weeklyRepeat) {
    this.startTime = getStartTime(); 
    this.endTime = getEndTime(); 
    
    
    String normalStartTime = militaryTimeToNormalTime(startTime); 
    String normalEndTime = militaryTimeToNormalTime(endTime); 
    
    if (this.meetingDays.equals("A")) {
      return "Arranged"; 
    }
   
    return getMeetingDays() + " " + normalStartTime + "-" + normalEndTime
        + " (every " + weeklyRepeat + " weeks)"; 
   
  }


  /* 
   * Overrides the hasCode method.
   * @see java.lang.Object#hashCode().
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + endTime;
    result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
    result = prime * result + startTime;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Activity other = (Activity) obj;
    if (endTime != other.endTime) {
      return false;
    }
    if (meetingDays == null) {
      if (other.meetingDays != null) {
        return false;
      }
    } else if (!meetingDays.equals(other.meetingDays)) {
      return false;
    }
    if (startTime != other.startTime) {
      return false;
    }
    if (title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!title.equals(other.title)) {
      return false;
    }
    return true;
  }
  
  /** Methods for validating is an subclass is a duplicate.
   * @param activity activity object Event or Course
   * @return returns true is object is a duplicate
   **/ 
  public abstract boolean isDuplicate(Activity activity);

  /* 
   * Overrides the check conflict method in the conflict class 
   * throws exception for each conflict 
   */
  @Override
  public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
    
    if (this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays())
        && this.getStartTime() == possibleConflictingActivity.getEndTime()) {
     
      throw new ConflictException("The course cannot be added due to a conflict");
    } 
    
    if (this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays())
        && this.getEndTime() == possibleConflictingActivity.getEndTime() && this.getStartTime() 
        == possibleConflictingActivity.getStartTime()) {
     
      throw new ConflictException("The course cannot be added due to a conflict");
    } 
      
    if (this.getTitle().equals(possibleConflictingActivity.getTitle()) 
        && possibleConflictingActivity instanceof Event) {
      throw new ConflictException("You have already created an event called " + this.getTitle()); 
    }
    
    if (this.getEndTime() == possibleConflictingActivity.getEndTime() 
        && possibleConflictingActivity instanceof Event
        && this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays())) {
      throw new ConflictException("The event is invalid"); 
    }
    
    if (this.getStartTime() == possibleConflictingActivity.getStartTime() 
        && possibleConflictingActivity instanceof Event
        && this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays())) {
      throw new ConflictException("The event is invalid"); 
    }
    
    if (this.getEndTime() == possibleConflictingActivity.getStartTime() 
        && this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays())
          && possibleConflictingActivity instanceof Event) {
      throw new ConflictException("The event is invalid due to overlap time conflict"); 
    }
    
    if (this.getMeetingDays().matches(possibleConflictingActivity.getMeetingDays())
        && this.startTime == possibleConflictingActivity.getEndTime()) {
     
      throw new ConflictException("The course cannot be added due to a conflict");
    } 
    
    if (this.getMeetingDays().matches(possibleConflictingActivity.getMeetingDays())
        && this.endTime == possibleConflictingActivity.getStartTime()) {
     
      throw new ConflictException("The course cannot be added due to a conflict");
    } 
    
    if (this.getMeetingDays().matches(".*[" + possibleConflictingActivity.getMeetingDays() 
        + "].*")  
        && this.getEndTime() > possibleConflictingActivity.getStartTime()
        && this.getStartTime() < possibleConflictingActivity.getEndTime()) {
      throw new ConflictException("The event cannot be added due to a conflict."); 
    }
  
    
  } 
  
  
  
    

}