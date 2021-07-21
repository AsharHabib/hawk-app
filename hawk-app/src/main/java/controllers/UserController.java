package controllers;

import models.User;
import services.UserService;
import services.UserServiceImpl;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    static UserService userService = new UserServiceImpl();

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
        Map<Integer, User> user = userService.userLogIn(context.formParam("email"),
                context.formParam("password"));
        context.sessionAttribute("currentUser", user);
        context.json(user);
        context.render("/index.html");
    }
    public static void registerUser(Context context){
        userService.registerUser(context.formParam("firstName"),
                context.formParam("lastName"),
                context.formParam("email"),
                context.formParam("password"));
        context.render("/index.html");
    }

    public static void defaultPage(Context context){
        context.render("/login.html");
    }

    public static void logOut(Context context){
        context.sessionAttribute("currentUser", null);
        context.result("You logged out, Thank you for using HARS");
    }

}
