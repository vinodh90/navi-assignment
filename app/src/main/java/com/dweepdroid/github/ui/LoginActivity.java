package com.dweepdroid.github.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dweepdroid.github.R;
import com.dweepdroid.github.customview.NaviButton;
import com.dweepdroid.github.customview.NaviEditText;
import com.dweepdroid.github.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginPresenter.LoginView {

    NaviEditText repoNameEt;
    NaviEditText ownerNameEt;
    NaviButton   continueBtn;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        init();
        setListeners();
    }

    private void init(){
        repoNameEt = (NaviEditText) findViewById(R.id.repoNameEt);
        ownerNameEt = (NaviEditText) findViewById(R.id.ownerNameEt);
        continueBtn = (NaviButton) findViewById(R.id.continueBtn);

        loginPresenter = new LoginPresenter(this, this);
    }

    private void setListeners(){
        continueBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.continueBtn:
                loginPresenter.continueClicked();
                break;
            default:
                break;
        }
    }

    @Override
    public CharSequence getRepoName() {
        return repoNameEt.getText();
    }

    @Override
    public CharSequence getOwnername() {
        return ownerNameEt.getText();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchNextAcitvity(Class className) {
        startActivity(new Intent(this, className));
    }
}
