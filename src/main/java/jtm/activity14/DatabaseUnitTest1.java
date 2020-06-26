package jtm.activity14;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jtm.TestUtils;

/*
 * !!! Note that this class is not included in Student's workspace !!!
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseUnitTest1 {
	// Standard logger for test journals
	private static Logger logger = Logger.getLogger(DatabaseUnitTest1.class);
	// Necessary structure for the test
	private static StudentManager manager;

	@BeforeClass // create manager which establishes connection
	public static void beforeClass() {
		manager = new StudentManager();
		logger.info("Connection successfully established!");
	}

	@Test
	public void test0InsertStudentAll() {
		Student student = new Student(5, "Just", "Test");
		boolean result = manager.insertStudent(student);
		assertTrue(result);
		logger.debug("OK");
	}

	@Test
	public void test1FindStudent() {
		List<Student> results = manager.findStudent("D", "D");
		assertEquals("Find error.", 1, results.size());
		assertEquals("Find error.", 2, results.get(0).getId());
		assertEquals("Find error.", "DIANA", results.get(0).getFirstName().toUpperCase());
		assertEquals("Find error.", "DOS", results.get(0).getLastName().toUpperCase());
		logger.debug("OK");
	}

	@Test
	public void test2UpdateStudent() {
		Student student = new Student(5, "She Will", "B. Deleted");
		boolean result = manager.updateStudent(student);
		assertTrue(result);
		logger.debug("OK");
	}

	@Test
	public void test3InsertStudent() {
		boolean result = manager.insertStudent("Nana", "Seven");
		assertTrue(result);
		logger.debug("OK");
	}

	@Test
	public void test4DeleteStudent() {
		boolean result = manager.deleteStudent(5);
		assertTrue(result);
		logger.debug("OK");
	}

	@Test
	public void test5DeleteStudentFalse() {
		boolean result = manager.deleteStudent(5000);
		assertFalse(result);
		logger.debug("OK");
	}

	@Test
	public void test5UpdateStudentFalse() {
		Student student = new Student(5000, "She Will", "B. Deleted");
		boolean result = manager.updateStudent(student);
		assertFalse(result);
		logger.debug("OK");
	}

	@Test
	public void test6FindID() {
		Student result = manager.findStudent(1);
		assertEquals("FindID error.", "ANNA", result.getFirstName().toUpperCase());
		assertEquals("FindID error.", "TRESS", result.getLastName().toUpperCase());
		logger.debug("OK");
	}

	@Test
	public void test7Exceptions() {
		try {
			// Create mocked connection which will throw exceptions
			Connection mockedConn = spy(DriverManager
					.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root", "Student007"));
			when(mockedConn.prepareStatement(anyString()))
					.thenThrow(new SQLException("Expected mocked statement exception"));
			doThrow(new SQLException("Mocked mocked connection exception")).when(mockedConn).close();
			// Get exceptions for queries
			manager.conn = mockedConn;
			manager.insertStudent(null, null);
			manager.insertStudent(new Student(0, null, null));
			manager.findStudent(0);
			manager.findStudent(null, null);
			manager.updateStudent(null);
			manager.deleteStudent(0);
			// Get exception for closing connection
			manager.closeConnecion();
		} catch (Throwable t) {
			TestUtils.handleErrorAndFail(t);
		}
	}

	@AfterClass // close connection at the end of tests
	public static void afterClass() {
		manager.closeConnecion();
	}

	public static void main(String[] args) {
		DatabaseUnitTest dbUnitTest = new DatabaseUnitTest();
		dbUnitTest.test();
	}
}




///**
// * 
// */
//package jtm.activity14;
//
//import static org.junit.Assert.*;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.constraints.AssertTrue;
//
//import org.apache.catalina.Manager;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class DatabaseUnitTest1 {
//
//	private static StudentManager manager;
//	
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		manager = new StudentManager();
//	}
//
//	
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//
//
//	@Test
//	public void findStudentByID() {
//		int studentId = 1;
//		Student res = manager.findStudent(studentId);
//		assertTrue(res!=null);
//		assertTrue(res.getId() == studentId);
//	}
//	
//	
//	@Test
//	public void findStudentByName() {
//		String firstname = "one";
//		String lastname =  "two";
//		List<Student> res = manager.findStudent(firstname, lastname);
//		assertTrue(res != null);
//			
//	}
//	
//	@Test
//	public void insertStudentByName() {
//		Student student = new Student();
//		boolean res = manager.insertStudent(student.getFirstName(), student.getLastName());
//		assertTrue(true);
//	}
//		
//	@Test
//	public void insertStudent() {
//		Student student = new Student();
//		boolean res = manager.insertStudent(student);
//		assertTrue(true);
//		
//	}
//		
//	@Test
//	public void updateStudent() {
//		Student student = new Student();
//		boolean res = manager.updateStudent(student);
//		assertTrue(true);
//	}
//			
//	@Test
//	public void deleteStudent() {
//		Student student = new Student();
//		boolean res = manager.deleteStudent(student.getId());
//		assertTrue(true);
//	}
//	
//	
//	@Test
//	public void closeCon() {
//		
//	}
//	
//	
//	
//} //end
