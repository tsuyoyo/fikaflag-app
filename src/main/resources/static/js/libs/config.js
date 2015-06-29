/**
 * RequireJSの設定
 *
 */
requirejs.config({
    baseUrl : '../../',  // pathsのbaseになる場所で、このconfig.jsからの相対パス

    paths : {
        jquery : [
            'bower_components/jquery/dist/jquery'
        ],
        join : [
            'js/libs/join'
        ],
        socket : [
            'js/libs/socket'
        ]
    }

});