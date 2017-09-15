package cn.skio.cdcservice;

import cn.skio.cdcservice.rabbit.Receiver;
import cn.skio.cdcservice.rabbit.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CDCServiceApplicationTests {

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Test
	public void test() throws Exception {
		sender.sendString("test message");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

	}
}
