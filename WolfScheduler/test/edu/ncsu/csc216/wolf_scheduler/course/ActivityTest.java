
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Activity.checkConflict method.
 * @author andres
 *
 */
public class ActivityTest {

  /** Check conflict time overlap. **/
  @Test
  public void testCheckConflict() {
    
    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 
          4, "sesmith5", "MW", 1330, 1445);
    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 
          4, "sesmith5", "TH", 1330, 1445);
    try {
      a1.checkConflict(a2);
      assertEquals("Incorrect meeting string for this Activity.",
            "MW 1:30PM-2:45PM", a1.getMeetingString());
      assertEquals("Incorrect meeting string for possibleConflictingActivity.",
            "TH 1:30PM-2:45PM", a2.getMeetingString());
    } catch (ConflictException e) {
      fail("A ConflictException was thrown when two Activities at the"
            + " same time on completely distinct days were compared.");
    }
      
    //Update a1 with the same meeting days and a start time that overlaps the end time of a2
    a1.setMeetingDays("TH");
    a1.setActivityTime(1445, 1530);
    try {
      a1.checkConflict(a2);
      fail(); //ConflictException should have been thrown, but was not.
    } catch (ConflictException e) {
      //Check that the internal state didn't change during method call.
      assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
    // test same activity 
    a1.setActivityTime(1330, 1445);
    try {
      a1.checkConflict(a2);
      fail(); //ConflictException should have been thrown, but was not.
    } catch (ConflictException e) {
      //Check that the internal state didn't change during method call.
      assertEquals("TH 1:30PM-2:45PM", a1.getMeetingString());
      assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
  }
  
  
  /** Test the conflict of having the same event name. **/
  @Test
  public void testNameConflict() {
    
    Activity a1 = new Event("Lunch", "M", 1130, 1445, 1, "");
    Activity a2 = new Event("Lunch", "TH", 1500, 1600, 1, "");
    
    try {
      a1.checkConflict(a2);
      a2.checkConflict(a1);
      fail(); 
    } catch (ConflictException e) {
      assertEquals("You have already created an event called Lunch", e.getMessage());
     
    }
    
  }
  
  /** Test the conflict of the event starting time. **/
  @Test
  public void testStartTimeConflict() {
    // Test for events with the same starting time 
    Activity a1 = new Event("Lunch", "M", 1100, 1445, 1, "");
    Activity a2 = new Event("Group Chat", "M", 1100, 1600, 1, "");
    
    try {
      a1.checkConflict(a2);
      a2.checkConflict(a1);
      fail(); 
    } catch (ConflictException e) {
      assertEquals("The event is invalid", e.getMessage());
     
    }
    
  }
  
  /** Test the conflict of the event ending time. **/
  @Test
  public void testEndTimeConflict() {
    // Test for events with the same ending time 
    Activity a1 = new Event("Lunch", "M", 1100, 1445, 1, "");
    Activity a2 = new Event("Group Chat", "M", 1000, 1445, 1, "");
    
    try {
      a1.checkConflict(a2);
      a2.checkConflict(a1);
      fail(); 
    } catch (ConflictException e) {
      assertEquals("The event is invalid", e.getMessage());
     
    }
    
  }
  
  /** Test that a course and an event don't occur at the same time. **/
  @Test
  public void testActivityTime() {
   
    
    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
        "sesmith5", "M", 1330, 1445);
    Activity a2 = new Event("Lunch", "M", 1330, 1500, 1, "");
    
    try {
      a1.checkConflict(a2);
      a2.checkConflict(a1);
      fail(); 
    } catch (ConflictException e) {
      assertEquals("The event is invalid", e.getMessage());
     
    }
    
  }
  
  /** 
   * Test that adding a course when an event is already present 
   * throws an exception. 
   **/
  @Test 
  public void testAddingCourse() {
   
    // test ending time of course 
    Activity a1 = new Event("Lunch", "M", 1445, 1500, 1, "");
    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001",
        4, "sesmith5", "M", 1330, 1445);
   
    try {
      a1.checkConflict(a2);
      a2.checkConflict(a1);
      fail(); 
    } catch (ConflictException e) {
      assertEquals("The course cannot be added due to a conflict", e.getMessage());
     
    }
    
    // test start time of course 
    a1.setActivityTime(1200, 1330);
    try {
      a1.checkConflict(a2);
      a2.checkConflict(a1);
      fail(); 
    } catch (ConflictException e) {
      assertEquals("The course cannot be added due to a conflict", e.getMessage());
     
    }
    
  }
    
  
  

  
  
  
  
  
  


}
