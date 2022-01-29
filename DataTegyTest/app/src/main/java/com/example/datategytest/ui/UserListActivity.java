package com.example.datategytest.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.adapters.ToolbarBindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.datategytest.application.BaseApplication;
import com.example.datategytest.databinding.ActivityMainBinding;
import com.example.datategytest.di.components.DaggerActivityComponent;
import com.example.datategytest.di.modules.MvpModule;
import com.example.datategytest.presentation.userlist.UserListPresenter;
import com.example.datategytest.presentation.userlist.UserListViewModel;
import com.example.datategytest.ui.listAdapter.UserDataListAdapter;

import javax.inject.Inject;

public class UserListActivity extends AppCompatActivity implements UserListViewModel {


    ProgressDialog progressDialog;
    private ActivityMainBinding binding;
    @Inject
    UserListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DaggerActivityComponent.builder()
                .appComponent(BaseApplication.get(this).component())
                .mvpModule(new MvpModule(this))
                .build()
                .inject(this);


        UserDataListAdapter adapter = new UserDataListAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listPresenter.getAllUsers().observe(this, userDataList -> {
            adapter.updateData(userDataList);
            binding.recyclerView.setAdapter(adapter);
        });

    }

    @Override
    public void showProgress() {
        if (progressDialog == null)
            progressDialog = new ProgressDialog(this);
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
        Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
    }
}