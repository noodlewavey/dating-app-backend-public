package com.wang.app.rest.Security;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 70000;
    public static final String JWT_SECRET = "secret"; //normally put this in gitignore
    //if someone can obtain your jwt secret...they can generate tokens on their own
    //don't let this get out...
    //for course purposes


}
