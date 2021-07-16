package driver;

import io.javalin.Javalin;

public class Main {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(5000);
		
		app.get("/seatmap", context -> {
			context.render("seatmap.html");
		});

	}

}
