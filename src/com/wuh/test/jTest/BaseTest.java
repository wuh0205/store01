package com.wuh.test.jTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationcontext-jdbc.xml",
		"classpath:applicationcontext-beans.xml",
		"classpath:springMVC-servlet.xml","classpath:applicationcontext-cache.xml"})
public class BaseTest {

}
