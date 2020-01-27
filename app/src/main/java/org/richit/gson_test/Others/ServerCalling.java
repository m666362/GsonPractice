package org.richit.gson_test.Others;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interfaces.StringRequestListener;

import org.richit.gson_test.Models.User;

public class ServerCalling {
    private static String api_endpoint = "http://192.168.0.101:3000/";

    private static String users = "users/";

    public static void createUser(User user, StringRequestListener listener) {
        AndroidNetworking.post( api_endpoint + users )
                .addBodyParameter( user )
                .build()
                .getAsString( listener );
    }

    public static void getAllUsers(StringRequestListener listener) {
        AndroidNetworking.get( api_endpoint + users )
                .build()
                .getAsString( listener );
    }
}
