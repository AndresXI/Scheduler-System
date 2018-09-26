
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Conflict interface.
 * @author andres
 *
 */
public interface Conflict {
  /** Conflict method that will be implemented. **/
  void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
