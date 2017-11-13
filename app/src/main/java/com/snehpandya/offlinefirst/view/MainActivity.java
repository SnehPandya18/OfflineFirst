package com.snehpandya.offlinefirst.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.snehpandya.offlinefirst.R;
import com.snehpandya.offlinefirst.adapter.RecyclerAdapter;
import com.snehpandya.offlinefirst.databinding.ActivityMainBinding;
import com.snehpandya.offlinefirst.viewmodel.MainActivityViewModel;

    //TODO: 01. Add required dependencies in app level `build.gradle`
    //TODO: 02. Add INTERNET permission in `AndroidManifest.xml`

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;
    private MainActivityViewModel mMainActivityViewModel;

    private RecyclerAdapter mRecyclerAdapter;

    private DividerItemDecoration mDividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.getListLiveData().observe(this, results -> mRecyclerAdapter.addUsers(results));

        mRecyclerAdapter = new RecyclerAdapter();
        mActivityMainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mActivityMainBinding.recyclerview.setAdapter(mRecyclerAdapter);

        mDividerItemDecoration = new DividerItemDecoration(mActivityMainBinding.recyclerview.getContext(), LinearLayoutManager.VERTICAL);
        mActivityMainBinding.recyclerview.addItemDecoration(mDividerItemDecoration);
    }
}
