package driver;

import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
* Main class with the main method, which creates the Javalin app with static files at /public and runs on port 5000
* FrontController is also defined here to take in the Javalin app
*/
public class Main {
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public");
		}).start(5000);
<<<<<<< HEAD

//		app.get("/airport-results", context -> {
//	    	context.render("./public/airport_results.html");
//	    });
//	    
//	    app.get("/search-results", context -> {
//	    	context.render("./public/search_results.html");
//	    });
//	    
//	    app.get("/price-results", context -> {
//	    	context.render("./public/price_results.html");
//	    });
=======
		
		app.get("/airport-results", context -> {
	    	context.render("./public/airport_results.html");
	    });
	    
	    app.get("/search-results", context -> {
	    	context.render("./public/search_results.html");
	    });
	    
	    app.get("/price-results", context -> {
	    	context.render("./public/price_results.html");
	    });
>>>>>>> 701008def2d6d6185a7cdf79fe7aa4c1b770591d

		
		FrontController fc = new FrontController(app);
	}
}
