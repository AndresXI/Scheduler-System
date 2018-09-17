
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/** Wolf Scheduler Class.
 * @author andres
 *
 **/
public class WolfScheduler {

  /** an array list of course objects for the schedule field. **/ 
  private ArrayList<Activity> mySchedule; // can contain course and activity objects 
  /** string field for the scheduleTitle field. **/
  private String scheduleTitle;   
  /** an array list of course objects for the courseCatalog field. **/ 
  private ArrayList<Course> myCourseCatalog; 
  
  
  /**
   * Constructor for the wolf scheduler class.
   * @param fileName file name containing the courses 
   */
  public WolfScheduler(String fileName) {
    
    try {
      
      this.myCourseCatalog = CourseRecordIO.readCourseRecords(fileName);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    this.mySchedule = new ArrayList<Activity>(); 
    setTitle("My Schedule"); 
  }
  
  /** PRIVATE HELPER METHODS
   returns the position of the the specified course.  **/
  private int findCourseInCatalog(final String name, final String section) {
    // loop trough all of the courses and extract the name and section 
    // to compare the name and string that was passed. 
    for (int i = 0; i < this.myCourseCatalog.size(); i++) {
      Course course = this.myCourseCatalog.get(i); // gets a single course object in the array list
      if (course.getName().equals(name) && course.getSection().equals(section)) {
        return i; 
      }
    }
    return -1; // return -1 if the course was not found on the Catalog 
  }
  
  
  
  /** METHODS 
   returns the specified course object from the course catalog. 
   *@param name course catalog name
   *@param section course section 
   *@return returns the course from the catalog 
   */
  public Course getCourseFromCatalog(final String name, final String section) {
    
    final int position = findCourseInCatalog(name, section); 
    if (position >= 0) {
      // returns the element (single course) at the specified position
      return this.myCourseCatalog.get(position); 
    }
    
    return null; // the course does not exist in the catalog 
  }
  
 
  /** add course to the schedule. 
   *@param name course catalog name
   *@param section course section 
   *@return returns true if course was added 
   * */ 
  public boolean addCourse(final String name, final String section) {
    Course myC = getCourseFromCatalog(name, section); 
    // don't add the course if it is not on the catalog
    if (myC == null) {
      return false; 
    } 
     
    Activity course = new Course(myC.getName(), myC.getTitle(), myC.getSection(), myC.getCredits(), 
        myC.getInstructorId(), myC.getMeetingDays(), myC.getStartTime(), myC.getEndTime());     
    // use loop to find if the course is a duplicate 
    for (int i = 0; i < mySchedule.size(); i++) {
      // loop through all items in mySchedule and check if there
      // exist a duplicate course 
      if (mySchedule.get(i).isDuplicate(course)) { // if duplicate throw exception 
        throw new IllegalArgumentException("You are already enrolled in " + myC.getName()); 
      } 

    }
    this.mySchedule.add(course);
    return true;
   
  } // end add course 
  

  /** Adds an event object.
   * @param title event title
   * @param meetingDays event meeting days 
   * @param startTime event starting time
   * @param endTime event ending time
   * @param weeklyRepeat event weekly repeat 
   * @param eventDetails the event details 
   **/
  public void addEvent(String title, String meetingDays, 
      int startTime, int endTime, int weeklyRepeat, String eventDetails) {
    
    Activity course = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
    
    for (int i = 0; i < mySchedule.size(); i++) {
      if (mySchedule.get(i).isDuplicate(course)) {
        throw new IllegalArgumentException(
            "You have already created an event called " + course.getTitle()); 
      }
    }
    
    this.mySchedule.add(course); 
    //myEvent.(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails ); 
  }
  

  /** removes a course from the schedule. 
   * @param idx index to be removed from the schedule array list
   *@return returns true if course could be removed 
   * */
  public boolean removeActivity(final int idx) {
   
    if (mySchedule.isEmpty() || idx > mySchedule.size()) {
      return false; 
    }
    mySchedule.remove(idx); 
    return true; 
  }
  
  
  /** Gets the scheduled Courses. 
   * @return returns a 2d array of the scheduled courses 
   * */
  public String[][] getScheduledActivities() {
  
    String[][] scheduleArray = new String[mySchedule.size()][4]; 
    if (scheduleArray.length == 0) {
      return scheduleArray; 
    }
   
    for (int r = 0; r < mySchedule.size(); r++) {
      String[] shortArray = mySchedule.get(r).getShortDisplayArray(); 
      scheduleArray[r][0] = shortArray[0]; 
      scheduleArray[r][1] = shortArray[1]; 
      scheduleArray[r][2] = shortArray[2]; 
      scheduleArray[r][3] = shortArray[3]; 

    }
    
    return scheduleArray;
  }
  
  
  /** Returns a 2d array of the full schedule. 
   * @return returns a 2d array of the scheduled courses with full information. 
   */
  public String[][] getFullScheduledActivities() {
    String[][] scheduleArray = new String[mySchedule.size()][7]; 
    
    if (scheduleArray.length == 0) {
      return scheduleArray; 
    }
    
    for (int r = 0; r < mySchedule.size(); r++) {
      String[] longArray = mySchedule.get(r).getLongDisplayArray(); 
      scheduleArray[r][0] = longArray[0]; 
      scheduleArray[r][1] = longArray[1]; 
      scheduleArray[r][2] = longArray[2]; 
      scheduleArray[r][3] = longArray[3]; 
      scheduleArray[r][4] = longArray[4];
      scheduleArray[r][5] = longArray[5];
      scheduleArray[r][6] = longArray[6];

    }

    
    return scheduleArray;
  }
  
  
  


  /** return 2d array of course catalog. 
   * @return returns a 2d array from the catalog courses 
   * */ 
  public String[][] getCourseCatalog() {
    // returns an empty string array if catalog is empty 
    if (myCourseCatalog.isEmpty()) {
      return new String[0][0]; 
    }
    String[][] courseArray = new String[myCourseCatalog.size()][myCourseCatalog.size()]; 
    for (int r = 0; r < myCourseCatalog.size(); r++) {
      for (int c = 0; c < myCourseCatalog.size(); c++) {
        
        courseArray[c][0] = myCourseCatalog.get(c).getName(); 
        courseArray[c][1] = myCourseCatalog.get(c).getSection(); 
        courseArray[c][2] = myCourseCatalog.get(c).getTitle(); 
        courseArray[c][3] = myCourseCatalog.get(c).getMeetingString(); 
      
      }
    }
    
    return courseArray;
  }
  
  /** write schedule to a file. 
   * @param fileName the schedule name to be exported 
   * */ 
  public void exportSchedule(final String fileName) {
    
    try {
      ActivityRecordIO.writeActivityRecords(fileName, mySchedule);
    } catch (IOException e) {
      throw new IllegalArgumentException("The file cannot be saved"); 
    }
  }
  
  /** returns title.  
   * @return returns the schedule title 
   * */
  public String getTitle() {
    return scheduleTitle; 
  }
  
  /** sets the title.  
   * @param title sets the schedule title.
   * */
  public void setTitle(String title) {
    if (title == null) {
      throw new IllegalArgumentException("Title cannot be null"); 
    }
    this.scheduleTitle = title; 

  }
  
  /** Resets schedule, returns a new empty array. **/
  public void resetSchedule() {
    mySchedule = new ArrayList<Activity>(); 

  }

}
