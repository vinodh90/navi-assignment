package com.dweepdroid.github.network.pullrequest;

import com.dweepdroid.github.model.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPullRequestAPIs {

    @GET("repos/{userName}/{repoName}/pulls?state=closed")
    Call<List<PullRequest>> getAllPullRequests(@Path("userName") String userName, @Path("repoName") String repoName, @Query("page") int pageNumber);

}
