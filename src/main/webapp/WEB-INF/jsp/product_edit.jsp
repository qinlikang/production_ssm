<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet"> 
<script src="js/jquery.uploadfile.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="productEditForm" class="productForm" method="post">
		<input type="hidden" name="productId"/>
	    <table cellpadding="5">
	        <tr>
	            <td>小组名称:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="productName" data-options="required:true"></input>
    			</td>  
	        </tr>
	        
	        <tr>
	            <td>所属用户:</td>
	            <td>
	            	<input class="easyui-textbox" value="${activeUser.username}" type="text" name="productType" data-options="required:true"/>
    			</td>  
	        </tr>

	        <tr>
	            <td>状态:</td>
	            <td>
		            <select id="cc" class="easyui-combobox" name="status" panelHeight="auto"
							data-options="required:true,width:150, editable:false">
						<option value="1">启用</option>
						<option value="0">停用</option>
					</select>
				</td>
	        </tr>


	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitProductEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	var productEditEditor ;
	
	$(function(){

	});

	
	function submitProductEditForm(){
		$.get("product/edit_judge",'',function(data){
    		if(data.msg != null){
    			$.messager.alert('提示', data.msg);
    		}else{

    			
    			$.post("product/update_all",$("#productEditForm").serialize(), function(data){
    				if(data.status == 200){
    					$.messager.alert('提示','修改小组成功!','info',function(){
    						$("#productEditWindow").window('close');
    						$("#productList").datagrid("reload");
    					});
    				}else{
    					$.messager.alert('提示',data.msg);
    				}
    			});
    		}
    	});
	}
</script>
