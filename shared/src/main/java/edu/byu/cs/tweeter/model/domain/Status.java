package edu.byu.cs.tweeter.model.domain;

public class Status {
    private User user;
    private String statusStr;
    private String datePosted;


    private Status() {}

    public Status(User user, String statusStr, String date) {
        this.user = user;
        this.statusStr = statusStr;
        this.datePosted = date;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public User getUser() {
        return user;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
}
