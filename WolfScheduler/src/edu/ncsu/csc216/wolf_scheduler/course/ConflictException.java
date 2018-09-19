/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * @author andres
 *
 */
public class ConflictException extends Exception {
  
  
  public ConflictException(String message) {
    // call constructor of parent Exception with custom string message 
    super(message); 
  }
  
  
  public ConflictException() {
    // calls the parameterless constructor 
    this("Schedule conflict"); 
  }



}
