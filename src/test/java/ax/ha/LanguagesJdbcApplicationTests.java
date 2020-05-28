package ax.ha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ax.ha.it.languages.LanguagesJdbcApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LanguagesJdbcApplication.class)
public class LanguagesJdbcApplicationTests {

	@Test
	public void contextLoads() {
	}

}
