package frontcontroller;

import io.javalin.Javalin;
import io.javalin.http.Context;


public class FrontController {
	Javalin app;
	Dispatcher dispatcher;
	
	public FrontController(Javalin app) {
		this.app = app;
		
		
		this.app.before("/api/*", FrontController::checkAllRequests);
		
		this.dispatcher = new Dispatcher(app);
	}
	
	
	public static void checkAllRequests(Context context) {
		System.out.println("Middleware has been hit");
	}

}
