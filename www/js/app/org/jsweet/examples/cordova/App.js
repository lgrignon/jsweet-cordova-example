"Generated from Java with JSweet 1.0.0-RC1 - http://www.jsweet.org";
var org;
(function (org) {
    var jsweet;
    (function (jsweet) {
        var examples;
        (function (examples) {
            var cordova;
            (function (cordova) {
                var Playlist = (function () {
                    function Playlist(title, id) {
                        ;
                        this.title = title;
                        this.id = id;
                    }
                    return Playlist;
                })();
                cordova.Playlist = Playlist;
                var PlaylistsService = (function () {
                    function PlaylistsService() {
                    }
                    PlaylistsService.prototype.getPlaylists = function () {
                        return [new Playlist("Reggae", 1), new Playlist("Chill", 2), new Playlist("Dubstep", 3),
                            new Playlist("Indie", 4), new Playlist("Rap", 5), new Playlist("Cowbell", 6)];
                    };
                    PlaylistsService.prototype.searchPlaylists = function (search) {
                        return (this.getPlaylists()).filter(function (p, __, ___) {
                            return p.title.indexOf(search) != -1;
                        });
                    };
                    return PlaylistsService;
                })();
                cordova.PlaylistsService = PlaylistsService;
                var BrowseSearch = (function () {
                    function BrowseSearch() {
                    }
                    return BrowseSearch;
                })();
                cordova.BrowseSearch = BrowseSearch;
                var BrowseViewModel = (function () {
                    function BrowseViewModel() {
                    }
                    return BrowseViewModel;
                })();
                cordova.BrowseViewModel = BrowseViewModel;
                var BrowseController = (function () {
                    function BrowseController($scope, $ionicSideMenuDelegate, playlistsService) {
                        var _this = this;
                        this.$scope = $scope;
                        this.playlistsService = playlistsService;
                        this.$scope.search = new BrowseSearch();
                        this.$scope.onSearch = function () { return _this.onSearch(); };
                        this.$scope.playlistsResult = new Array();
                    }
                    BrowseController.prototype.onSearch = function () {
                        console.log("search=" + this.$scope.search.query);
                        var playlists = this.playlistsService.searchPlaylists(this.$scope.search.query);
                        console.log("playlists", playlists);
                        this.$scope.playlistsResult.splice(0, this.$scope.playlistsResult.length);
                        for (var i = 0; i < playlists.length; i++) {
                            this.$scope.playlistsResult.push(playlists[i]);
                        }
                    };
                    return BrowseController;
                })();
                cordova.BrowseController = BrowseController;
                var PlaylistsController = (function () {
                    function PlaylistsController($scope) {
                        this.$scope = $scope;
                        $scope.playlists = [new Playlist("Reggae", 1), new Playlist("Chill", 2),
                            new Playlist("Dubstep", 3), new Playlist("Indie", 4), new Playlist("Rap", 5),
                            new Playlist("Cowbell", 6)];
                    }
                    return PlaylistsController;
                })();
                cordova.PlaylistsController = PlaylistsController;
                var Pizza = (function () {
                    function Pizza() {
                        this.pepperoni = true;
                        this.sausage = false;
                        this.anchovies = true;
                        this.jalapenos = false;
                    }
                    return Pizza;
                })();
                cordova.Pizza = Pizza;
                var PizzaController = (function () {
                    function PizzaController($scope) {
                        var _this = this;
                        this.$scope = $scope;
                        $scope.pizza = new Pizza();
                        $scope.toppings = function () { return _this.getToppings(); };
                    }
                    PizzaController.prototype.getToppings = function () {
                        var _this = this;
                        var toppings = (Object.keys(this.$scope.pizza)).filter(function (flavor, __, ___) {
                            return _this.$scope.pizza[flavor];
                        });
                        if ((toppings.length > 1)) {
                            toppings[toppings.length - 1] = "and " + toppings[toppings.length - 1];
                        }
                        if ((toppings.length > 2)) {
                            return (toppings).join(", ");
                        }
                        else if ((toppings.length > 0)) {
                            return (toppings).join(" ");
                        }
                        else {
                            return "nothing";
                        }
                    };
                    return PizzaController;
                })();
                cordova.PizzaController = PizzaController;
                var App = (function () {
                    function App() {
                    }
                    App.prototype.initialize = function () {
                        this.bindEvents();
                    };
                    App.prototype.bindEvents = function () {
                        var _this = this;
                        var handler = function (e) { return _this.onDeviceReady(e); };
                        document.addEventListener("deviceready", handler, false);
                    };
                    App.prototype.onDeviceReady = function (e) {
                        console.log("device ready!");
                        return null;
                    };
                    App.main = function (args) {
                        App.instance.initialize();
                        var app = angular.module("starter", ["ionic"]);
                        app.config((function ($stateProvider, $urlRouterProvider) {
                            $stateProvider.state("app", { url: "/app", Abstract: true, templateUrl: "templates/menu.html" }).state("app.browse", { url: "/browse", views: {
                                    "menuContent": { templateUrl: "templates/browse.html",
                                        "controller": "BrowseController" } } }).state("app.playlists", { url: "/playlists", views: {
                                    "menuContent": { templateUrl: "templates/playlists.html",
                                        "controller": "PlaylistsController" } } }).state("app.pizza", { url: "/pizza", views: {
                                    "menuContent": { templateUrl: "templates/pizza.html",
                                        "controller": "PizzaController" } } });
                            $urlRouterProvider.otherwise("/app/browse");
                        }));
                        var controller = PlaylistsController;
                        console.log("PlaylistsController: ", controller);
                        app.controller("PlaylistsController", controller);
                        controller = BrowseController;
                        console.log("BrowseController: ", controller);
                        app.controller("BrowseController", controller);
                        controller = PizzaController;
                        console.log("PizzaController: ", controller);
                        app.controller("PizzaController", controller);
                        var playlistsService = PlaylistsService;
                        console.log("playlistsService: ", controller);
                        app.service("playlistsService", playlistsService);
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