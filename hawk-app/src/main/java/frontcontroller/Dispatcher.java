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

		app.routes(() ->{
			path("/api/test", () -> {
				get(TestController::testCon);
			});
		});
		
		app.routes(()->{
			path("/api/users",() ->{
				get(UserController::getAllUsers);
			});
		});

		app.routes(()->{
			path("/api/check-user", ()->{
				post(UserController::checkUser);
			});
		});

		app.routes(()->{
			path("/api/register", ()->{
				post(UserController::createUser);
			});
		});
	}

}
