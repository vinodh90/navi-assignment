package com.dweepdroid.github.network.repo;

import com.dweepdroid.github.model.Repo;
import com.dweepdroid.github.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRepoAPIs {

    @GET("repos/{userName}/{repoName}")
    Call<Repo> getRepo(@Path("userName") String userName, @Path("repoName") String repoName);
}
