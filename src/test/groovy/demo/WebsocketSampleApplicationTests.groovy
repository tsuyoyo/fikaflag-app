package demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

import tsuyoyo.fikaflag.FikaFlagApplication

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = FikaFlagApplication)
@WebAppConfiguration
class WebsocketSampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
