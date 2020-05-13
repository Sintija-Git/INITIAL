package jtm.activity01;

import org.junit.BeforeClass;

public class ConfigurationTest1 extends ConfigurationTest {

	@BeforeClass
	public static void setUserAndPassword() {
		// It is not encouraged in class, but if you work at home,
		// you can change user and password for automated test
		// here:
		ConfigurationTest.user = "student";
		ConfigurationTest.password = "Student007";
	}

}
