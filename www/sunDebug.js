var exec = require('cordova/exec');
var sunDebug   = {
  open:function (arg0, success, error) {
    exec(success, error, 'sunDebug', 'open', [arg0]);
	}
};
module.exports = sunDebug;
