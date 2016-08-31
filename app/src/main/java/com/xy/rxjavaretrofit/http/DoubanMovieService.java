package com.xy.rxjavaretrofit.http;

import com.xy.rxjavaretrofit.model.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xingyun on 2016/8/31.
 * 豆瓣电影api
 * https://api.douban.com/v2/movie/
 */
public interface DoubanMovieService {

    /**
     * 获取豆瓣电影top250
     * @param start
     * @param count
     * @return
     */
    @GET("top250")
    Call<MovieEntity> getMovieTop250(@Query("start") int start, @Query("count") int count);

    @GET("coming_soon")
    Observable<MovieEntity> getComingMovie();
}
