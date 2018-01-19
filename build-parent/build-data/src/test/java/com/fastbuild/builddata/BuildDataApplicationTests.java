package com.fastbuild.builddata;

import com.fastbuild.builddata.entity.TestTab;
import com.fastbuild.builddata.service.TestTabService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildDataApplicationTests {

	@Autowired
	private TestTabService testTabService;

	@Test
	public void contextLoads() {
		TestTab tab = testTabService.selectById("1");
		Assert.assertNotNull(tab);
	}

}
