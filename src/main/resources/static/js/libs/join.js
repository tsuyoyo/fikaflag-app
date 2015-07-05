define(['jquery'], function($) {
    return {
        join: function(flagUuid, justNow) {
            var data = {
                flagId: flagUuid,
                name: 'test'
            };

            $.ajax({
                type: "POST",

                url: "join?now=" + justNow,

                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: 'json',

                data: JSON.stringify(data),

                success: function(res){
                    console.log("Join has been submitted : " + JSON.stringify(res));
                },

                error: function(req, status, err) {
                    console.log(err);
                }
            });
        }
    };
});
