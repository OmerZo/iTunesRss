package com.learn.zohky.itunesrss;



public class Song {
    private String mArtistName;
    private int mId;
    private String mReleaseDate;
    private String mName;
    private String mArtworkUrl;

    public Song(String artistName, int id, String releaseDate, String name, String artworkUrl) {
        this.mArtistName = artistName;
        this.mId = id;
        this.mReleaseDate = releaseDate;
        this.mName = name;
        this.mArtworkUrl = artworkUrl;
    }

    public String getmArtistName() {
        return mArtistName;
    }

    public void setmArtistName(String artistName) {
        this.mArtistName = artistName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int id) {
        this.mId = id;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        this.mName = name;
    }

    public String getmArtworkUrl() {
        return mArtworkUrl;
    }

    public void setmArtworkUrl(String artworkUrl) {
        this.mArtworkUrl = artworkUrl;
    }
}
