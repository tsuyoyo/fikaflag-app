define(['jquery'], function($) {
    return {

        /**
         * Register this client to server app
         *
         */
        registerClient: function (onInvitationPosted) {
            var END_POINT = "ws://" + location.host + "/client";

            var ws = new WebSocket(END_POINT);

            var notify = function(flag) {
                if (permission !== 'granted') {
                    console.log('Notification is not allowed');
                    return;
                }

                var notify = new Notification('Fika invitation has comes',
                        {
                            tag: 'MyService',
                            body: flag.message,
                            icon: 'images/coffee.jpg'
                        });

                notify.addEventListener('click', function() {
                    open('http://' + location.host);
                });

                setTimeout(function() {
                    notify.close();
                }, 5000);
            };

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
                var msg = JSON.parse(message.data);

                if (msg.event == 'openflag') {
                    notify(msg.fikaflag);
                    onInvitationPosted(msg.fikaflag);
                }
                else if (msg.event == 'closeflag') {
                    onInvitationPosted();
                }
            };

            ws.onerror = function(event){
                alert('Failed to connect socket');
            };
        }
    };
});
