$(document).ready(function(){
	/*$('#left').jstree(); 
	$('#left').on("changed.jstree", function (e, data) {
		  console.log(data.selected);
		});*/
	getNodes('#');

	$('#newDomain').click(function(){
		addEntity('#' , 0 , "Add new domain");
	});
	
	$('#logout').click(function(){
		window.location = "index.jsp";
	});
});
function addEntity(parent, type, title, callback){
	message("Loading");
	$.ajax({
		type:'GET',
		dataType:"text",
		url: 'rest/entity/getdescription',
		data:{type:type},
		complete: function(){},
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data){
			message("");
			console.log(data);
			var items = JSON.parse(data);
			content="<table class='iform'>";
			$.each(items, function(k , v) {
				content += "<tr>";
				content += "<td><span>" + k + "</span></td>";
				content += "<td><input id='" + k + "' type='text' class='text' placeholder='" + v + "'><br/></td>";
				content += "</tr>";
			});
			content += "</table>";
			//content = $(content);
			$(this).idialog({width:500, title:title,content:$(content),fcontrol:'title',save:1,cancel:1},function(){
				result = "{";
				first = true;
				title = "";
				$.each(items, function(k , v) {
					if(first == false)
						result += ',';
					else
						title = $('.idialog').find('#' + k).val();
					result = result + '"' + k + '":"' + $('.idialog').find('#' + k).val() + '"';
					first = false;
				});
				result += "}";
				// Save entity
				message("Saving");
				console.log(title);
				$.ajax({
					type:'POST',
					dataType:"text",
					url: 'rest/entity/addentity',
					data:{parent:parent, type:type, title:title, data:result},
					complete: function(){},
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					success: function(data){
						message("");
						closeDialog();
						console.log(data);
						//$("#tree").jstree("create", $("#"+parent), "inside", { "data":"new_node" }, false, true);
						//getNodes('#');
						var ref = $('#tree').jstree(true),
							sel = ref.get_selected();
						if(!sel.length) { return false; }
						sel = sel[0];
						sel = ref.create_node(sel, {"type":"file"});
						//if(sel) {
							//ref.edit(sel);
						//}
					}
				});
				console.log(result);
			
			});			
		}
		
	});
}
function getNodes(parentId){
	message("Loading");
	$.ajax({
		type:'GET',
		dataType:"text",
		url: 'rest/tree/getalltree',
		data:{parentId:parentId},
		complete: function(){},
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data){
			message("");
			var tree = JSON.parse(data);
			//.data = tree ['tree'];
			tree['data'] = tree ['tree'];
			//tree['core'] = "";
			delete tree ['tree'];
			//tree.push({core : tree});
			var tree2 = {};
			tree2["core"] = tree;
			tree2["types"] = {
			    "#" : {
			      "max_children" : 1, 
			      "max_depth" : 4, 
			      "valid_children" : ["root"]
			    },
			    "root" : {
			      "icon" : "/static/3.0.0-beta3/assets/images/tree_icon.png",
			      "valid_children" : ["default"]
			    },
			    "default" : {
			      "valid_children" : ["default","file"]
			    },
			    "file" : {
			      "icon" : "glyphicon glyphicon-file",
			      "valid_children" : []
			    }
			  };
			tree2["plugins"] =  ["contextmenu", "dnd", "search", "state", "types", "unique", "wholerow" ];
			tree2["contextmenu"] = {items: customMenu};
			/*tree2["dnd"] = {
	                "drop_target" : "#content"
	                
	            };
			tree2["drag_check"] = function (data) {
				console.log('here');
                if(data.r.attr("type") == "0") {
                	console.log('here');
                    return false;
                }
                return { 
                    after : false, 
                    before : false, 
                    inside : true 
                };
            };*/
//			tree['core']['data'] = tree;
			
			console.log(tree2);
			
			$('#tree').jstree(tree2);
			
			 //tree.jstree("refresh");
			  var to = false;
			  $('#searchTree').keyup(function () {
			    if(to) { clearTimeout(to); }
			    to = setTimeout(function () {
			      var v = $('#searchTree').val();
			      console.log(v);
			      $('#tree').jstree(true).search(v);
			    }, 250);
			  });
			  $('#tree').on("changed.jstree", function (e, data) {
			      if(data.node.original.type=='1'){			    	  
			    	  //$('#right').html(getSubDomain(data.node.original.text));
			  		}
			    });
		}
		});
}
function customMenu(node){
	 var items = {};
		        /*renameItem: { // The "rename" menu item
		            label: "Rename",
		            action: function () {}
		        },
		        deleteItem: { // The "delete" menu item
		            label: "Delete",
		            action: function () {}
		        }
		    };*/
	 console.log(node.original.type);
 		switch (node.original.type){
 		case '0':	//domain
			items.newItem = {
	            label: "Add new subdomain",
	            action: function () {
	            	addEntity(node.original.id , 1 , "Add new subdomain");
	            }
	        };
			break;
		case '1':	//algorithm
			items.newItem = {
	            label: "Add new algorithm type",
	            action: function () {
	            	addEntity(node.original.id , 8 , "Add new algorithm type");
	            }
	        };
			break;
		case '108':	//algorithm
			items.newItem = {
	            label: "Add new algorithm",
	            action: function () {
	            	addEntity(node.original.id , 2 , "Add new algorithm");
	            }
	        };
			break;

		case '103':	//Scenario
			items.newItem = {
	            label: "Add new scenario",
	            action: function () {
	            	addEntity(node.original.id , 3 , "Add new scenario");
	            }
	        };
			break;
		case '104':	//Benchmark
			items.newItem = {
	            label: "Add new benchmark",
	            action: function () {
	            	addEntity(node.original.id , 4 , "Add new benchmark");
	            }
	        };
			break;
		case '105':	//Dataset
			items.newItem = {
	            label: "Add new dataset",
	            action: function () {
	            	addEntity(node.original.id , 5 , "Add new dataset");
	            }
	        };
			break;
		case '106':	//Measure
			items.newItem = {
	            label: "Add new measure",
	            action: function () {
	            	addEntity(node.original.id , 6 , "Add new measure");
	            }
	        };
			break;
		case '107':	//Computational environment
			items.newItem = {
	            label: "Add new computational environment",
	            action: function () {
	            	addEntity(node.original.id , 7 , "Add new computational environment");
	            }
	        };
			break;
		default:
			items.newItem = {
	            label: "properties",
	            action: function () {
	            	console.log(node);
	            	getProperties(node.original.entityId , node.original.text);
	            }
	        };

		}
	 			
	 			
		/*    if ($(node).hasClass("folder")) {
		        // Delete the "delete" menu item
		        delete items.deleteItem;
		    }*/
 		return items;
}
function getProperties(id, title){
	console.log(id);
	message("Loading");
	$.ajax({
		type:'GET',
		dataType:"text",
		url: 'rest/entity/getentity',
		data:{id:id},
		complete: function(){},
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data){
			console.log(data);
			message("");
			var items = JSON.parse(data);
			content="<table class='iform'>";
			$.each(items, function(k , v) {
				content += "<tr>";
				content += "<td><span>" + k + "</span></td>";
				content += "<td><input id='" + k + "' type='text' class='text' value='" + v + "'><br/></td>";
				content += "</tr>";
			});
			content += "</table>";
			//content = $(content);
			$(this).idialog({width:500, title:title,content:$(content),fcontrol:'title',save:1,cancel:1},function(){
			});
			
		}
	});

}
function getSubDomain(name){
	var object = "<div class='subdomain'><span id = 'name' draggable='true'>"+ name +"</span><input id='run' type='button' value='Run'/>" +
			"<div id='content' ondrop='drop(event)' ondragover='allowDrop(event)'></div>";
	object = $(object);
	return object;
}
function allowDrop(ev)
{
	ev.preventDefault();
}
function drop(ev)
{
	console.log('dropped');
	/*ev.preventDefault();
	if(ev.dataTransfer.getData("origin")==$(ev.target).parent(this).html()){
		return false;
	}
	var items=new Array();
	data=ev.dataTransfer.getData("items");
	parentCode=ev.dataTransfer.getData("parentCode");
	if(data.indexOf(',')>0)
		items=data.split(',');
	else
		items.push(data);
	tBody=$(ev.target).parent(this).parent(this);
	parent=$(ev.target).parent(this).find('#i').text();
	file({items:items,tBody:tBody,parentCode:parentCode,parent:parent,action:'move'});
	return true;*/
}
function message(text){
	if(text!=''){
		if(text.length>30)
			text=text.substring(0,30)+"...";
		$('#messageBox').html(text).fadeIn('fast');
	}
	else{
		$('#messageBox').fadeOut('slow');
	}
}
