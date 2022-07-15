package com.example.ghRepos;

import java.util.Date;

public class GHRepository {
    private String fullName;
    private String description;
    private String clone_url;
    private int stars;
    private String createdAt;

    public GHRepository(String fullName, String description, String clone_url, int stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.clone_url = clone_url;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
