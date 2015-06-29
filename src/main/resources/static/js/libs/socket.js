define(['jquery'], function($) {
    return {

        /**
         * Register this client to server app
         *
         */
        registerClient: function () {
            var END_POINT = "ws://" + location.host + "/client";

            var ws = new WebSocket(END_POINT);

            ws.onopen = function(){
                if (!window.Notification) {
                    console.log('Notification is not supported');
                    return;
                }
                Notification.requestPermission(function(selectedPermission) {
                    permission = selectedPermission;
                });
                console.log('Socket is opened');
            };

            ws.onclose = function(){
                console.log('Socket is closed');
            };

            ws.onmessage = function(message){

                if (permission !== 'granted') {
                    console.log('Notification is not allowed');
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
                alert('Failed to connect socket');
            };
        }
    };
});
