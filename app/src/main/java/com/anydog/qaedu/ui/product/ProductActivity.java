package com.anydog.qaedu.ui.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.anydog.qaedu.R;
import com.anydog.qaedu.ViewModelProviderFactory;
import com.anydog.qaedu.databinding.ActivityProductBinding;
import com.anydog.qaedu.ui.base.BaseActivity;

import javax.inject.Inject;


public class ProductActivity extends BaseActivity<ActivityProductBinding, ProductViewModel> implements ProductNavigator {

    public static String ProductIdKey = "productIdKey";
    public static String JustInfoKey = "justInfo";
    @Inject
    ViewModelProviderFactory factory;

    public static Intent newIntent(Context context, String productId, boolean justInfo) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra(ProductIdKey, productId);
        intent.putExtra(JustInfoKey, justInfo);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    public ProductViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(ProductViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String productId = getIntent().getStringExtra(ProductIdKey);
        getViewDataBinding().setViewModel(getViewModel());
        if (getIntent().getBooleanExtra(JustInfoKey, false)) {
            getViewModel().setJustInfoMode();
        }

        getViewModel().setNavigator(this);
        getViewModel().fetch(productId);
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
