/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * @author andres
 *
 */
public interface Conflict {
  
  void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
