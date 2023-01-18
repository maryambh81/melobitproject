package com.example.melobitapplication.model;

public class Image
{
    ThumbnailSmall thumbnail_small;
    Thumbnail thumbnail;
    CoverSmall coverSmall;
    Cover cover;

    public ThumbnailSmall getThumbnail_small() {
        return thumbnail_small;
    }

    public void setThumbnail_small(ThumbnailSmall thumbnailSmall) {
        this.thumbnail_small = thumbnailSmall;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CoverSmall getCover_small() {
        return coverSmall;
    }

    public void setCover_small(CoverSmall coverSmall) {
        this.coverSmall = coverSmall;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }
}
