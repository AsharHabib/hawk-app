package ye;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;


import io.javalin.Javalin;


public class Main {
	
	public static void main(String[] args) throws ResponseException {
	    Javalin app = Javalin.create().start(5000);
	    
	    app.get("/", context -> {
	    	context.result("u");
	    });

	    app.get("/airports", context -> {
	    	context.render("index.html");
	    });
	    
	    app.get("/airport-results", context -> {
	    	context.render("airport_results.html");
	    });
	    
	    app.get("/search-results", context -> {
	    	context.render("search_results.html");
	    });
	    
	    app.get("/price-results", context -> {
	    	context.render("price_results.html");
	    });
	    
	    app.get("/create-order", context -> {
	    	context.render("create_order.html");
	    });
	    
	    app.get("/seatmap-display", context -> {
	    	context.render("seatmap_display.html");
	    });

	  }

}