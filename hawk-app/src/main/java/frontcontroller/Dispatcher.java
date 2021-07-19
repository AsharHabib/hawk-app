package frontcontroller;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import controllers.TestController;

//import controllers.UserController;
import controllers.UserController;
import io.javalin.Javalin;

public class Dispatcher {
	
	public Dispatcher(Javalin app) {
		// This route is for opening the localhost, the default page is the login page
		app.routes(() ->{
			path("/", () -> {
				get(UserController::defaultPage);
			});
		});
		// initial test route
		app.routes(() ->{
			path("/api/test", () -> {
				get(TestController::testCon);
			});
		});
		// Checks if the user is in the database
		app.routes(()->{
			path("/api/users",() ->{
				get(UserController::getAllUsers);
			});
		});
		// A test variation of the login functionality
		app.routes(()->{
			path("/api/check-user", ()->{
				post(UserController::checkUser);
			});
		});
		// The login functionality
		app.routes(()->{
			path("/api/login", ()->{
				post(UserController::userLogIn);
			});
		});
		// The logout functionality with the session
		app.routes(()->{
			path("/api/logout", ()->{
				post(UserController::logOut);
			});
		});
		// The test variation of registering the user
		app.routes(()->{
			path("/api/create-user", ()->{
				post(UserController::createUser);
			});
		});
		// The register functionality
		app.routes(()->{
			path("/api/register", ()->{
				post(UserController::registerUser);
			});
		});

	}

}
