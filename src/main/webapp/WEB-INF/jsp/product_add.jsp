<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="productAddForm" class="productForm" method="post">
	    <table cellpadding="5" >
	        <tr>
	            <td>小组编号:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="productId" data-options="required:true"/>
	            </td>
	        </tr>
	        <tr>
	            <td>小组名称:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="productName" data-options="required:true"/>
    			</td>  
	        </tr>
	        
	        <tr>
	            <td>所属用户:</td>
	            <td>
	            	<input class="easyui-combobox" name="productType"   
    					data-options="valueField:'id',textField:'username',
    						url:'user/get_data', editable:false, required:true" />
    			</td>  
	        </tr>

	        <tr>
	            <td>状态:</td>
	            <td>
		            <select class="easyui-combobox" name="status" panelHeight="auto" data-options="required:true,
		            		width:150, editable:false">
						<option value="1">启用</option>
						<option value="0">停用</option>
					</select>
				</td>
	        </tr>


	    </table>
	    <input type="hidden" name="productParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitProductAddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearProductAddForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	
	var productAddEditor ;
	//页面初始化完毕后执行此方法

	
	//提交表单
	function submitProductAddForm(){


		
		//ajax的post方式提交表单
		//$("#productAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("product/insert",$("#productAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增成功!');
		//clearProductAddForm();
				$("#productAddWindow").window('close');

				$("#productList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	function clearProductAddForm(){
		$('#productAddForm').form('reset');
		productAddEditor.html('');
	}
</script>
