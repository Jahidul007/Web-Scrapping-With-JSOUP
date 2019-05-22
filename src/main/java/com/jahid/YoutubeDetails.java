package com.jahid;

import java.util.Objects;

public class YoutubeDetails {

    int id;
    String title;
    String link;
    String noOfView;
    String channelName;
    String pubDate;

    public YoutubeDetails(int id, String title, String link, String noOfView, String channelName, String pubDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.noOfView = noOfView;
        this.channelName = channelName;
        this.pubDate = pubDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNoOfView() {
        return noOfView;
    }

    public void setNoOfView(String noOfView) {
        this.noOfView = noOfView;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeDetails that = (YoutubeDetails) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(link, that.link) &&
                Objects.equals(noOfView, that.noOfView) &&
                Objects.equals(channelName, that.channelName) &&
                Objects.equals(pubDate, that.pubDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, noOfView, channelName, pubDate);
    }
}
