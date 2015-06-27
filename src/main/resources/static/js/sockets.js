// メモ : 
// $(function(){})は、html内のDOMが全て利用可能になると関数を呼び出す
// 	http://www.jquerystudy.info/reference/core/jQuery3.html
$(function(){
	var ws = new WebSocket("ws://" + location.host + "/echo");

	ws.onopen = function(){
        
		if (!window.Notification) {
			console.log('Notification is not supported');
			return;
		}
        
		Notification.requestPermission(function(selectedPermission) {
			permission = selectedPermission;
		});

		console.log('onOpen : ' + location.host);
		    
	};
    
	ws.onclose = function(){ };
	
    ws.onmessage = function(message){
    	$("#result").append(message.data).append("<br>");
            
        if (permission !== 'granted') {
        	console.log('not allow');
			return;
        }
		
        var msg = JSON.parse(message.data);
        
        var notify = new Notification('Invitation from ' + msg.createdby, 
        		{ 
					tag: 'MyService', 
					body: msg.message, 
					icon: 'images/coffee.jpg'
				});
		notify.addEventListener('click', function() {
			open('http://' + location.host);
		});
    };
        
    ws.onerror = function(event){
    	alert("接続に失敗しました。");
    };
    
    $("#form").submit(function(){
    	ws.send($("#message").val());
        $("#message").val("")
        return false;
    });

});

