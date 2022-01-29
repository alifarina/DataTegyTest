package com.example.datategytest.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.datategytest.R;
import com.example.datategytest.application.BaseApplication;
import com.example.datategytest.databinding.UserDetailLayoutBinding;
import com.example.datategytest.di.components.DaggerActivityComponent;
import com.example.datategytest.di.modules.MvpModule;
import com.example.datategytest.presentation.userDetail.UserDetailViewModel;
import com.example.datategytest.presentation.userDetail.UserDetailsPresenter;
import com.example.datategytest.utils.Constants;

import javax.inject.Inject;

public class UserDetailActivity extends AppCompatActivity implements UserDetailViewModel {

    private String userId = "";
    ProgressDialog progressDialog;

    @Inject
    UserDetailsPresenter presenter;

    private UserDetailLayoutBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserDetailLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DaggerActivityComponent.builder()
                .appComponent(BaseApplication.get(this).component())
                .mvpModule(new MvpModule(this))
                .build()
                .inject(this);


        if (getIntent() != null) {
            userId = getIntent().getStringExtra(Constants.USER_ID);
            presenter.getUserDetails(userId);
        }

        presenter.getUserProfile().observe(this, profile -> {
            Log.d("profile", profile.toString());
            binding.detailLayout.setVisibility(View.VISIBLE);
            binding.emptyTv.setVisibility(View.GONE);
            Glide.with(this)
                    .load(profile.getPicture())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.profileImage);
            binding.firstName.setText("First Name : " + profile.getTitle() + " " + profile.getFirstName());
            binding.lastName.setText("Last Name : " + profile.getLastName());
            binding.gender.setText("Gender : " + profile.getGender());
            binding.email.setText("Email : " + profile.getEmail());
            binding.phone.setText("Phone : " + profile.getPhone());
            binding.dob.setText("Date of birth : " + profile.getDateOfBirth());
            binding.location.setText("Address : " + profile.getLocation().getCity() +
                    "," + profile.getLocation().getState() + "," + profile.getLocation().getCountry());
        });

    }

    @Override
    public void showProgress() {
        if (progressDialog == null) progressDialog = new ProgressDialog(this);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.hide();
    }


    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void noDataFound() {
        binding.detailLayout.setVisibility(View.GONE);
        binding.emptyTv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressDialog.dismiss();
    }
}
