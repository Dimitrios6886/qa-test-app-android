package com.anydog.qaedu.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.anydog.qaedu.R;
import com.anydog.qaedu.ViewModelProviderFactory;
import com.anydog.qaedu.databinding.ActivitySplashBinding;
import com.anydog.qaedu.ui.base.BaseActivity;
import com.anydog.qaedu.ui.feed.FeedActivity;
import com.anydog.qaedu.ui.login.LoginActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator, HasAndroidInjector {

    @Inject
    ViewModelProviderFactory factory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return  ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = FeedActivity.newIntent(SplashActivity.this);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        getViewModel().setNavigator(this);
        getViewModel().startSeeding();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 4);

    }

    @Inject
    public DispatchingAndroidInjector<Object> injector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return injector;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
