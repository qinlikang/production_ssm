package com.megagao.production.ssm.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.megagao.production.ssm.domain.Condition;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.Summary;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.DeviceTypeService;

public class countTest {
	@Autowired
	DeviceTypeService service;

	@Test
	public void countTest() throws Exception {
		Condition condition=new Condition();
		EUDataGridResult r =  service.findList(1, 30,  condition);
		System.out.println("111111111111"+r.toString());
	}
}
