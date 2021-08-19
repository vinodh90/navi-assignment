package com.dweepdroid.github.network.user;

import com.dweepdroid.github.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUserAPIs {

    @GET("users/{userName}")
    Call<User> getUser(@Path("userName") String userName);
}
