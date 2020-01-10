package com.github.elwyncrestha.darazreplica.endpoint;

import com.github.elwyncrestha.darazreplica.model.Collection;
import com.github.elwyncrestha.darazreplica.model.Product;
import com.github.elwyncrestha.darazreplica.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Elvin Shrestha on 1/10/20
 */
public interface DarazService {

    @GET("products")
    Call<List<Product>> getProduct();

    @GET("collections")
    Call<List<Collection>> getCollection();

    @POST("users/login")
    Call<User> login(@Body User user);

    @POST("users/register")
    Call<User> register(@Body User user);
}
