package com.megagao.production.ssm.test;

import org.junit.Test;

import com.megagao.production.ssm.util.QRCode;

public class qcodeTest {
	@Test
	public void name() {
		QRCode.encoderQRCode("https://www.baidu.com", "C:\\Users\\Jzh\\Desktop\\poi\\1a.png", "png", 10);
		
	}
}
