package com.megagao.production.ssm.test;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

public class propTest {
@Test
public void propTest() {

	     //config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可  
	     ResourceBundle resource = ResourceBundle.getBundle("jdbc");
	     String key = resource.getString("severurl"); 
	     System.out.println("url="+key);
}
}
