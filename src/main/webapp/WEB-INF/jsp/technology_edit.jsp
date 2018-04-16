<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="technologyEditForm" class="technologyForm" method="post">
		<input type="hidden" name="technologyId"/>
	    <table cellpadding="5">
	        <tr>
	            <td>跳转链接名称:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="technologyName" data-options="required:true"/>
	            </td>
	        </tr>

	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitTechnologyEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	function submitTechnologyEditForm(){
		$.get("technology/edit_judge",'',function(data){
    		/* if(data.msg != null){
    			$.messager.alert('提示', data.msg);
    		}else{ */

    			$.post("technology/update_all",$("#technologyEditForm").serialize(), function(data){
    				if(data.status == 200){
    					$.messager.alert('提示','修改链接成功!','info',function(){
    						$("#technologyEditWindow").window('close');
    						$("#technologyList").datagrid("reload");
    					});
    				}else{
    					$.messager.alert('提示',data.msg);
    				}
    			});
    		//}
    	});
	}
</script>
