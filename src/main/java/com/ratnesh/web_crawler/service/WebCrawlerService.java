package com.ratnesh.web_crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

@Service
public class WebCrawlerService {

    private static final int CRAWL_LIMIT = 20;

    public void crawl(String startUrl) {
        Set<String> visitedUrls = new HashSet<>();
        Queue<String> urlQueue = new LinkedList<>();
        urlQueue.add(startUrl);

        int currentCrawlCount = 0;
        Random random = new Random();

        while (!urlQueue.isEmpty() && currentCrawlCount < CRAWL_LIMIT) {
            String currentUrl = urlQueue.poll();
            System.out.println("Crawling: " + currentUrl);


            try {
                Thread.sleep(1000 + random.nextInt(2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            try {
                Document document = fetchDocument(currentUrl);
                if (document != null) {
                    Elements links = document.select("a[href]");
                    for (Element link : links) {
                        String url = formatUrl(link.attr("href"), currentUrl);
                        if (url != null && !visitedUrls.contains(url)) {
                            urlQueue.add(url);
                            visitedUrls.add(url);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to retrieve " + currentUrl + ": " + e.getMessage());
            }

            currentCrawlCount++;
        }
    }

    private Document fetchDocument(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try {
            if (connection.getResponseCode() == 200) {
                return Jsoup.parse(connection.getInputStream(), null, urlString);
            } else {
                System.err.println("Failed to fetch " + urlString + ": HTTP " + connection.getResponseCode());
            }
        } finally {
            connection.disconnect();
        }
        return null;
    }

    private String formatUrl(String url, String currentUrl) {
        if (url.startsWith("#")) {
            return null;
        }
        if (url.startsWith("//")) {
            return "https:" + url;
        }
        if (url.startsWith("/")) {
            return currentUrl.split("/")[0] + "//" + currentUrl.split("/")[2] + url;
        }
        if (!url.startsWith("http")) {
            return null;
        }
        return url.split("#")[0];
    }
}
