package edu.ncsu.csc216.wolf_scheduler.course;

/** Course class. 
 * @author andres
 * */
public class Course extends Activity {

  /** constant values for the class section length. **/ 
  private static final int SECTION_LENGTH = 3; 
  /** minimum name length. **/
  private static final int MAX_NAME_LENGTH = 6; 
  /** minimum name length. **/
  private static final int MIN_NAME_LENGTH = 4; 
  /** Maximum credits allowed for a class. **/
  private static final int MAX_CREDITS = 5; 
  /** minimum credits allowed for a class. **/
  private static final int MIN_CREDITS = 1; 
  /** Course's name. */
  private String name;
  /** Course's section. */
  private String section;

  /** Course's credit hours. */
  private int credits;

  /** Course's instructor. */
  private String instructorId;

  /** 
   * Set name method.
   * @return returns the Course's name.
   */
  public String getName() {
    return name; 
  }
  
  
  /** Sets the course's name.
  * If the name is null, is less than 4 or greater than 6, an 
  * illegalArgumentException is thrown.
  **/
  private void setName(final String name) {
    if (name == null) {
      throw new IllegalArgumentException(); 
    } 
    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException(); 
    }
    this.name = name;
  }

  /** sets the course's section.
   * @param section course section 
   * */
  public final void setSection(final String section) {
    if (section == null || section.equals("") || section.length() != SECTION_LENGTH) {
      throw new IllegalArgumentException(); 
    }
    this.section = section;
  }

  
  /** sets the course's credits.
   * @param credits course credits. 
   * */
  public void setCredits(final int credits) {
    if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
      throw new IllegalArgumentException(); 
    }
    this.credits = credits;
  }
  
  
  /** sets the course's instructors id. 
   * @param instructorId the instructor's id.
   * */
  public final void setInstructorId(final String instructorId) {
    if (instructorId == null || instructorId.equals("")) {
      throw new IllegalArgumentException(); 
    }
    this.instructorId = instructorId;
  }
  
  /** gets the course's instructors id. 
   * @return returns the instructors id. 
   * */ 
  public String getInstructorId() {
    return instructorId;
  }
  
  /** returns the course's credits. 
   * @return returns the course credits 
   * */ 
  public int getCredits() { 
    return credits;
  }
  
  /** returns the course's section. 
   * @return returns the course section
   * */ 
  public String getSection() {
    return section;
  }
  
  /**
   * This is the main constructor 
   * Creates a Course object with the given name, title, section, 
   * credits, instructorId, and meetingDays for 
   * courses that are arranged.
   * @param name course name
   * @param title course title
   * @param section course section
   * @param credits course credits
   * @param instructorId course instructorId
   * @param meetingDays course meeting days 
   * @param startTime course starting time 
   * @param endTime course ending time
   */
  public Course(String name, String title, String section, 
      int credits, String instructorId, String meetingDays,
      int startTime, int endTime) {
    
    super(title, meetingDays, startTime, endTime);
    setName(name); 
    setSection(section);
    setCredits(credits);
    setInstructorId(instructorId);
    
   
   

  }
  
  /** creates a Course object that accepts 6 parameters.
   * @param name course name
   * @param title course title
   * @param section course section
   * @param credits course credits
   * @param instructorId course instructorId
   * @param meetingDays course meeting days 
   * */  
  public Course(String name, String title, String section, int credits, 
      String instructorId, String meetingDays) {
    this(name, title, section, credits, instructorId, meetingDays, 0, 0); // calls main constructor 
  }
 

  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + credits;
    result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((section == null) ? 0 : section.hashCode());
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
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Course other = (Course) obj;
    if (credits != other.credits) {
      return false;
    }
    if (instructorId == null) {
      if (other.instructorId != null) {
        return false;
      }
    } else if (!instructorId.equals(other.instructorId)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (section == null) {
      if (other.section != null) {
        return false;
      }
    } else if (!section.equals(other.section)) {
      return false;
    }
    return true;
  }


  /* Override toString method.
   * @see java.lang.Object#toString()
   */ 
  @Override
  public String toString() {
    
    if (this.getMeetingDays().equals("A")) { 
      return this.getName() + "," + this.getTitle() + ","
        + this.getSection() + "," + this.getCredits()
        + "," + this.getInstructorId() + "," + this.getMeetingDays();
    } else {
      return this.getName() + "," + this.getTitle() 
        + "," + this.getSection() + "," + this.getCredits()
        + "," + this.getInstructorId() + "," + this.getMeetingDays() + "," + this.getStartTime()
        + "," + this.getEndTime();
    
    }
   
  }


  @Override
  /** Returns a 1D array getting the information
   *  up to 4 elements.
   **/
  public String[] getShortDisplayArray() {
    String[] shortDisplay = new String[4];
    shortDisplay[0] = getName(); 
    shortDisplay[1] = getSection(); 
    shortDisplay[2] = getTitle(); 
    shortDisplay[3] = getMeetingString(); 
    
    return shortDisplay;
  }


  @Override
  /** Returns a 1D array getting the information
   *  up to 7 elements.
   *  **/
  public String[] getLongDisplayArray() {
    
    String[] longDisplay = new String[7]; 
    longDisplay[0] = getName(); 
    longDisplay[1] = getSection(); 
    longDisplay[2] = getTitle(); 
    longDisplay[3] = Integer.toString(getCredits()); 
    longDisplay[4] = getInstructorId(); 
    longDisplay[5] = getMeetingString(); 
    longDisplay[6] = ""; 
                
    return longDisplay;
  }
  
  
  
  @Override
  /** Sets the meeting days.**/
  public void setMeetingDays(String meetingDays) {
    if (meetingDays == null || meetingDays.matches(".*[BCDEGIJKLNOPQRSUVXYZ0].*") 
        || meetingDays.contains("A") && meetingDays.length() != 1 || getStartTime() == 1) {
      throw new IllegalArgumentException(); 
    }
    super.setMeetingDays(meetingDays);
  }


  @Override
  /** Makes sure the activity object is a Course object. **/
  public boolean isDuplicate(Activity activity) {
    /** first check to see if it is an instance of Course **/
    if (activity instanceof Course) {
      // if it is cast it to a course object
      Course courseCasted = (Course) activity; 
      
      /** return true if a duplicate course is found **/
      if (this.getName().equals(courseCasted.getName())) {
        return true; 
      }
      
    }
    
    return false; 
  } 


} // end Course class 



















