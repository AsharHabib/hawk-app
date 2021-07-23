package driver;

import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public");
		}).start(5000);

		FrontController fc = new FrontController(app);
	}
}