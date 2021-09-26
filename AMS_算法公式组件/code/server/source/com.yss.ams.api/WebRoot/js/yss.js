$(document).ready(function(){
		$("#main").html("");
		$("#main").append("<table id='grid'></table>");

		$("#grid").flexigrid({
			url :"bundle/list/", 
			method : "GET",   
			dataType :"json",
			colModel : [
	    		{display: '组件标识', name : 'code', width : 300, sortable : true, align: 'left'},
	    		{display: '组件名称', name : 'name', width : 300, sortable : true, align: 'left'},
	    		{display: '组件状态', name : 'state', width : 100, sortable : true, align: 'left'},
	    		{display: '启动级别', name : 'level', width : 100, sortable : true, align: 'left'}
	    		],
    		buttons : [
  					{ name: '安装', display: 'install', onpress:btnClick},
  					{ name: '卸载', display: 'uninstall', onpress:btnClick},
  		            { name: '启动', display: 'start', onpress:btnClick },
  		            { name: '停止', display: 'stop', onpress:btnClick },
  					{separator: true}
  					],
	    	searchitems : [
	    		{display: 'CODE', name : 'code'},
	    		{display: 'NAME', name : 'name', isdefault: true}
	    		],
	    	sortname: "level",
	    	sortorder: "asc",
	    	usepager: false,
	    	title: '组件库',
	    	useRp: true,
	    	rp: 15,
	    	checkbox: true,
	    	resizable: false, //resizable table是否可伸缩
	    	width: document.documentElement.clientWidth-50,
	    	onRowDblclick:rowDblclick,  //行双击
	    	//onSubmit: addFormData,
	    	//preProcess: parsedForm,
	    	height: document.documentElement.clientHeight-150
		});
	});
	
	function rowDblclick(rowData){
        var code = rowData.code;  //rowData.属性名,源码中放进json的名
	  	$.ajax({
		     type:'get',
		     dataType:'json',
		     url:'bundle/list/'+ code,
		     success:function(json){
		    	 var temp = json.requireBundles;
		    	 var s = "";
		    	 for(var i=0; i < temp.length; i++){
		    		 s += temp[i]+"<br>";
		    	 }
		    	 $("#detail").html("<div><table><tr><td width='100'>组件代码</td><td>"+json.code+"</td></tr><tr><td>组件名称</td><td>"+json.name+"</td></tr><tr><td>组件版本</td><td>"+json.version+"</td></tr><tr><td>组件依赖</td><td>"+s+"</td></tr></table></div>");
		 		 showCover();
		    	 //$('#grid').flexReload();
		     },
		     error:function(response){
		    	alert(response.responseText);
		    	//$('#grid').flexReload();
		     }
		});
	}
		
	function btnClick(com, grid) {
       	if (com=='安装'){
			install(grid);
		}else if (com=='卸载'){
			uninstall(grid);
		}else if (com=='启动'){
			start(grid);
		}else if (com=='停止'){
			stop(grid);
		}
    }
	
	function uninstall(grid){
		if ($('.trSelected', grid).length == 0){
			alert("请选择要卸载的组件！");
            return ;  
        }
		
		var arr = new Array(0);
		for(var i=0;i<$('.trSelected',grid).length;i++){ 
			arr.push($('.trSelected',grid)[i].id);
		}
		
		$.ajax({
		     type:'delete',
		     dataType:'json',
		     url:'bundle/uninstall/',
		     data:arr.toString(),
		     success:function(json){
		    	 $('#grid').flexReload();
		     },
		     error:function(response){
		    	alert(response.responseText);
			//STORY #35685 简化接口部署操作、部署、搜索等操作时支持焦点锁定 leijianhua 20170306
			//将勾选的放置到前面
		    	$('#grid')
	    	 	.flexOptions({checkedArray:arr})
	    	 	.flexReload();
//		    	$('#grid').flexReload();
		     }
		});
	}
	
	function showCover() {
		document.getElementById("cover_div").style.display = "block";
		document.getElementById("message_div").style.display = "block";
	}
	
	function hideCover() {
		$("#detail").html("");
		document.getElementById("cover_div").style.display = 'none';
		document.getElementById("message_div").style.display = 'none';
	}
	
	function install(grid){		
		$("#detail").html("<form onsubmit='return doSubmit();' action='bundle/install' method='post' enctype='multipart/form-data'><input name='file' size='80' style='*width:500px;' type='file' id='fileupload' multiple='multiple' accept='aplication/jar'/> <input type='submit' value='提交'><div id='retString'></div> </form>");
		showCover();
	}
	
	function doSubmit(){
		$("#retString").html("");
		
		var userAgent = window.navigator.userAgent.toLowerCase();
		 
		$.browser.msie10 = $.browser.msie && /msie 10\.0/i.test(userAgent);
		$.browser.msie9 = $.browser.msie && /msie 9\.0/i.test(userAgent); 
		$.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
		$.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
		$.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
 
		var deployArray = new Array(0);
		var obj = document.getElementById("fileupload");
		var files;
		var fileName = "";
		if($.browser.msie7){
			fileName = obj.value;
			if(fileName == ""){
				return false;
			}
			
			var pos = fileName.lastIndexOf(".");
			var lastname = fileName.substring(pos,fileName.length);
			deployArray.push('row'+fileName.substring(0,pos));
			if(!(lastname.toLowerCase()==".jar")){
				$("#retString").append(""+fileName+"是无效组件<br>");
				return false;
			}
		}
		else{
			files = obj.files;
			if(files.length==0){
				return false;
			}

			for(var j=0; j < files.length; j++){
				var f = files[j];
				var name=f.name;
				var pos = name.lastIndexOf(".");
				var lastname = name.substring(pos,name.length);
				deployArray.push('row'+name.substring(0,pos));
				if(!(lastname.toLowerCase()==".jar")){
					$("#retString").append(""+name+"是无效组件<br>");
					return false;
				}
			}
		}
         
		$("form").ajaxSubmit({  
            type:"post",  //提交方式  
            dataType:"json", //数据类型  
            url:"bundle/install", //请求url  
            success:function(data){ //提交成功的回调函数
                for(var i=0;i<data.length;i++){
            		$("#retString").append(data[i]+'<br>');
                }
		
		//STORY #35685 简化接口部署操作、部署、搜索等操作时支持焦点锁定 leijianhua 20170306
		//将勾选的放置到前面
                $('#grid')
	    	 		.flexOptions({checkedArray:deployArray})
	    	 		.flexReload();
//                $('#grid').flexReload();
            }
        });
		
        return false; //不刷新页面  
	}
	
	function start(grid){
		if ($('.trSelected', grid).length == 0){
			alert("请选择要启动的组件！");
            return ;  
        }
		
		var arr = new Array(0);
		for(var i=0;i<$('.trSelected',grid).length;i++){ 
			arr.push($('.trSelected',grid)[i].id);
		}
		
		$.ajax({
		    type:'put',
		    dataType:'json',
		    url:'bundle/start/',
		    data:arr.toString(),
		    success:function(json){
		    	$('#grid').flexReload();
		    },
		    error:function(response){
		    	alert(response.responseText);
			//STORY #35685 简化接口部署操作、部署、搜索等操作时支持焦点锁定 leijianhua 20170306
			//将勾选的放置到前面
		    	$('#grid')
		    	 	.flexOptions({checkedArray:arr})
		    	 	.flexReload();
//		    	$('#grid').flexReload();
		    }
		});
	}
	
	function stop(grid){

		if ($('.trSelected', grid).length == 0){
			alert("请选择要停止的组件！");
            return ;  
        }
		
		var arr = new Array(0);
		for(var i=0;i<$('.trSelected',grid).length;i++){ 
			arr.push($('.trSelected',grid)[i].id);
		}
		
		$.ajax({
		     type:'put',
		     dataType:'json',
		     url:'bundle/stop/',
		     data:arr.toString(),
		     success:function(json){
		     //STORY #35685 简化接口部署操作、部署、搜索等操作时支持焦点锁定 leijianhua 20170306
		     //将勾选的放置到前面
		    	 $('#grid')
		    	 	.flexOptions({checkedArray:arr})
		    	 	.flexReload();
//		    	 $('#grid').flexReload();
		     },
		     error:function(json){
		    	alert(json);
		    	$('#grid').flexReload();
		     }
		});
	}