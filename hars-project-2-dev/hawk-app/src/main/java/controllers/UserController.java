package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import io.javalin.http.Context;
import models.Reservation;
import models.Traveller;
import models.User;
import services.ReservationService;
import services.ReservationServiceImpl;
import services.TravellersService;
import services.TravellersServiceImpl;
import services.UserService;
import services.UserServiceImpl;

public class UserController {
    static UserService userService = new UserServiceImpl();
    static ReservationService reservationService = new ReservationServiceImpl();
    static TravellersService travellersService = new TravellersServiceImpl();

    public static void getAllUsers(Context context){
        context.json(userService.getAllUsers());
    }

    public static void createUser(Context context) {
        User user = context.bodyAsClass(User.class);
        userService.createUser(user);
        context.result("A new user has been created!");
    }

    public static void checkUser(Context context){
        User user = context.bodyAsClass(User.class);
        context.json(userService.checkUser(user));
    }
    public static void userLogIn(Context context){
        try {
			User user = userService.userLogIn(context.formParam("email"), context.formParam("password"));
			context.sessionAttribute("currentUser", user);
			context.json(user);
			context.render("public/dashboard.html");
		} catch (IllegalArgumentException e) {
			context.redirect("/");
		}
    }
    public static void registerUser(Context context){
        userService.registerUser(context.formParam("firstName"),
                context.formParam("lastName"),
                context.formParam("email"),
                context.formParam("password"));
        context.render("public/index.html");
    }

    public static void defaultPage(Context context){
        if (context.sessionAttribute("currentUser") == null) {
			context.render("public/login.html");
		} else {
			context.redirect("/api/airports");
		}
    }
    
    public static void userDashboard(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		context.render("public/dashboard.html");
    	} else {
    		context.redirect("/");
    	}
    }
    
    public static void airportsNearest(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		User user = context.sessionAttribute("currentUser");
    		context.render("public/index.html");
    	} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void airportResults(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		context.render("public/airport_results.html");
    	} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void searchResults(Context context) {
    	// Check the user is logged in
    	if (context.sessionAttribute("currentUser") != null) {
    		//Validate the query params
    		Date date=new Date();
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    		String departureDate = context.queryParam("departureDate");
    		String returnDate = context.queryParam("returnDate");
    		String adults = context.queryParam("adults");
    		String maxPrice = context.queryParam("maxPrice");
    		dateFormat.setLenient(false);
    		try {
    			dateFormat.parse(departureDate.trim());
    			dateFormat.parse(returnDate.trim());
            } catch (ParseException pe) {
                context.redirect("/api/airports");
            } catch (NullPointerException npe) {
            	context.redirect("/api/airports");
            }
    		try {
				if (Integer.valueOf(adults) < 1 || Integer.valueOf(maxPrice) < 1) {
					context.redirect("/api/airports");
				}
			} catch (NumberFormatException e) {
				context.redirect("/api/airports");
			}
    		
			context.render("public/search_results.html");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void priceResults(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
			context.render("public/price_results.html");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void paymentInfo(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
			context.render("public/create_order.html");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void createOrder(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
			User user = context.sessionAttribute("currentUser");
			int userId = user.getId();
			String jsonCookie = context.cookie("flight");
			context.removeCookie("flight");
			reservationService.createReservation(userId, jsonCookie);
			context.redirect("/");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void getAllReservationsByUserId(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
			User user = context.sessionAttribute("currentUser");
			int userId = user.getId();
			List<Reservation> reservations = reservationService.getAllReservations(userId);
			context.json(reservations);
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void getReservationById(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
			User user = context.sessionAttribute("currentUser");
			Integer reservationId = Integer.parseInt(context.pathParam("id"));
			Reservation reservation = reservationService.getReservation(reservationId);
			context.json(reservation);
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void seatmapDisplay(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
			context.render("public/seatmap_display.html");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void bookSeats(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		Integer reservationId = Integer.parseInt(context.pathParam("id"));
    		Map<String, List<String>> map = context.formParamMap();
    		for (Map.Entry<String, List<String>> pair : map.entrySet()) {
    			String key = pair.getKey();
    			String planeSeat = pair.getValue().get(0);
    			String carrierCode = key.split("-")[0];
    			Integer flightNumber = Integer.parseInt(key.split("-")[1]);
    			travellersService.bookSeat(reservationId, planeSeat, "", "2011-01-01 00:00:00", carrierCode, flightNumber);
    		}
    		context.redirect("/api/dashboard");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void updateSeats(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		Integer reservationId = Integer.parseInt(context.pathParam("id"));
    		Map<String, List<String>> map = context.formParamMap();
    		for (Map.Entry<String, List<String>> pair : map.entrySet()) {
    			String key = pair.getKey();
    			String planeSeat = pair.getValue().get(0);
    			Integer travellerId = Integer.parseInt(key.split("-")[3]);
    			travellersService.updateSeat(travellerId, planeSeat);
    		}
    		context.redirect("/api/dashboard");
		} else {
    		//Redirect to login if the user isn't logged in
    		context.redirect("/");
    	}
    }
    
    public static void getAllSeatsByReservationId(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		Integer reservationId = Integer.parseInt(context.pathParam("id"));
    		List<Traveller> seats = travellersService.getAllSeats(reservationId);
    		context.json(seats);
    	} else {
    		context.redirect("/");
    	}
    }
    
    public static void deleteReservation(Context context) {
    	if (context.sessionAttribute("currentUser") != null) {
    		Integer reservationId = Integer.parseInt(context.pathParam("id"));
    		reservationService.deleteReservation(reservationId);
    		context.redirect("/api/dashboard");
    	} else {
    		context.redirect("/");
    	}
    }

    public static void logOut(Context context){
        if (context.sessionAttribute("currentUser") != null) {
			context.sessionAttribute("currentUser", null);
			context.result("You logged out, Thank you for using HARS");
		} else {
			//Redirect to login if the user isn't logged in
    		context.redirect("/");
		}
    }

}
