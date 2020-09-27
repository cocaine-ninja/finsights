package com.kingsmen.finsights.dao;

public class Offer {
    private String header;
    private String description;
    private String type;

    public Offer() {
        this.header = "";
        this.description = "";
        this.type = "";
    }

    public Offer(String header, String description, String type) {
        this.header = header;
        this.description = description;
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
