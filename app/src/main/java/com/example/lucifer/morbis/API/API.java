package com.example.lucifer.morbis.API;

/**
 * Created by lucifer on 4/19/2016.
 */
public final class API {
    public static String DEV_URL = "http://168.235.90.24/";
    public static int PORT = 7777;
    public static String PROD_URL = "";

    public static String BASE_URL = DEV_URL;

    public static String LOGIN = (BASE_URL.equals(DEV_URL)?DEV_URL:PROD_URL)+"api-token-auth/";

    public static  String HOSPITAL = (BASE_URL.equals(DEV_URL)?DEV_URL:PROD_URL)+"api/hospitals/";
    
    public static String DOCTOR_INFO = (BASE_URL.equals(DEV_URL)?DEV_URL:PROD_URL)+"api/hospitals/";

}
