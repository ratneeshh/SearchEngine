package com.ratnesh.web_crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class WebCrawlerService {

    private static final int CRAWL_LIMIT = 20;

    public List<Map.Entry<String, Document>> crawl(String startUrl) throws IOException {
        Queue<String> urlsToCrawl = new LinkedList<>();
        Set<String> visitedUrls = new HashSet<>();
        List<Map.Entry<String, Document>> crawledDocuments = new LinkedList<>();

        urlsToCrawl.add(startUrl);
        visitedUrls.add(startUrl);

        while (!urlsToCrawl.isEmpty() && crawledDocuments.size() < CRAWL_LIMIT) {
            String currentUrl = urlsToCrawl.poll();
            System.out.println("Crawling: " + currentUrl);

            try {
                Document document = Jsoup.connect(currentUrl).get();
                crawledDocuments.add(new AbstractMap.SimpleEntry<>(currentUrl, document));

                // Extract hyperlinks
                document.select("a[href]").stream()
                        .map(link -> link.attr("abs:href"))
                        .filter(link -> !link.isEmpty() && !visitedUrls.contains(link))
                        .forEach(link -> {
                            urlsToCrawl.add(link);
                            visitedUrls.add(link);
                        });

            } catch (IOException e) {
                System.out.println("Failed to retrieve " + currentUrl + ": " + e.getMessage());
            }
        }

        return crawledDocuments;
    }
}
