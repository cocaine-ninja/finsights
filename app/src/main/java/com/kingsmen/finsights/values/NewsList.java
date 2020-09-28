package com.kingsmen.finsights.values;

import com.kingsmen.finsights.dao.News;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    private List<News> newsList;

    public NewsList() {
        this.newsList = new ArrayList<>();
    }

    public NewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public void init() {
        News news1 = new News();
        news1.setHeading("U.S. Stocks Join in Global Rally");
        news1.setDetails("U.S. stocks climbed after four weeks of declines and Europe shares added the most in three weeks as banks rebounded. The dollar weakened.");

        News news2 = new News();
        news2.setHeading("MPC Meeting To Be Rescheduled, Says RBI");
        news2.setDetails("A meeting of India’s monetary policy committee, scheduled for this week, has been rescheduled.");

        newsList.add(news1);
        newsList.add(news2);
    }
}
