package jtm.activity13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;




public class TeacherManager {
	
	private static Logger log = Logger.getLogger(TeacherManager.class);
	
	protected Connection conn = null;
	private ResultSet rSet = null;
	private PreparedStatement pStatement = null;

	static final String url = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false&characterEncoding=utf8";
	static final String userName = "root";
	static final String password = "Student007";


	
	public TeacherManager() {
		// TODO #1 When new TeacherManager is created, create connection to the
		// database server:
		// url =
		// "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false&characterEncoding=utf8"
		// user = "root"
		// pass = "Student007"
		// Hints:
		// 1. Do not pass database name into url, because some statements
		// for tests need to be executed server-wise, not just database-wise.
		// 2. Set AutoCommit to false and use conn.commit() where necessary in
		// other methods

		if (conn == null) {

			try {

				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, userName, password);
				conn.setAutoCommit(false);

			} catch (Exception e) {	
			log.debug(e.getMessage());

			}
		}
	}

	
	/**
	 * Returns a Teacher instance represented by the specified ID.
	 * 
	 * @param id the ID of teacher
	 * @return a Teacher object
	 */

	public Teacher findTeacher(int id) {
		// TODO #2 Write an sql statement that searches teacher by ID.
		// If teacher is not found return Teacher object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "database_activity.Teacher"

		Teacher teacher = new Teacher(0, null, null);

		try {

			pStatement = conn.prepareStatement("SELECT * FROM database_activity.teacher WHERE id = ?");
			pStatement.setInt(1, id);
			conn.commit();
			rSet = pStatement.executeQuery();

			if (rSet.next()) {
				teacher = new Teacher(rSet.getInt(1), rSet.getString(2), rSet.getString(3));
				return teacher;
			}

		} catch (SQLException s) {
			s.printStackTrace();
		}

		return teacher;

	}

	/**
	 * Returns a list of Teacher object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 * 
	 * @param firstName the first name of teacher.
	 * @param lastName  the last name of teacher.
	 * @return a list of Teacher object.
	 */
	public List<Teacher> findTeacher(String firstName, String lastName) {
		// TODO #3 Write an sql statement that searches teacher by first and
		// last name and returns results as ArrayList<Teacher>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!

		List <Teacher> teachersList = new ArrayList<Teacher>();

		try {

			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(
					"SELECT * FROM database_activity.teacher WHERE firstname LIKE ? AND lastname LIKE ?");
			pStatement.setString(1, "%" + firstName + "%");
			pStatement.setString(2, "%" + lastName + "%");
			conn.commit();
			
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				teachersList.add(new Teacher(rSet.getInt(1),rSet.getString(2), rSet.getString(3)));
			}
			
			rSet.close();
			pStatement.close();		
		} catch (SQLException s) {
			log.debug(s.getMessage());
		}

		return teachersList;
	}

	/**
	 * Insert an new teacher (first name and last name) into the repository.
	 * 
	 * @param firstName the first name of teacher
	 * @param lastName  the last name of teacher
	 * @return true if success, else false.
	 */

	public boolean insertTeacher(String firstName, String lastName) {
		// TODO #4 Write an sql statement that inserts teacher in database.

		try {
			
			pStatement = conn
					.prepareStatement("INSERT INTO database_activity.teacher (firstname, lastname) VALUES (?,?)");
			pStatement.setString(1, firstName);
			pStatement.setString(2, lastName);
			pStatement.executeUpdate();
			conn.commit();	
			
		} catch (SQLException s) {
			log.debug(s.getMessage());
			return false;
		}

		return true;
	}
		

	/**
	 * Insert teacher object into database
	 * 
	 * @param teacher
	 * @return true on success, false on error (e.g. non-unique id)
	 */
	public boolean insertTeacher(Teacher teacher) {
		// TODO #5 Write an sql statement that inserts teacher in database.

		boolean success = false;

		try {
			
			pStatement = conn.prepareStatement(
					"INSERT INTO database_activity.teacher (id, firstname, lastname) VALUES (?, ?, ?)");
			pStatement.setInt(1, teacher.getId());
			pStatement.setString(2, teacher.getFirstName());
			pStatement.setString(3, teacher.getLastName());
			int rows = pStatement.executeUpdate();
			conn.commit();
			
			if (rows ==1 ) {
				success = true;
			}

		} catch (SQLException s) {
			log.debug(s.getMessage());
		}

		return success;
	}
	

	/**
	 * Updates an existing Teacher in the repository with the values represented by
	 * the Teacher object.
	 * 
	 * @param teacher a Teacher object, which contain information for updating.
	 * @return true if row was updated.
	 */
	public boolean updateTeacher(Teacher teacher) {
		// TODO #6 Write an sql statement that updates teacher information.

		boolean status = false;

		try {
	
			pStatement = conn
					.prepareStatement("UPDATE database_activity.teacher SET firstname = ?, lastname = ? WHERE id = ?");
			pStatement.setString(1, teacher.getFirstName());
			pStatement.setString(2, teacher.getLastName());
			pStatement.setInt(3, teacher.getId());
			int rows = pStatement.executeUpdate();
			conn.commit();
			pStatement.close();	
			
			if (rows == 1) {
				status = true;
			}

		} catch (SQLException s) {
			log.debug(s.getMessage());
		}

		return status;

	}

	/**
	 * Delete an existing Teacher in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of teacher.
	 * @return true if row was deleted.
	 */
	public boolean deleteTeacher(int id) {
		// TODO #7 Write an sql statement that deletes teacher from database.
		
		boolean deleted = false;

		try {
			
			pStatement = conn.prepareStatement("DELETE FROM database_activity.teacher WHERE id = ?");
			pStatement.setInt(1, id);
			
			int x = pStatement.executeUpdate();
			conn.commit();
			pStatement.close();
			
			if (x ==1) {	
				deleted = true;
			}

		} catch (SQLException s) {
			log.debug(s.getMessage());
		}

		return deleted;

	}

	public void closeConnecion() {
		// TODO Close connection to the database server and reset conn object to null
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
	}
}
