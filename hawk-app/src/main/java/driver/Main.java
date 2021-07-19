package driver;

import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
	public static void main(String[] args) {
		//Javalin app = Javalin.create().start(8081);

//		Javalin app = Javalin.create(config -> {
//			config.addStaticFiles("/public", Location.CLASSPATH);
//		}).start(8081);

		// Need to figure out the class path to be able to use by other team members
		// Absolute path is working for now

		Javalin app = Javalin.create(javalinConfig ->
				javalinConfig.addStaticFiles("C:\\Users\\kentr\\Desktop\\project2\\hars-project-2\\hawk-app\\src\\resources\\public",
						Location.EXTERNAL)).start(8081);

		FrontController fc = new FrontController(app);
	}
}
