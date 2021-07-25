package driver;

import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public");
		}).start(5001);

		app.get("/airport-results", context -> {
	    	context.render("./public/airport_results.html");
	    });
	    
	    app.get("/search-results", context -> {
	    	context.render("./public/search_results.html");
	    });
	    
	    app.get("/price-results", context -> {
	    	context.render("./public/price_results.html");
	    });

		
		FrontController fc = new FrontController(app);
	}
}