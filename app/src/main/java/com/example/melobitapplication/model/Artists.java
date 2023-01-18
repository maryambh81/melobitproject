package com.example.melobitapplication.model;

public class Artists
{
    String id;
    int followersCount;
    String fullName;
    String type;
    String sumSongsDownloadsCount;
    Image image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSumSongsDownloadsCount() {
        return sumSongsDownloadsCount;
    }

    public void setSumSongsDownloadsCount(String sumSongsDownloadsCount) {
        this.sumSongsDownloadsCount = sumSongsDownloadsCount;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

