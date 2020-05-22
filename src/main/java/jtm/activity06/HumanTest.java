package jtm.activity06;
import jtm.TestUtils;
import jtm.testSuite.JTMTest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HumanTest extends JTMTest {
	private Human human;
	private int food = TestUtils.randomInt(20, 0);

	@Test
	public void test01Constructor() {
		this.human = new Human();

		try {
			Assert.assertEquals("Wrong alive status.", "Alive", this.human.isAlive());
			Assert.assertEquals("Wrong weight.", 2L, (long) this.human.getWeight());
			logger.info("OK");
		} catch (Exception var2) {
			TestUtils.handleErrorAndFail(var2);
		}

	}
	@Test
	public void test02KillHimself() {
		this.human = new Human();

		try {
			Assert.assertEquals("Human test error.", "Alive", this.human.isAlive());
			Assert.assertEquals("Human KillHimself error", "Dead", this.human.killHimself());
			Assert.assertEquals("Human KillHimself error", "Dead", this.human.isAlive());
			logger.info("OK");
		} catch (Exception var2) {
			TestUtils.handleErrorAndFail(var2);
		}

	}

	@Test
	public void test03EatAndVomit() {
		this.human = new Human();
		byte expectedWeightb = 2;

		try {
			int expectedWeight = expectedWeightb + this.food;
			this.human.eat(this.food);
			Assert.assertEquals("Wrong human weight after eating food once.", (long) expectedWeight,
					(long) this.human.getWeight());
			Assert.assertEquals("Wrong implementation of .toString() method.",
					"Human: " + (2 + this.food) + " [" + this.food + "]", this.human.toString());

			int i;
			for (i = 0; i < 5; ++i) {
				this.human.eat(this.food);
				Assert.assertEquals("Wrong human weight after eating food several times.", (long) expectedWeight,
						(long) this.human.getWeight());
			}

			expectedWeightb = 2;
			Assert.assertEquals("Wrong vomit amount after vomiting once.", (long) this.food, ((Integer) this.human.vomit()).longValue());
			Assert.assertEquals("Wrong human weight after vomiting once.", (long) expectedWeightb,
					(long) this.human.getWeight());

			for (i = 0; i < 3; ++i) {
				Assert.assertEquals("Wrong vomit amount after vomiting several times.", 0L, ((Integer) this.human.vomit()).longValue());
				Assert.assertEquals("Wrong human weight after vomiting several times.", (long) expectedWeightb,
						(long) this.human.getWeight());
			}

			logger.info("OK");
		} catch (Exception var3) {
			TestUtils.handleErrorAndFail(var3);
		}

	}
	}
