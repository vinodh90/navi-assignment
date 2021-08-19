package com.dweepdroid.github.common;

import com.dweepdroid.github.model.ErrorResponse;
import com.dweepdroid.github.model.IResponseListener;
import com.dweepdroid.github.model.PullRequest;
import com.dweepdroid.github.model.Repo;
import com.dweepdroid.github.model.User;
import com.dweepdroid.github.network.NetworkController;

import java.util.List;

public class BusinessController {

    private static final String TAG = BusinessController.class.getSimpleName();


    private static BusinessController businessController;

    private static User user;

    private static Repo repo;

    private static List<PullRequest> pullRequestList;

    private static int currentPageNumber = 1;

    private BusinessController(){

    }

    public static BusinessController getInstance(){
        if(businessController == null){
            synchronized (BusinessController.class){
                if(businessController == null){
                    businessController = new BusinessController();
                }
            }
        }

        return businessController;
    }

    public User getUser(){
        return user;
    }

    public Repo getRepo(){
        return repo;
    }

    public List<PullRequest> getPrList(){
        return pullRequestList;
    }

    public static List<PullRequest> getPullRequestList(){
        return pullRequestList;
    }

    public void getUserDetails(String userName, IResponseListener listener){
        resetData();
        NetworkController.getInstance().getUserDetails(userName, new IResponseListener<User>(){
            @Override
            public void onSuccess(User userObj) {
                if(userObj != null){
                    Logger.d(TAG, "getUserDetails: onSuccess: userObj: "+userObj);
                    user = userObj;
                    listener.onSuccess("User is available");

                }
                else{
                    listener.onError(new ErrorResponse("User is empty"));
                    user = null;
                }
            }

            @Override
            public void onError(ErrorResponse value) {
                listener.onError(new ErrorResponse(value.getMessage()));
            }
        });
    }

    public void getRepoDetails(String userName, String repoName, IResponseListener listener){
        resetData();
        NetworkController.getInstance().getRepoDetails(userName, repoName, new IResponseListener<Repo>() {
            @Override
            public void onSuccess(Repo repoObj) {
                repo = repoObj;
                listener.onSuccess("Repo is available");
            }

            @Override
            public void onError(ErrorResponse value) {
                listener.onError(new ErrorResponse(value.getMessage()));
            }
        });
    }

    public void getPullRequests(String userName, String repoName, boolean isLoadingMore, IResponseListener listener){
        if(isLoadingMore){
            incrementPage();
        }
        else {
            resetData();
        }
        NetworkController.getInstance().getReposPullRequests(userName, repoName, currentPageNumber, new IResponseListener<List<PullRequest>>() {
            @Override
            public void onSuccess(List<PullRequest> pullRequestsObj) {
                Logger.d(TAG, "pullRequests count = "+pullRequestsObj.size());
                if(pullRequestsObj.size() > 0){
                    PullRequest firstPr = pullRequestsObj.get(0);
                    if(firstPr != null) {
                        if(firstPr.getBase() != null) {
                            user = firstPr.getBase().getUser();
                            repo = firstPr.getBase().getRepo();
                        }
                    }
                    if(isLoadingMore){
                        pullRequestList.addAll(pullRequestsObj);
                    }
                    else {
                        pullRequestList = pullRequestsObj;
                    }
                    listener.onSuccess("Success");
                }
                else{
                    listener.onError(new ErrorResponse("Reached end of list !!"));
                }
            }

            @Override
            public void onError(ErrorResponse value) {
                decrementPage();
                listener.onError(new ErrorResponse("Please enter valid owner and repo name"));
            }
        });
    }

    public void resetData(){
        user = null;
        repo = null;
        pullRequestList = null;
        currentPageNumber = 1;
    }

    public void incrementPage(){
        currentPageNumber++;
    }

    public void decrementPage(){
        currentPageNumber--;
    }
}
