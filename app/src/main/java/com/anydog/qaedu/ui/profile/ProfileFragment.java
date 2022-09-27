package com.anydog.qaedu.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.anydog.qaedu.R;
import com.anydog.qaedu.ViewModelProviderFactory;
import com.anydog.qaedu.databinding.FragmentProfileBinding;
import com.anydog.qaedu.di.component.AppComponent;
import com.anydog.qaedu.ui.base.BaseFragment;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> implements ProfileNavigator {

    public static final String TAG = ProfileFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private ProfileViewModel viewModel;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public ProfileViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        return viewModel;
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void getImage() {
        CropImage.activity().start(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri uri = result.getUri();
                GetFile(uri);
            }
//            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
        }
    }

    private void GetFile(Uri fileUri) {
        String str = fileUri.getPath();
        File file = new File(str);
        viewModel.updateProfileImage(file);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().setViewModel(getViewModel());
        viewModel.setNavigator(this);
        viewModel.fetch();
    }

    @Override
    public void changeName() {

    }

    @Override
    protected void performDependencyInjection(AppComponent component) {
        component.inject(this);
    }
}