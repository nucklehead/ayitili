(function () {
    $.getScript("//connect.facebook.net/en_US/sdk.js", function(){
        FB.init({
            appId: '1983585678552683',
            version: 'v2.12'
        });
        FB.XFBML.parse($('#social-container')[0]);
    });
    $.getScript("//platform.twitter.com/widgets.js");
})();