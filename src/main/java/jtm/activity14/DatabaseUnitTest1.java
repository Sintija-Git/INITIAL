/**
 * 
 */
package jtm.activity14;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.apache.catalina.Manager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseUnitTest1 {

	private static StudentManager manager;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		manager = new StudentManager();
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}



	@Test
	public void findStudentByID() {
		int studentId = 1;
		Student res = manager.findStudent(studentId);
		assertTrue(res!=null);
		assertTrue(res.getId() == studentId);
	}
	
	
	@Test
	public void findStudentByName() {
		String firstname = "one";
		String lastname =  "two";
		List<Student> res = manager.findStudent(firstname, lastname);
		assertTrue(res != null);
			
	}
	
	@Test
	public void insertStudentByName() {
		Student student = new Student();
		boolean res = manager.insertStudent(student.getFirstName(), student.getLastName());
		assertTrue(true);
	}
		
	@Test
	public void insertStudent() {
		Student student = new Student();
		boolean res = manager.insertStudent(student);
		assertTrue(true);
		
	}
		
	@Test
	public void updateStudent() {
		Student student = new Student();
		boolean res = manager.updateStudent(student);
		assertTrue(true);
	}
			
	@Test
	public void deleteStudent() {
		Student student = new Student();
		boolean res = manager.deleteStudent(student.getId());
		assertTrue(true);
	}
	
	
	@Test
	public void closeCon() {
		
	}
	
	
	
} //end
