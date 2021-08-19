package com.dweepdroid.github.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Repo implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("full_name")
    public String fullName;

    @SerializedName("owner")
    public User owner;

    @SerializedName("open_issues_count")
    public int openIssues;

    @SerializedName("created_at")
    public Date createdAt;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public User getOwner() {
        return owner;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", owner=" + owner +
                ", openIssues=" + openIssues +
                ", createdAt=" + createdAt +
                '}';
    }
}
