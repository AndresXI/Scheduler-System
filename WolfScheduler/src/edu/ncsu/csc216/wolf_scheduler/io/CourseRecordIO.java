
package edu.ncsu.csc216.wolf_scheduler.io;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author andres
 *
 */
public class CourseRecordIO {
  
  /**
 * Reads course records from a file and generates a list of valid Courses.  Any invalid
 * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
 * a File NotFoundException is thrown.
 * @param fileName file to read Course records from
 * @return a list of valid Courses
 * @throws FileNotFoundException if the file cannot be found or read
 */
  public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException  {
    
    Scanner fileReader = new Scanner(new FileInputStream(fileName)); 
    ArrayList<Course> courses = new ArrayList<Course>(); // creates a new array of Course objects 
    
    while (fileReader.hasNextLine()) {
      try {
        Course course = readCourse(fileReader.nextLine());
        boolean duplicate = false;
        for (int i = 0; i < courses.size(); i++) {
          Course c = courses.get(i); // get the first course 
          if (course.getName().equals(c.getName()) 
              && course.getSection().equals(c.getSection())) {
            //it's a duplicate
            duplicate = true;
          }
        } 
        
        if (!duplicate) {
          courses.add(course); // add course if not duplicate 
            
        }
      } catch (IllegalArgumentException e) {
          // if duplicate course than skip the line 
      }
    }
    
    fileReader.close();
    return courses;
  }

  
  /** helper method. **/
  private static Course readCourse(final String course) {
    final Scanner fileReader = new Scanner(course);
    fileReader.useDelimiter(",");

    String name = null; 
    String title = null; 
    String section = null; 
    Integer credits = 0; 
    String instructorId = null; 
    String meetingDays = null; 
    Integer startTime = 0; 
    Integer endTime = 0; 
  
    try {
      while (fileReader.hasNext()) {
        name = fileReader.next(); 
        title = fileReader.next(); 
        section = fileReader.next(); 
        credits = Integer.parseInt(fileReader.next()); 
        instructorId = fileReader.next(); 
        meetingDays = fileReader.next();
        startTime = Integer.parseInt(fileReader.next()); 
        endTime = Integer.parseInt(fileReader.next()); 
       
        if (meetingDays.contains("A")) {
          fileReader.close();
          throw new IllegalArgumentException(); 
        } 
      }

    } catch (NoSuchElementException e) {
      // catch exceptions 
    }
    final Course courseObj = new Course(name, title, 
          section, credits, instructorId, meetingDays, startTime, endTime);
    fileReader.close();
    return courseObj; 
  }

}
