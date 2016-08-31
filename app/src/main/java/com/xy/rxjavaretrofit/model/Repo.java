package com.xy.rxjavaretrofit.model;

/**
 * Created by xingyun on 2016/8/30.
 */
public class Repo {
    String id;
    String name;
    String full_name;
    String html_url;
    String description;
    boolean fork;
    String forks_count;

    @Override
    public String toString() {
        return "Repo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", html_url='" + html_url + '\'' +
                ", description='" + description + '\'' +
                ", fork=" + fork +
                ", forks_count='" + forks_count + '\'' +
                '}';
    }
}
