package com.xy.rxjavaretrofit.http;

import android.content.Context;

/**
 * Created by xingyun on 2016/8/30.
 */
public class ApiFactory {

    private static GithubService githubService;
    private static DoubanMovieService doubanMovieService;

    public static GithubService getGithubService(Context context) {
        if (githubService == null) {
            githubService = new RetrofitClient(context).getRetrofit().create(GithubService.class);
        }
        return githubService;
    }

    public static DoubanMovieService getDoubanMovieService(Context context) {
        if (doubanMovieService == null) {
            doubanMovieService = new RetrofitClient(context).getRetrofit().create(DoubanMovieService.class);
        }
        return doubanMovieService;
    }
}
