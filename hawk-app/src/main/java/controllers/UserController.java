package controllers;

import models.User;
import services.UserService;
import services.UserServiceImpl;

import io.javalin.http.Context;

public class UserController {
    static UserService userService = new UserServiceImpl();

    public static void getAllUsers(Context context){
        context.json(userService.getAllUsers());
    }

    public static void checkUser(Context context){
        context.json(userService.checkUser());
    }

    public static void createUser(Context context) {
        User user = context.bodyAsClass(User.class);
        userService.createUser(user);
    }

}
