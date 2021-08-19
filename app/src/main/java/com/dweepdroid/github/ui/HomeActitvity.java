package com.dweepdroid.github.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dweepdroid.github.R;
import com.dweepdroid.github.common.Logger;
import com.dweepdroid.github.customview.NaviTextView;
import com.dweepdroid.github.presenter.HomePresenter;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeActitvity extends BaseActivity implements HomePresenter.HomeView {

    private static final String TAG = HomeActitvity.class.getSimpleName();

    NaviTextView userNameTv;
    NaviTextView userIdTv;
    NaviTextView repoNameTv;
    NaviTextView repoIdTv;
    NaviTextView repoCreatedAtTv;
    ImageView userAvatarImgV;
    RecyclerView prRecyclerView;
    LinearLayoutManager layoutManager;

    HomePresenter homePresenter;

    private boolean isLoading;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        homePresenter = new HomePresenter(this);

        init();

        setUserDetails();
        setRepoDetails();
        setRecyclerViewList();
    }

    private void init(){
        userNameTv = (NaviTextView) findViewById(R.id.userNameTv);
        userIdTv = (NaviTextView) findViewById(R.id.userNameId);
        userAvatarImgV = (ImageView) findViewById(R.id.userAvatarImg);

        repoNameTv = (NaviTextView) findViewById(R.id.repoNameTv);
        repoIdTv = (NaviTextView) findViewById(R.id.repoIdTv);
        repoCreatedAtTv = (NaviTextView) findViewById(R.id.repoOpenIssuesTv);

        prRecyclerView = (RecyclerView) findViewById(R.id.prRecyclerView);


    }

    private void setUserDetails(){
        userNameTv.setText(homePresenter.getUserName());
        userIdTv.setText(homePresenter.getUserId());
        String imageUrl = homePresenter.getAvatarImg();
        if(imageUrl != null){
            Picasso.with(this).load(imageUrl).into(userAvatarImgV);
        }
    }

    private void setRepoDetails(){
        repoNameTv.setText(homePresenter.getRepoName());
        repoCreatedAtTv.setText(homePresenter.getRepoCreatedAt());
        repoIdTv.setText(homePresenter.getRepoId());
    }


    private void setRecyclerViewList(){
        prRecyclerView.setAdapter(homePresenter.getAdapter());
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        prRecyclerView.setLayoutManager(layoutManager);
        prRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        isLoading = true;
                        Logger.d(TAG, "onScroll , load more");
                        homePresenter.loadMore();
                    }
                }
            }
        );

    }

    @Override
    public void setLoading(boolean flag) {
        isLoading = flag;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
