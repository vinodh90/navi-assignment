package com.dweepdroid.github.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.dweepdroid.github.common.BusinessController;
import com.dweepdroid.github.model.ErrorResponse;
import com.dweepdroid.github.model.IResponseListener;
import com.dweepdroid.github.model.Repo;
import com.dweepdroid.github.model.User;
import com.dweepdroid.github.ui.PrRecyclerAdapter;

public class HomePresenter {



    HomeView view;
    BusinessController businessController;
    User user;
    Repo repo;
    PrRecyclerAdapter adapter;

    public HomePresenter(HomeView view){
        this.view = view;
        businessController = BusinessController.getInstance();
        user = businessController.getUser();
        repo = businessController.getRepo();
    }

    public String getUserName(){
        if(user != null && user.getLogin() != null){
            return user.getLogin();
        }
        return "NA";
    }

    public String getUserId(){
        if(user != null && user.getId() > 0){
            return String.valueOf(user.getId());
        }
        return "NA";
    }

    public String getAvatarImg(){
        if(user != null && user.getAvatarUrl() != null){
            return user.getAvatarUrl();
        }
        return null;
    }

    public String getUserCreatedAt(){
        if(user != null){
            return "Created At : "+user.getCreatedAt();
        }
        return "Created At : NA";
    }

    public String getRepoName(){
        if(repo != null && repo.getName() != null){
            return "Repo Name : "+repo.getName();
        }
        return "Repo Name : NA";
    }

    public String getRepoId(){
        if(repo != null){
            return "Repo Id : "+repo.getId();
        }
        return "Repo Id : NA";
    }

    public String getRepoCreatedAt(){
        if(repo != null){
            return "Repo Created At : "+repo.getCreatedAt();
        }
        return "Repo Created At : NA";
    }

    public RecyclerView.Adapter getAdapter(){
        adapter = new PrRecyclerAdapter(businessController.getPrList());
        return adapter;
    }

    public void loadMore(){
        if(user != null && repo != null) {
            businessController.getPullRequests(user.getLogin(), repo.getName(), true, new IResponseListener() {
                @Override
                public void onSuccess(Object value) {
                    if(adapter != null){
                        adapter.updateList(businessController.getPrList());
                    }
                    view.setLoading(false);
                }

                @Override
                public void onError(ErrorResponse value) {
                    view.setLoading(false);
                    //view.showToast(value.getMessage());
                }
            });
        }
    }

    public interface HomeView{
        void setLoading(boolean flag);
        void showToast(String message);
    }
}
