package com.megagao.production.ssm.controller.scheduling;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.megagao.production.ssm.domain.Product;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.service.ProductService;
import com.megagao.production.ssm.service.UserService;
import com.megagao.production.ssm.util.QRCode;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@RequestMapping("/get/{productId}")
	@ResponseBody
	public Product getItemById(@PathVariable String productId) throws Exception{
		Product cProduct = productService.get(productId);
		return cProduct;
	}
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "product_list";
	}
	
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Product> getData() throws Exception{
		return productService.find();
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "product_add";
	}
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "product_edit";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, Product product,HttpServletRequest request) throws Exception{
		String username = (String) request.getSession().getAttribute("username");
		 String rolename = (String) request.getSession().getAttribute("rolename");
		 if("管理员".equals(rolename)){
			 product.setProductType(username);
		 }
		EUDataGridResult result = productService.getList(page, rows, product);
		return result;
	}
	//新建小组，生产二维码
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Product product,HttpServletRequest request, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		
		if(productService.finda(product.getProductName()).size()>0){
			return CustomResult.build(101, "该小组名已经存在，请更换小组名!");
		} 
		Random rand = new Random();
		rand.nextInt(999999);
		product.setProductId(rand.nextInt(999999)+"");
		while(productService.get(product.getProductId()) != null){
			product.setProductId(rand.nextInt(999999)+"");
		}
			//生产二维码
			String dirpath=request.getSession().getServletContext().getRealPath("/").toString()+"qcode";
			File pathdir = new File(dirpath);
			if (!pathdir.exists()) {
				pathdir.mkdir();
			}
			String path=dirpath+"/"+product.getProductId()+".png";
		     ResourceBundle resource = ResourceBundle.getBundle("jdbc");
		     String key = resource.getString("severurl");
		    String urlpath=key+"/production_ssm/qcode/"+product.getProductId()+".png";
			product.setImage(urlpath);
			//网址拼接
			String contentUrl=key+"/production_ssm/count/get/"+product.getProductId();
			product.setNote(contentUrl);
			QRCode.encoderQRCode(contentUrl,path, "png", 10);
			
			result = productService.insert(product);
		
		return result;
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid Product product, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return productService.update(product);
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Product product, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return productService.update(product);
	}
	
	@RequestMapping(value="/update_note")
	@ResponseBody
	private CustomResult updateNote(@Valid Product product, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return productService.updateNote(product);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = productService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = productService.deleteBatch(ids);
		return result;
	}
	
	//根据小组id查找
	@RequestMapping("/search_product_by_productId")
	@ResponseBody
	public EUDataGridResult searchProductByProductId(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = productService.searchProductByProductId(page, rows, searchValue);
		return result;
	}
	
	//根据小组名称查找
	@RequestMapping("/search_product_by_productName")
	@ResponseBody
	public EUDataGridResult searchProductByProductName(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = productService.searchProductByProductName(page, rows, searchValue);
		return result;
	}
	
	//根据小组类型查找
	@RequestMapping("/search_product_by_productType")
	@ResponseBody
	public EUDataGridResult searchProductByProductType(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = productService.searchProductByProductType(page, rows, searchValue);
		return result;
	}
}
