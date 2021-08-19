package com.dweepdroid.github.network;

import com.dweepdroid.github.common.Logger;
import com.dweepdroid.github.model.ErrorResponse;
import com.dweepdroid.github.model.IResponseListener;
import com.dweepdroid.github.model.PullRequest;
import com.dweepdroid.github.model.Repo;
import com.dweepdroid.github.model.User;
import com.dweepdroid.github.network.pullrequest.IPullRequestAPIs;
import com.dweepdroid.github.network.repo.IRepoAPIs;
import com.dweepdroid.github.network.user.IUserAPIs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {

    private static final String TAG = NetworkController.class.getCanonicalName();

    private static NetworkController networkController;

    private Retrofit retrofit;

    private static final String BASE_URL = "https://api.github.com";

    private NetworkController(){

    }

    public static NetworkController getInstance(){
        if(networkController == null){
            synchronized (NetworkController.class){
                if(networkController == null){
                    networkController = new NetworkController();
                }
            }
        }

        return networkController;
    }

    public void createRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getUserDetails(String userName, IResponseListener listener){
        Logger.i(TAG, "getUserDetails :: userName: "+userName);
        IUserAPIs userAPIs = retrofit.create(IUserAPIs.class);

        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Logger.d(TAG, "getUserDetails :: onResponse: "+response);
                if(response != null){
                    Logger.d(TAG, "getUserDetails :: onResponse: "+response.body());
                    if(response.body() != null){
                        User user = (User) response.body();
                        Logger.d(TAG, "getUserDetails :: onResponse: "+user);
                        listener.onSuccess(user);
                    }
                    else{
                        listener.onError(new ErrorResponse());
                    }

                }
                else{
                    listener.onError(new ErrorResponse());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Logger.e(TAG, "getUserDetails :: onFailure: "+t);

            }
        };
        Call<User> call = userAPIs.getUser(userName);
        call.enqueue(callback);
    }

    public void getRepoDetails(String userName, String repoName, IResponseListener listener){
        Logger.i(TAG, "getRepoDetails :: userName: "+userName+", repoName: "+repoName);
        IRepoAPIs repoAPIs = retrofit.create(IRepoAPIs.class);

        Callback<Repo> callback = new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Logger.d(TAG, "getRepoDetails :: onResponse: "+response);
                if(response != null){
                    Logger.d(TAG, "getRepoDetails :: onResponse: "+response.body());
                    if(response.body() != null){
                        Repo repo = (Repo) response.body();
                        Logger.d(TAG, "getRepoDetails :: onResponse: "+repo);
                        listener.onSuccess(repo);
                    }
                    else{
                        listener.onError(new ErrorResponse());
                    }

                }
                else{
                    listener.onError(new ErrorResponse());
                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                listener.onError(new ErrorResponse());
            }
        };
        Call<Repo> call = repoAPIs.getRepo(userName, repoName);
        call.enqueue(callback);
    }

    public void getReposPullRequests(String userName, String repoName, int pageNumber, IResponseListener listener){
        Logger.i(TAG, "getReposPullRequests :: userName: "+userName+", repoName: "+repoName+", pageNumber: "+pageNumber);
        IPullRequestAPIs pullRequestAPIs = retrofit.create(IPullRequestAPIs.class);

        Callback<List<PullRequest>> callback = new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                Logger.d(TAG, "getReposPullRequests :: onResponse: "+response);
                if(response != null){
                    Logger.d(TAG, "getReposPullRequests :: onResponse body: "+response.body());
                    if(response.body() != null){
                        List<PullRequest> pullRequests = (List<PullRequest>) response.body();
                        Logger.d(TAG, "getReposPullRequests :: onResponse: "+pullRequests);
                        listener.onSuccess(pullRequests);
                    }
                    else{
                        listener.onError(new ErrorResponse("Something went wrong  !!"));
                    }

                }
                else{
                    listener.onError(new ErrorResponse("Please enter valid owner and repo name !!"));
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                listener.onError(new ErrorResponse("Something went wrong !!"));
            }
        };
        Call<List<PullRequest>> call = pullRequestAPIs.getAllPullRequests(userName, repoName, pageNumber);
        call.enqueue(callback);
    }

}
