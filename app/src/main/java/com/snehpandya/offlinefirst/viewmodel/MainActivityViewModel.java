package com.snehpandya.offlinefirst.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.snehpandya.offlinefirst.database.AppDatabase;
import com.snehpandya.offlinefirst.database.Result;
import com.snehpandya.offlinefirst.model.Response;
import com.snehpandya.offlinefirst.service.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sneh.pandya on 10/11/17.
 */

//TODO: 06. Setup ViewModel

public class MainActivityViewModel extends AndroidViewModel {

    private static final String EXCLUDE_PARAMS = "location,login,registered,phone,cell,id,email,dob";

    private final LiveData<List<Result>> mListLiveData;
    private AppDatabase mAppDatabase;
    private List<com.snehpandya.offlinefirst.model.Result> mResults = new ArrayList<>();
    private Result mResult;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        mAppDatabase = AppDatabase.getInstance(this.getApplication());

        mListLiveData = mAppDatabase.mCommonDao().getAllUsers();

        callAPI();
    }

    public LiveData<List<Result>> getListLiveData() {
        return mListLiveData;
    }

    private void addUserToDatabase(Response response) {
        mResults.addAll(response.getResults());
        for (int i = 0; i < mResults.size(); i++) {
            mResult = new Result(mResults.get(i).getName().getTitle(),
                    mResults.get(i).getName().getFirst(),
                    mResults.get(i).getName().getLast(),
                    mResults.get(i).getGender(),
                    mResults.get(i).getPicture().getLarge(),
                    mResults.get(i).getNat());
            mAppDatabase.mCommonDao().addUser(mResult);
        }
    }

    public void callAPI() {
        RetrofitAPI retrofitAPI = new RetrofitAPI();
        Observable<Response> responseObservable = retrofitAPI.mRandomUserAPI.getRandomUsers(20, EXCLUDE_PARAMS, "recker");
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addUserToDatabase, this::handleError);
    }

    private void handleError(Throwable throwable) {
        Log.e("TAG", "handleError: " + throwable);
        Toast.makeText(getApplication(), "Error!", Toast.LENGTH_SHORT).show();
    }
}
