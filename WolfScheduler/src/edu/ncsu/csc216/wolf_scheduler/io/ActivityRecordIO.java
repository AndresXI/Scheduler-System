
package edu.ncsu.csc216.wolf_scheduler.io;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;



/**
 * ActivityRecordIO class.
 * @author andres alcocer
 **/
public class ActivityRecordIO {
  /** Writes the given list of Courses to a file. 
   * @param fileName file name for courses to be recorded. 
   * @param courses array list of courses 
   * @throws IOException throws exception if file cannot be recorded 
   * */ 
  public static void writeActivityRecords(String fileName, 
        ArrayList<Activity> courses) throws IOException {
    
    final PrintStream fileWriter = new PrintStream(new File(fileName));
    
    for (int i = 0; i < courses.size(); i++) {
      fileWriter.println(courses.get(i).toString());
    }
    
    fileWriter.close();
  }
  
}
