package com.megagao.production.ssm.service.impl;

import java.util.List;

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.mapper.DeviceTypeMapper;
import com.megagao.production.ssm.service.DeviceTypeService;
import com.megagao.production.ssm.domain.Condition;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.DeviceTypeExample;
import com.megagao.production.ssm.domain.Summary;
import com.megagao.production.ssm.domain.DeviceTypeExample.Criteria;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

	@Autowired
    DeviceTypeMapper deviceTypeMapper;
	
	@Override
	public EUDataGridResult getList(int page, int rows, DeviceType deviceType) throws Exception {
		//分页处理
		PageHelper.startPage(page, rows);
		List<DeviceType> list = deviceTypeMapper.find(deviceType);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public EUDataGridResult findList(int page, int rows, Condition condition) throws Exception {
		//分页处理
		PageHelper.startPage(page, rows);
		List<DeviceType> list = deviceTypeMapper.selectByCondition(condition);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public EUDataGridResult findListDay(int page, int rows, Condition condition) throws Exception {
		//分页处理
		PageHelper.startPage(page, rows);
		List<DeviceType> list = deviceTypeMapper.selectByConditionDay(condition);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
/*	@Override
	public List<Summary> find(Condition condition) throws Exception {
		List<Summary> list = deviceTypeMapper.selectByCondition(condition);
		return list;
	}*/
	
	@Override
	public List<DeviceType> find() throws Exception {
		DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
		Criteria criteria = deviceTypeExample.createCriteria();
		List<DeviceType> list = deviceTypeMapper.selectByExample(deviceTypeExample);
		return list;
	}
	
	@Override
	public List<DeviceType> findbyDate() throws Exception {
		DeviceTypeExample deviceTypeExample = new DeviceTypeExample();
		Criteria criteria = deviceTypeExample.createCriteria();
		List<DeviceType> list = deviceTypeMapper.selectByExample(deviceTypeExample);
		return list;
	}

	@Override
	public DeviceType get(String id) throws Exception {
		return deviceTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public CustomResult insert(DeviceType deviceType) throws Exception {
		int i = deviceTypeMapper.insert(deviceType);
		if(i>=0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}
	@Override
	public CustomResult insertDate(DeviceType deviceType) throws Exception {
		int i = deviceTypeMapper.insertDate(deviceType);
		if(i>=0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}
	@Override
	public CustomResult deleteBatch(String[] deviceTypeIds) throws Exception {
		int i = deviceTypeMapper.deleteBatch(deviceTypeIds);
		if(i>=0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	@Override
	public CustomResult update(DeviceType deviceType) throws Exception {
		int i = deviceTypeMapper.updateByPrimaryKeySelective(deviceType);
		if(i>=0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改设备种类信息失败");
		}
	}
	
	@Override
	public CustomResult updateAll(DeviceType deviceType) throws Exception {
		int i = deviceTypeMapper.updateByPrimaryKey(deviceType);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改设备种类信息失败");
		}
	}

	@Override
	public EUDataGridResult searchDeviceTypeByDeviceTypeId(Integer page,
			Integer rows, String deviceTypeId) {
		//分页处理
		PageHelper.startPage(page, rows);
		List<DeviceType> list = deviceTypeMapper.searchDeviceTypeByDeviceTypeId(deviceTypeId);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public EUDataGridResult searchDeviceTypeByDeviceTypeName(Integer page,
			Integer rows, String deviceTypeName) {
		//分页处理
		PageHelper.startPage(page, rows);
		List<DeviceType> list = deviceTypeMapper.searchDeviceTypeByDeviceTypeName(deviceTypeName);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public List<Summary> find(Condition condition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}