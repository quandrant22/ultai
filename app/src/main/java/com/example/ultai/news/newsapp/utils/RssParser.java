package com.example.ultai.news.newsapp.utils;

import com.example.ultai.news.newsapp.model.NewsItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssParser {

    public static List<NewsItem> fetchNews(String rssUrl) {
        List<NewsItem> newsList = new ArrayList<>();
        try {
            URL url = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));

            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setTitle(entry.getTitle());
                newsItem.setDescription(entry.getDescription() != null ? entry.getDescription().getValue() : "No description");
                newsItem.setImageUrl(getImageUrlFromContent(entry));
                newsItem.setSource(feed.getTitle());
                newsList.add(newsItem);
            }
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    private static String getImageUrlFromContent(SyndEntry entry) {
        if (entry.getContents() != null && !entry.getContents().isEmpty()) {
            String content = entry.getContents().get(0).getValue();
            if (content.contains("src=")) {
                int start = content.indexOf("src=\"") + 5;
                int end = content.indexOf("\"", start);
                return content.substring(start, end);
            }
        }
        return "default_image_url";
    }
}