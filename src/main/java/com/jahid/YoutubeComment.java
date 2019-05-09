package com.jahid;

import java.util.ArrayList;
import java.util.Objects;

public class YoutubeComment {

    int commentId;
    ArrayList<String> comments;

    public YoutubeComment() {
    }

    public YoutubeComment(int commentId, ArrayList<String> comments) {
        this.commentId = commentId;
        this.comments = comments;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeComment that = (YoutubeComment) o;
        return commentId == that.commentId &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comments);
    }
}
