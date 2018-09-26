
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Conflict interface.
 * @author andres
 *
 */
public interface Conflict {
  /** Conflict method that will be implemented.
   * @param possibleConflictingActivity activity that creates a conflict. 
   **/
  void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
