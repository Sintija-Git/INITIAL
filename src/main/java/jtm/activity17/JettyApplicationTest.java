package jtm.activity17;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jtm.activity13.DatabaseTest;
import jtm.activity13.Teacher;
import jtm.activity13.TeacherManager;

/*-
 * This test is open sourced intentionally. You can use it as a template for
 * automated Web API test in your teamwork project.
 * This tests uses Spring Boot test framework, to test web application.
 * To get more info, how to use it, look at:
 * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JettyApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JettyApplicationTest {

	private static Logger logger = Logger.getLogger(JettyApplicationTest.class);
	private static ResponseEntity<String> entity;
	private static String now;
	private static TeacherManager manager;
	private static int teacherId;
	private static final int timeout = 10; // maximum timeout in seconds

	@Value("${local.server.port}")
	private int port;

	@Rule
	public Timeout globalTimeout = Timeout.seconds(timeout);

	@BeforeClass()
	public static void setUp() {
		// Run web server if not started already
		try {
			URL status = new URL("http://localhost:8080/");
			status.getContent();
		} catch (Exception e) {
			try {
				JettyApplication.main(new String[] { "" });
				Thread.sleep(10); // wait 10 ms
				logger.info("Web application started");
			} catch (Exception e1) {
				Assert.fail(e1.toString());
			}
		}
	}

	@Test()
	public void test1Environment() {
		(new DatabaseTest()).test01SetUp(); // Set up database
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		now = df.format(new Date());
		manager = new TeacherManager();
		try {
			assertEquals("TeacherManager.findTeacher(name, surname) implementation error in package jtm.activity13.",
					"[Mara Ett]", manager.findTeacher("Mara", "Ett").toString());
		} catch (Exception e) {
			logger.error(e);
			Assert.fail(e.getMessage());
		}
		logger.info("OK");
	}

	@Test()
	public void test2WebHome() {
		entity = new TestRestTemplate().getForEntity("http://localhost:" + port, String.class);
		assertEquals("Wrong response status value. Check that web app is running.", HttpStatus.OK,
				entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value.", "" + //
				"<a href='/insertTeacher'>Insert teacher<a><br/>\n" + //
				"<a href='/findTeacher'>Find teacher<a><br/>\n" + //
				"<a href='/deleteTeacher'>Delete teacher<a><br/>\n", entity.getBody());
		logger.info("OK");
	}

	@Test()
	public void test3findTeacherNoParams() {
		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/findTeacher", String.class);
		assertEquals("Wrong response status value.", HttpStatus.OK, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for findTeacher request without parameters.", "" + //
				"<form action=''>\n" + //
				"Name: <input type='text' name='name' value=''><br/>\n" + //
				"Surname:<input type='text' name='surname' value=''><br/>\n" + //
				"<input type='submit' value='Find'><br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());
		logger.info("OK");
	}

	@Test()
	public void test4insertTeacher() {

		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/insertTeacher", String.class);
		assertEquals("Wrong response status value.", HttpStatus.OK, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for findTeacher request without parameters.", "" + //
				"<form action=''>\n" + //
				"Name: <input type='text' name='name' value=''><br/>\n" + //
				"Surname:<input type='text' name='surname' value=''><br/>\n" + //
				"<input type='submit' value='Insert'></form><br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/insertTeacher?name=&surname=",
				String.class);
		assertEquals("Wrong response status value.", HttpStatus.BAD_REQUEST, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for findTeacher request without parameters.", "" + //
				"false<br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/insertTeacher?name=&surname=",
				String.class);
		assertEquals("Wrong response status value.", HttpStatus.BAD_REQUEST, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for findTeacher request without parameters.", "" + //
				"false<br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + port + "/insertTeacher?name=" + now + "&surname=" + now, String.class);
		assertEquals("Wrong response status value.", HttpStatus.OK, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for findTeacher request without parameters.", "" + //
				"true<br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		manager.closeConnecion();

		logger.info("OK");
	}

	@Test()
	public void test5findTeacherWithParams() {
		manager = new TeacherManager();
		entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + port + "/findTeacher?name=" + now + "&surname=" + now, String.class);
		assertEquals("Wrong response status value.", HttpStatus.OK, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		teacherId = getTeacherId(now);
		assertEquals("Wrong response content value for findTeacher request with parameters.", "" + //
				"<form action=''>\n" + //
				"Name: <input type='text' name='name' value=''><br/>\n" + //
				"Surname:<input type='text' name='surname' value=''><br/>\n" + //
				"<input type='submit' value='Find'><br/>\n" + //
				"<table>\n" + //
				"<tr>\n" + //
				"<td>" + teacherId + "</td>\n" + //
				"<td>" + now + "</td>\n" + //
				"<td>" + now + "</td>\n" + //
				"</tr>\n" + //
				"</table><br>\n" + //
				"<a href='/'>Back</a>\n" //
				, entity.getBody());
		logger.info("OK");
	}

	@Test()
	public void test6deleteTeacher() {

		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/deleteTeacher", String.class);
		assertEquals("Wrong response status value.", HttpStatus.OK, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for /deletTeacher request without parameters.", "" + //
				"<form action=''>\n" + //
				"ID:<input type='text' name='id' value=''><br/>\n" + //
				"<input type='submit' value='Delete'><br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/deleteTeacher?id=", String.class);
		assertEquals("Wrong response status value.", HttpStatus.BAD_REQUEST, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for deleteTeacher request with empty parameter.", "" + //
				"false<br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		System.out.println(teacherId);
		entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/deleteTeacher?id=" + teacherId,
				String.class);
		assertEquals("Wrong response status value.", HttpStatus.OK, entity.getStatusCode());
		assertEquals("Wrong content type header value.", "text/html;charset=UTF-8",
				entity.getHeaders().getContentType().toString());
		assertEquals("Wrong response content value for deleteTeacher request with parameter.", "" + //
				"true<br/>\n" + //
				"<a href='/'>Back</a>\n", entity.getBody());

		logger.info("OK");
	}

	private int getTeacherId(String name) {
		int id = 0;
		try {
			List<Teacher> teachers = manager.findTeacher(name, name);
			id = teachers.get(0).getId();
		} catch (Exception e) {
			logger.error(e);
			Assert.fail(e.getMessage());
		}
		return id;
	}

	@AfterClass
	public static void afterClass() {
		try {
			if (manager != null)
				manager.closeConnecion();
		} catch (Exception e) {
			logger.error(e);
			Assert.fail(e.getMessage());
		}

	}
}
