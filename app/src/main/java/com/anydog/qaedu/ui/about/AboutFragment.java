package com.anydog.qaedu.ui.about;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.anydog.qaedu.R;
import com.anydog.qaedu.ViewModelProviderFactory;
import com.anydog.qaedu.databinding.FragmentAboutBinding;
import com.anydog.qaedu.di.component.AppComponent;
import com.anydog.qaedu.ui.base.BaseFragment;

import javax.inject.Inject;

public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> implements AboutNavigator {

    public static final String TAG = AboutFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private AboutViewModel mAboutViewModel;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void performDependencyInjection(AppComponent component) {
        component.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public AboutViewModel getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this, factory).get(AboutViewModel.class);
        return mAboutViewModel;
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAboutViewModel.setNavigator(this);
        getViewDataBinding().setViewModel(getViewModel());
    }
}
