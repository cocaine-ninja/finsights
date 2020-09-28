package com.kingsmen.finsights.dao;

public class News {
    private String heading;
    private String details;

    public News() {
        this.heading = "";
        this.details = "";
    }

    public News(String heading, String details) {
        this.heading = heading;
        this.details = details;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
