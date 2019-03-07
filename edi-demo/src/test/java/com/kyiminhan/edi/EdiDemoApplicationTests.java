package com.kyiminhan.edi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kyiminhan.edi.bean.Order;
import com.kyiminhan.edi.bean.Status;
import com.kyiminhan.edi.utils.XMLToJavaConverter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EdiDemoApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Test
	public void whenConvert_thenPOJOsConstructedCorrectly() throws Exception {
		Order order = XMLToJavaConverter.getInstance().converOrderXMLToOrderObject("/order.xml");
		assertThat(order.getNumber(), is(771L));
		assertThat(order.getStatus(), is(Status.IN_PROGRESS));
		assertThat(order.getCreationDate(), is(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-14")));
	}
}
