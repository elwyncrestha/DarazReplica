package com.github.elwyncrestha.darazreplica.util;

import android.util.Log;

import com.github.elwyncrestha.darazreplica.endpoint.DarazService;
import com.github.elwyncrestha.darazreplica.endpoint.RetrofitUtils;
import com.github.elwyncrestha.darazreplica.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Elvin Shrestha on 1/10/20
 */
public class UserUtils {

    public static boolean loggedIn = false;

    public static boolean checkUser(String email, String password) {
        DarazService darazService = RetrofitUtils.getRetrofit().create(DarazService.class);
        Call<User> userCall = darazService.login(new User(null, email, password));
        try {
            Response<User> loginResponse = userCall.execute();
            if (loginResponse.isSuccessful()) {
                loggedIn = true;
            }
        } catch (IOException e) {
            Log.e("Login Error", e.getLocalizedMessage(), e);
        }
        return loggedIn;
    }
}
