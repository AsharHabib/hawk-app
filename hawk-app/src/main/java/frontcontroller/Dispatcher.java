package frontcontroller;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import controllers.TestController;

//import controllers.UserController;
import io.javalin.Javalin;

public class Dispatcher {
	
	public Dispatcher(Javalin app) {

		app.routes(() ->{
			path("/api/test", () -> {
				get(TestController::testCon);
			});
		});
		
		
	}

}
