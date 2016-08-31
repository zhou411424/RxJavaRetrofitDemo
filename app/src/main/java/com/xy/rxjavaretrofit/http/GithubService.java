package com.xy.rxjavaretrofit.http;

import com.xy.rxjavaretrofit.model.Repo;
import com.xy.rxjavaretrofit.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xingyun on 2016/8/30.
 */
public interface GithubService {
    // 获取用户信息https://api.github.com/users/zhou411424
    @GET("users/{user}")
    Call<User> userInfo(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
