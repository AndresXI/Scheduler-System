
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Conflict Exception implementation.
 * @author andres
 *
 */
public class ConflictException extends Exception {
  
  
  /** call constructor of parent Exception with custom string message. 
   * @param message message to be trown to the user.
   **/
  public ConflictException(String message) {
    super(message); 
  }
  
  /** calls the parameterless constructor. **/ 
  public ConflictException() {
    
    this("Schedule conflict."); 
  }


}
