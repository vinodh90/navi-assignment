package com.dweepdroid.github.presenter;

import android.content.Context;

import com.dweepdroid.github.common.BusinessController;
import com.dweepdroid.github.model.ErrorResponse;
import com.dweepdroid.github.model.IResponseListener;
import com.dweepdroid.github.ui.HomeActitvity;
import com.dweepdroid.github.utils.DisplayUtility;

public class LoginPresenter {

    LoginView view;

    Context activityContext;

    public LoginPresenter(LoginView view, Context context){
        this.view = view;
        activityContext = context;
    }

    public void continueClicked(){
        String repoName = view.getRepoName().toString();
        String ownerName = view.getOwnername().toString();
        if(repoName == null || repoName.length() <= 0){
            view.showToast("Please enter valid Repo Name");
        }
        else if(ownerName == null || ownerName.length() <= 0){
            view.showToast("Please enter valid Owner Name");
        }
        else{
            DisplayUtility.getInstance().displayProgressDialog(activityContext, "Fetching", "Please wait...");
            BusinessController.getInstance().getPullRequests(ownerName, repoName, false, new IResponseListener() {
                @Override
                public void onSuccess(Object value) {
                    DisplayUtility.getInstance().hideProgressDialog();
                    view.launchNextAcitvity(HomeActitvity.class);
                }

                @Override
                public void onError(ErrorResponse value) {
                    DisplayUtility.getInstance().hideProgressDialog();
                    view.showToast(value.getMessage());
                }
            });
        }

    }

    public void setContext(Context context){

    }

    public interface LoginView{
        CharSequence getRepoName();
        CharSequence getOwnername();
        void showToast(String message);
        void launchNextAcitvity(Class className);
    }
}
