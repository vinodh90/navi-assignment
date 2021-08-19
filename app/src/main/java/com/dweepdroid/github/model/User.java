package com.dweepdroid.github.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{

    @SerializedName("login")
    public String login;

    @SerializedName("id")
    public int id;
    public String node_id;

    @SerializedName("avatar_url")
    public String avatarUrl;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
    public Object name;
    public Object company;
    public String blog;
    public Object location;
    public Object email;
    public Object hireable;
    public Object bio;
    public Object twitter_username;

    @SerializedName("public_repos")
    public int publicRepos;
    public int public_gists;
    public int followers;
    public int following;

    @SerializedName("created_at")
    public Date createdAt;

    @SerializedName("updated_at")
    public Date updatedAt;


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", node_id='" + node_id + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", name=" + name +
                ", email=" + email +
                ", publicRepos=" + publicRepos +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getNode_id() {
        return node_id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public String getType() {
        return type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public Object getName() {
        return name;
    }

    public Object getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public Object getLocation() {
        return location;
    }

    public Object getEmail() {
        return email;
    }

    public Object getHireable() {
        return hireable;
    }

    public Object getBio() {
        return bio;
    }

    public Object getTwitter_username() {
        return twitter_username;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
