package com.xy.rxjavaretrofit.model;

/**
 * Created by xingyun on 2016/8/30.
 * 取部分字段做测试https://api.github.com/users/zhou411424
 */
public class User {
    public String login;
    public String id;
    public String repos_url;
    public String followers;
    public String created_at;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id='" + id + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", followers='" + followers + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    /*{
        "login": "zhou411424",
            "id": 2360195,
            "avatar_url": "https://avatars.githubusercontent.com/u/2360195?v=3",
            "gravatar_id": "",
            "url": "https://api.github.com/users/zhou411424",
            "html_url": "https://github.com/zhou411424",
            "followers_url": "https://api.github.com/users/zhou411424/followers",
            "following_url": "https://api.github.com/users/zhou411424/following{/other_user}",
            "gists_url": "https://api.github.com/users/zhou411424/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/zhou411424/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/zhou411424/subscriptions",
            "organizations_url": "https://api.github.com/users/zhou411424/orgs",
            "repos_url": "https://api.github.com/users/zhou411424/repos",
            "events_url": "https://api.github.com/users/zhou411424/events{/privacy}",
            "received_events_url": "https://api.github.com/users/zhou411424/received_events",
            "type": "User",
            "site_admin": false,
            "name": null,
            "company": null,
            "blog": null,
            "location": null,
            "email": null,
            "hireable": null,
            "bio": null,
            "public_repos": 122,
            "public_gists": 0,
            "followers": 2,
            "following": 1,
            "created_at": "2012-09-17T05:31:51Z",
            "updated_at": "2016-08-09T11:13:36Z"
    }*/
}
