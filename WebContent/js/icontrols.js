(function($) {
  $.fn.idialog = function(param,callback) {

  	var dialog="<div class='idialog h' style='";
	if(param.width!=undefined){
		dialog+="width:";
		dialog+=param.width+"px;";
		dialog+="max-width:";
		dialog+=param.width+"px;";
	}
	if(param.height!=undefined){
		dialog+="height:";
		dialog+=param.height+"px;";
	}
	if(param.right!=undefined){
		dialog+="right:";
		dialog+=param.right+"px;";
	}
	if(param.bottom!=undefined){
		dialog+="bottom:";
		dialog+=param.bottom+"px;margin:0;position:fixed;";
	}
	if(param.modal==true)
		modal=true;
	else
		modal=false;
	dialog+="'>\
	<table cellspacing='0' cellpadding='0'><tHead>\
		<tr>\
			<th class='title'>"+param.title+"</th>\
			<th class='close'><i class='icon-remove icon-large'></i></th>\
		</tr></tHead><tBody>\
		<tr>\
			<td class='content' colspan=2></td>\
		</tr>\
		<tr></tBody><tFoot>\
			<td class='buttom' colspan=2>";
		if(param.ok==1)	
			dialog+="<input id='ok' type='button' class='btn' value='"+ok+"'/>";
		if(param.yes==1)	
			dialog+="<input id='yes' type='button' class='btn' value='"+yes+"'/>";
		if(param.no==1)	
			dialog+="<input id='no' type='button' class='btn' value='"+no+"'/>";
		if(param.submit==1)	
			dialog+="<input id='submit' type='button' class='btn' value='"+submit+"'/>";
		if(param.save==1)	
			dialog+="<input id='save' type='button' class='btn' value='"+save+"'/>";
		if(param.cancel=1)	
			dialog+="<input id='cancel' type='button' class='btn' value='"+cancel+"'/>";
		if(param.close==1)	
			dialog+="<input id='close' type='button' class='btn' value='"+close+"'/>";
				
	dialog+="</td>\
		</tr></tFoot>\
	</div>";
	dialog=$(dialog);
	dialog.find('.content').html(param.content);
	dialog.find('#cancel,#close,.close i').on('click',function(){
		if(param.cancelfn){
			param.cancelfn();
		}
		closeDialog();
	});
	dialog.find('#submit,#save').on('click',function(){
		$(this).prop('disabled', true);
		if(param.fcontrol!=undefined)
			callback(dialog.find('#'+param.fcontrol).val());
		else
			callback('');
	});
	dialog.on('keydown',function(e){
		if(e.keyCode==27){
			e.preventDefault();
			closeDialog();
		}
	});
	closeDialog();
	$('#transparent').show().after(dialog);
//	dialog.slideDown("fast");
	dialog.fadeIn();
	if(param.fcontrol!=undefined)
		dialog.find('#'+param.fcontrol).focus().select();
	//dialog.drags({handle:".title"}); 
	}
})( jQuery );
function closeDialog(){
	$('#transparent').hide();
	$('.idialog').remove();
}
jQuery.fn.draggit = function (el) {
    var thisdiv = this;
    var thistarget = $(el);
    var relX;
    var relY;
    var targetw = thistarget.width();
    var targeth = thistarget.height();
    var docw;
    var doch;
    thistarget.css('position','absolute');
    thisdiv.bind('mousedown', function(e){
        var pos = $(el).offset();
        var srcX = pos.left;
        var srcY = pos.top;

        docw = $('body').width();
        doch = $('body').height();

        relX = e.pageX - srcX;
        relY = e.pageY - srcY;
        ismousedown = true;
    });
    $(document).bind('mousemove',function(e){ 
        if(ismousedown)
        {
            targetw = thistarget.width();
            targeth = thistarget.height();
            var maxX = docw - targetw - 10;
            var maxY = doch - targeth - 10;
            var mouseX = e.pageX;
            var mouseY = e.pageY;
            var diffX = mouseX - relX;
            var diffY = mouseY - relY;
            // check if we are beyond document bounds ...
            if(diffX < 0)   diffX = 0;
            if(diffY < 0)   diffY = 0;
            if(diffX > maxX) diffX = maxX;
            if(diffY > maxY) diffY = maxY;
            $(el).css('top', (diffY)+'px');
            $(el).css('left', (diffX)+'px');
        }
    });
    $(window).bind('mouseup', function(e){
        ismousedown = false;
    });
    return this;
}