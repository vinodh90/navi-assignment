package com.dweepdroid.github.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class PullRequest implements Serializable {

    @SerializedName("id")
    long id;

    @SerializedName("title")
    String title;

    @SerializedName("number")
    long number;

    @SerializedName("body")
    String body;

    @SerializedName("created_at")
    Date createdAt;

    @SerializedName("closed_at")
    Date closedAt;

    @SerializedName("user")
    User user;

    @SerializedName("base")
    Base base;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Base getBase() {
        return base;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public class Base implements Serializable{

        @SerializedName("repo")
        Repo repo;

        @SerializedName("user")
        User user;

        public Repo getRepo() {
            return repo;
        }

        public User getUser() {
            return user;
        }

        @Override
        public String toString() {
            return "Base{" +
                    "repo=" + repo +
                    ", user=" + user +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PullRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", closedAt=" + closedAt +
                ", user=" + user +
                ", base=" + base +
                '}';
    }
}

