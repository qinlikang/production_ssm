<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<!-- Table -->
<table class="easyui-datagrid" id="deviceType" title="设备种类列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,rownumbers:true,
       	url:'count/find',method:'get',pageSize:30, fitColumns:true,toolbar:toolbar_deviceType">
       
       	
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'deviceTypeId',width:100,align:'center'">
				小组编号
			</th>
            <th data-options="field:'deviceTypeName',width:100,align:'center'">
				小组名称
			</th>
            <th data-options="field:'deviceTypeQuantity',width:100,align:'center'">
				中转次数
			</th>
        </tr>
    </thead>
</table>

<!-- Toolbar -->
<div  id="toolbar_deviceType" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='deviceType:add'}">
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="deviceType_add()">
					新增
				</a>
		    </div>  
		</c:if>
		<c:if test="${per=='deviceType:edit'}">
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="deviceType_edit()">
					编辑
				</a>
		    </div>  
		</c:if>
		<c:if test="${per=='deviceType:delete'}">
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="deviceType_delete()">
					删除
				</a>
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="doSearch_deviceType()">查询</a>  
	</div>  
	
	开始时间：<input class="easyui-datebox" type="text" id="startDate" name="startDate" value="2018-04-15"/>
						   
	结束时间：<input class="easyui-datebox" type="text" id="endDate" name="endDate" value="2018-04-15"/>
						   


</div>

<!-- deviceTypeAddWindow -->
<div id="deviceTypeAddWindow" class="easyui-window" title="添加设备种类" data-options="modal:true,
	closed:true,resizable:true,iconCls:'icon-save',href:'deviceType/add'" style="width:40%;height:55%;padding:10px;">
</div>

<!-- deviceTypeEditWindow -->
<div id="deviceTypeEditWindow" class="easyui-window" title="编辑设备种类" data-options="modal:true,
	closed:true,resizable:true,iconCls:'icon-save',href:'deviceType/edit'" style="width:40%;height:55%;padding:10px;">
</div>


<script>
function doSearch_deviceType(){ //用户输入用户名,点击搜素,触发此函数  
var value='';
var name='';
	if(value == null || value == ''){
		$("#deviceType").datagrid({
	        title:'设备种类列表', 
	        singleSelect:false, 
	        collapsible:true, 
	        pagination:true, 
	        rownumbers:true, 
	        method:'get',
			nowrap:true, 
			toolbar:"toolbar_deviceType", 
			url:'count/find?startDateString='+$("#startDate").datebox('getValue')+'&endDateString='+$("#endDate").datebox('getValue'),  
			method:'get', 
			loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:false },
				{field : 'deviceTypeId', width : 100, align:'center', title : '小组编号'},
				{field : 'deviceTypeName', width : 100, align : 'center', title : '小组名称'},
				{field : 'deviceTypeQuantity', width : 100, title : '跳转次数', align:'center'},
	        ] ],  
	    });
	}else{
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
		$("#deviceType").datagrid({  
	        title:'设备种类列表', 
	        singleSelect:false, 
	        collapsible:true, 
	        pagination:true, 
	        rownumbers:true, 
	        method:'get',
			nowrap:true, 
			toolbar:"toolbar_deviceType", 
			url:'count/find?name='+value+'&startDateString='+$("#startDate").datebox('getValue')+'&endDateString='+$("#endDate").datebox('getValue'), 
			loadMsg:'数据加载中......',
			//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'deviceTypeId', width : 100, align:'center', title : '设备种类编号'},
				{field : 'deviceTypeName', width : 100, align : 'center', title : '设备种类名称'},
				{field : 'deviceTypeQuantity', width : 100, title : '台数', align:'center'},
				{field : 'deviceTypeWarranty', width : 130, title : '保修期', align:'center',formatter:TAOTAO.formatDate}
	        ] ],  
	    });
	}
}

	/*********************************** Toolbar function ***********************************/
	function getDeviceTypeSelectionsIds(){
		var deviceType = $("#deviceType");
		var sels = deviceType.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].deviceTypeId);
		}
		ids = ids.join(","); 
		return ids;
	}
	
	function deviceType_add(){
    	$.get("deviceType/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#deviceTypeAddWindow").window("open");
       		}
       	});
    }
    
    function deviceType_edit(){
    	$.get("deviceType/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getDeviceTypeSelectionsIds();
    	    	
    	    	if(ids.length == 0){
    	    		$.messager.alert('提示','必须选择一个设备种类才能编辑!');
    	    		return ;
    	    	}
    	    	if(ids.indexOf(',') > 0){
    	    		$.messager.alert('提示','只能选择一个设备种类!');
    	    		return ;
    	    	}
    	    	
    	    	$("#deviceTypeEditWindow").window({
    	    		onLoad :function(){
    	    			//回显数据
    	    			var data = $("#deviceType").datagrid("getSelections")[0];
    	    			data.deviceTypeWarranty = TAOTAO.formatDateTime(data.deviceTypeWarranty);
    	    			$("#deviceTypeEditForm").form("load", data);
    	    		}
    	    	}).window("open");
       		}
       	});
    }
    
    function deviceType_delete(){
    	$.get("deviceType/delete_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getDeviceTypeSelectionsIds();
    	    	if(ids.length == 0){
    	    		$.messager.alert('提示','未选中设备种类!');
    	    		return ;
    	    	}
    	    	$.messager.confirm('确认','确定删除ID为 '+ids+' 的设备种类吗？',function(r){
    	    	    if (r){
    	    	    	var params = {"ids":ids};
    	            	$.post("deviceType/delete_batch",params, function(data){
    	        			if(data.status == 200){
    	        				$.messager.alert('提示','删除设备种类成功!',undefined,function(){
    	        					$("#deviceType").datagrid("reload");
    	        				});
    	        			}
    	        		});
    	    	    }
    	    	});
       		}
       	});
    }
    
    function deviceType_reload(){
    	$("#deviceType").datagrid("reload");
    }
	/*********************************** Toolbar function ***********************************/
	
	//根据index拿到该行值
	function onDeviceTypeClickRow(index) {
		var rows = $('#deviceType').datagrid('getRows');
		return rows[index];
		
	}
</script>