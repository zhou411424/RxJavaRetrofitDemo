package com.xy.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.xy.rxjavaretrofit.http.ApiFactory;
import com.xy.rxjavaretrofit.http.DoubanMovieService;
import com.xy.rxjavaretrofit.http.GithubService;
import com.xy.rxjavaretrofit.model.MovieEntity;
import com.xy.rxjavaretrofit.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.merge_btn) Button mergeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.merge_btn) public void onClick() {
        Log.d(TAG, "onClick");
//        getUserInfo();
//        getMovieTop250();
        getComingMovie();
    }

    /**
     * 使用retrofit和rxjava
     */
    public void getComingMovie() {
        DoubanMovieService doubanMovieService = ApiFactory.getDoubanMovieService(this);
        doubanMovieService.getComingMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "getComingMovie==>onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getComingMovie==>onError...err msg: " + e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        Log.d(TAG, "getComingMovie==>onNext...moveEntity"+movieEntity.toString());
                    }
                });
    }

    public void getMovieTop250() {
        DoubanMovieService doubanMovieService = ApiFactory.getDoubanMovieService(this);
        Call<MovieEntity> movieEntityCall = doubanMovieService.getMovieTop250(0, 10);
        movieEntityCall.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                MovieEntity movieEntity = response.body();
                Log.d(TAG, "onResponse==>movieEntity="+movieEntity.toString());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Log.d(TAG, "onFailure==>");
            }
        });
    }

    /**
     * 单纯使用retrofit
     */
    public void getUserInfo() {
        GithubService githubService = ApiFactory.getGithubService(this);
        Call<User> userCall = githubService.userInfo("zhou411424");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse==>"+", thread name="+Thread.currentThread().getName()+", thread id="+Thread.currentThread().getId());
                User user = response.body();
                Log.d(TAG, "onResponse==>"+user != null ? "user info="+user.toString() : "user == null");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure==>");
                if (call.isCanceled()) {
                    Log.d(TAG, "onFailure==>call is canceled...");
                } else {
                    Log.e(TAG, "onFailure==>err msg: " + t.getMessage());
                }
            }
        });
    }
}
