"Generated from Java with JSweet 1.0.0-SNAPSHOT - http://www.jsweet.org";
var org;
(function (org) {
    var jsweet;
    (function (jsweet) {
        var examples;
        (function (examples) {
            var cordova;
            (function (cordova) {
                var App = (function () {
                    function App() {
                    }
                    App.prototype.initialize = function () {
                        this.bindEvents();
                    };
                    App.prototype.bindEvents = function () {
                        var _this = this;
                        document.addEventListener("deviceready", function (e) { return _this.onDeviceReady(e); }, false);
                    };
                    App.prototype.onDeviceReady = function (e) {
                        this.receivedEvent("deviceready");
                        return null;
                    };
                    App.prototype.receivedEvent = function (id) {
                        var parentElement = document.getElementById(id);
                        var listeningElement = parentElement.querySelector(".listening");
                        var receivedElement = parentElement.querySelector(".received");
                        listeningElement.setAttribute("style", "display:none;");
                        receivedElement.setAttribute("style", "display:block;");
                        console.log("Received Event: " + id);
                    };
                    App.main = function (args) {
                        App.instance.initialize();
                    };
                    App.instance = new App();
                    return App;
                })();
                cordova.App = App;
            })(cordova = examples.cordova || (examples.cordova = {}));
        })(examples = jsweet.examples || (jsweet.examples = {}));
    })(jsweet = org.jsweet || (org.jsweet = {}));
})(org || (org = {}));
org.jsweet.examples.cordova.App.main(null);
//# sourceMappingURL=App.js.map