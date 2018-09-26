
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Conflict Exception test class.
 * @author andres
 *
 */
public class ConflictExceptionTest {

  /**
   * Test method for ConflictException class. 
   */
  @Test
  public void testConflictExceptionString() {
    ConflictException ce = new ConflictException("Custom exception message");
    assertEquals("Custom exception message", ce.getMessage());
  }

  /**
   * Test method.
   */
  @Test
  public void testConflictException() {
    ConflictException ce = new ConflictException();
    assertEquals("Schedule conflict", ce.getMessage());
  }

}
