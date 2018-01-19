package com.fastbuild;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootArchetypeApplicationTests {

	@Test
	public void contextLoads() {


	}


//	@Test
//	public void testUserListPage() {
//		JSONObject result = httpGet("/user/test");
//		Integer total = result.getInteger("total");
//		Assert.assertTrue(0 != total);
//		Assert.assertNotNull(result.get("records"));
//		System.out.println(result);
//	}

}
