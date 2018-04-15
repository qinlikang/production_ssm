package com.megagao.production.ssm.controller.device;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.service.DeviceTypeService;
import com.megagao.production.ssm.service.ProductService;
import com.megagao.production.ssm.service.TechnologyService;
import com.megagao.production.ssm.util.ipUtil;
import com.megagao.production.ssm.domain.Condition;
import com.megagao.production.ssm.domain.DeviceType;
import com.megagao.production.ssm.domain.Product;
import com.megagao.production.ssm.domain.Technology;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/count")
public class DeviceTypeController {

	@Autowired
	private DeviceTypeService deviceTypeService;
	
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getListType(Integer page, Integer rows, DeviceType deviceType) throws Exception{
		EUDataGridResult result = deviceTypeService.getList(page, rows, deviceType);
		return result;
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows, Condition condition,HttpServletRequest request) throws Exception{
		String currentUserId = (String) SecurityUtils.getSubject().getSession().getAttribute("activeUser"); 
		 String userid = (String) request.getSession().getAttribute("userid");
		 String rolename = (String) request.getSession().getAttribute("rolename");
		 if("管理员".equals(rolename)){
			 condition.setUserid(userid);
		 }

		if(null==condition.getStartDateString()){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
			 String TimeString = time.format(new Date());
			 condition.setStartDateString(TimeString);
			 
		}
		EUDataGridResult result = deviceTypeService.findList(page, rows, condition);
		return result;
	}
	
	@RequestMapping("/findDay")
	@ResponseBody
	public EUDataGridResult getListDay(Integer page, Integer rows, Condition condition) throws Exception{
		EUDataGridResult result = deviceTypeService.findListDay(page, rows, condition);
		return result;
	}
	//跳转记录次数网址
	@RequestMapping("/get/{orderId}")
	public String getItemById(@PathVariable String orderId,HttpServletRequest request,HttpSession session) throws Exception{
		
		//查询是否启用
		Product product = productService.finda(orderId);
		if(null!=product){
			if(0==product.getStatus())
				return "";
		}
		DeviceType deviceType =new DeviceType();
		deviceType.setDeviceTypeId(orderId);
        Calendar calendar1 = Calendar.getInstance();  
        String ip = ipUtil.getIpAdrress(request);
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),  
                0, 0, 0);  
		deviceType.setDeviceTypeWarranty(calendar1.getTime());
		deviceType.setDeviceTypeName(ip);
		deviceTypeService.insertDate(deviceType);
		String url=(String) session.getAttribute("url");
		if(null==url){
			List<Technology> find = technologyService.find();
			url=find.get(0).getTechnologyName();
			session.setAttribute("url", url);
		}
		return "redirect:"+url;
	}
	
	//跳转记录网址
	@RequestMapping("/post/{orderId}")
	public String forward(@PathVariable String orderId,HttpServletRequest request,HttpSession session) throws Exception{
		
		DeviceType deviceType =new DeviceType();
		deviceType.setDeviceTypeId(orderId);
        Calendar calendar1 = Calendar.getInstance();  
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),  
                0, 0, 0);  
        String ip = ipUtil.getIpAdrress(request);
		deviceType.setDeviceTypeWarranty(calendar1.getTime());
		deviceType.setDeviceTypeName(ip);
		deviceTypeService.insert(deviceType);
		String url=(String) (session.getAttribute("url")!=null?session.getAttribute("url"):"http://baidu.com");
		return "forward:"+url;
	}
	//跳转记录网址
		@RequestMapping("/a")
		public String getItemByIdb( String orderId,HttpSession session) throws Exception{
			
			String url=(String) (session.getAttribute("url")!=null?session.getAttribute("url"):"http://baidu.com");
			
			return "redirect:"+url;
		}
		//跳转记录网址
				@RequestMapping("/b")
				public String getItemById2b( String orderId,HttpSession session) throws Exception{
					
					String url=(String) (session.getAttribute("url")!=null?session.getAttribute("url"):"http://baidu.com");
					
					return "forward:"+url;
				}
	@RequestMapping("/get_data")
	@ResponseBody
	public List<DeviceType> getData() throws Exception{
		List<DeviceType> list = deviceTypeService.find();
		return list;
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "deviceType_add";
	}
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "deviceType_edit";
	}
	
	/*
	 *此处的method可以取两个值，
	 *一个是RequestMethod.GET，一个是RequestMethod.POST，
	 *就是请求该方法使用的模式，是get还是post，即参数提交的方法
	 *ajax或者form表单提交数据有两种方法，即get和post。
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(deviceTypeService.get(deviceType.getDeviceTypeId()) != null){
			result = new CustomResult(0, "该设备种类编号已经存在，请更换设备种类编号！", null);
		}else{
			result = deviceTypeService.insert(deviceType);
		}
		return result;
	}

	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = deviceTypeService.deleteBatch(ids);
		return result;
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return deviceTypeService.update(deviceType);
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return deviceTypeService.updateAll(deviceType);
	}
	
	//根据设备种类编号查找
	@RequestMapping("/search_deviceType_by_deviceTypeId")
	@ResponseBody
	public EUDataGridResult searchDeviceTypeByDeviceTypeId(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = deviceTypeService.searchDeviceTypeByDeviceTypeId(page, rows, searchValue);
		return result;
	}
	
	//根据设备种类名称查找
	@RequestMapping("/search_deviceType_by_deviceTypeName")
	@ResponseBody
	public EUDataGridResult searchDeviceTypeByDeviceTypeName(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = deviceTypeService.searchDeviceTypeByDeviceTypeName(page, rows, searchValue);
		return result;
	}
}
