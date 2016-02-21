package org.jsweet.examples.cordova;

import static def.angularjs.Globals.angular;
import static jsweet.dom.Globals.console;
import static jsweet.dom.Globals.document;
import static jsweet.util.Globals.array;
import static jsweet.util.Globals.function;
import static jsweet.util.Globals.union;
import static jsweet.util.StringTypes.deviceready;

import java.util.function.Function;
import java.util.function.Supplier;

import def.angular_ui_router.ng.ui.IState;
import def.angular_ui_router.ng.ui.IStateProvider;
import def.angular_ui_router.ng.ui.IUrlRouterProvider;
import def.angularjs.ng.IModule;
import def.angularjs.ng.IScope;
import def.ionic.ionic.sidemenu.IonicSideMenuDelegate;
import jsweet.dom.Event;
import jsweet.lang.Array;
import jsweet.lang.Interface;
import jsweet.lang.Object;

class Playlist {

	String title;
	int id;

	Playlist(String title, int id) {
		super();
		this.title = title;
		this.id = id;
	}
}

class PlaylistsService {
	public Playlist[] getPlaylists() {
		return new Playlist[] { new Playlist("Reggae", 1), new Playlist("Chill", 2), new Playlist("Dubstep", 3),
				new Playlist("Indie", 4), new Playlist("Rap", 5), new Playlist("Cowbell", 6) };
	}

	public Playlist[] searchPlaylists(String search) {
		return array(getPlaylists()).filter((p, __, ___) -> {
			return p.title.indexOf(search) != -1;
		});
	}
}

class BrowseSearch {
	String query;
}

class BrowseViewModel {
	BrowseSearch search;

	Array<Playlist> playlistsResult;

	Runnable onSearch;
}

class BrowseController {

	private BrowseViewModel $scope;
	private PlaylistsService playlistsService;

	BrowseController(BrowseViewModel $scope, IonicSideMenuDelegate $ionicSideMenuDelegate,
			PlaylistsService playlistsService) {
		this.$scope = $scope;
		this.playlistsService = playlistsService;

		this.$scope.search = new BrowseSearch();
		this.$scope.onSearch = this::onSearch;
		this.$scope.playlistsResult = new Array<>();
	}

	private void onSearch() {
		console.log("search=" + $scope.search.query);
		Playlist[] playlists = playlistsService.searchPlaylists($scope.search.query);

		console.log("playlists", playlists);
		$scope.playlistsResult.splice(0, $scope.playlistsResult.length);
		for (int i = 0; i < playlists.length; i++) {
			$scope.playlistsResult.push(playlists[i]);
		}
	}
}

@Interface
abstract class PlaylistsViewModel extends IScope {
	Playlist[] playlists;
}

class PlaylistsController {
	PlaylistsViewModel $scope;

	PlaylistsController(PlaylistsViewModel $scope) {
		this.$scope = $scope;

		$scope.playlists = new Playlist[] { new Playlist("Reggae", 1), new Playlist("Chill", 2),
				new Playlist("Dubstep", 3), new Playlist("Indie", 4), new Playlist("Rap", 5),
				new Playlist("Cowbell", 6) };
	}
}

class Pizza extends jsweet.lang.Object {
	boolean pepperoni = true;
	boolean sausage = false;
	boolean anchovies = true;
	boolean jalapenos = false;
}

@Interface
abstract class PizzaViewModel extends IScope {
	Pizza pizza;
	Supplier<String> toppings;
}

class PizzaController {
	PizzaViewModel $scope;

	PizzaController(PizzaViewModel $scope) {
		this.$scope = $scope;

		$scope.pizza = new Pizza();
		$scope.toppings = this::getToppings;
	}

	String getToppings() {
		String[] toppings = array(Object.keys($scope.pizza)).filter((flavor, __, ___) -> {
			return (Boolean) $scope.pizza.$get(flavor);
		});

		if (toppings.length > 1) {
			toppings[toppings.length - 1] = "and " + toppings[toppings.length - 1];
		}
		if (toppings.length > 2) {
			return array(toppings).join(", ");
		} else if (toppings.length > 0) {
			return array(toppings).join(" ");
		} else {
			return "nothing";
		}
	}
}

public class App {

	public static App instance = new App();

	public void initialize() {
		this.bindEvents();
	}

	public void bindEvents() {
		document.addEventListener(deviceready, (Event e) -> {
			console.log("device ready!");
			return null;
		} , false);
	}

	// public void takePicture() {
	// navigator.camera.getPicture(function(imageURI) {
	//
	// // imageURI is the URL of the image that we can use for
	// // an <img> element or backgroundImage.
	//
	// }, function(err) {
	//
	// // Ruh-roh, something bad happened
	//
	// }, cameraOptions);
	// }

	public static void main(String[] args) {
		App.instance.initialize();

		IModule app = angular.module("starter", new String[] { "ionic" });

		app.config(function((IStateProvider $stateProvider, IUrlRouterProvider $urlRouterProvider) -> {

			$stateProvider.state("app", new IState() {
				{
					url = union("/app");
					Abstract = true;
					templateUrl = union("templates/menu.html");
				}
			}).state("app.browse", new IState() {
				{
					url = union("/browse");
					views = new Views() {
						{
							$set("menuContent", new IState() {
								{
									templateUrl = union("templates/browse.html");
									$set("controller", "BrowseController");
								}
							});
						}
					};
				}
			}).state("app.playlists", new IState() {
				{
					url = union("/playlists");
					views = new Views() {
						{
							$set("menuContent", new IState() {
								{
									templateUrl = union("templates/playlists.html");
									$set("controller", "PlaylistsController");
								}
							});
						}
					};
				}
			}).state("app.pizza", new IState() {
				{
					url = union("/pizza");
					views = new Views() {
						{
							$set("menuContent", new IState() {
								{
									templateUrl = union("templates/pizza.html");
									$set("controller", "PizzaController");
								}
							});
						}
					};
				}
			});
			$urlRouterProvider.otherwise("/app/browse");
		}));

		jsweet.lang.Function controller = function(PlaylistsController.class);
		console.log("PlaylistsController: ", controller);
		app.controller("PlaylistsController", controller);

		controller = function(BrowseController.class);
		console.log("BrowseController: ", controller);
		app.controller("BrowseController", controller);

		controller = function(PizzaController.class);
		console.log("PizzaController: ", controller);
		app.controller("PizzaController", controller);

		jsweet.lang.Function playlistsService = function(PlaylistsService.class);
		console.log("playlistsService: ", controller);
		app.service("playlistsService", playlistsService);
	}
}
