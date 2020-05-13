package jtm.activity15;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*-
 * This Junit Test suite runs two test cases:
 * 1. ColorSliderGUITes, which has source code provided to you in the package
 * 2. ColorSliderJarTest, which is compiled into JTM.jar library
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = { ColorSliderGUITest.class, ColorSliderJarTest.class })
public class ColorSliderTestSuite {

	ColorSliderTestSuite() {
	}

}
