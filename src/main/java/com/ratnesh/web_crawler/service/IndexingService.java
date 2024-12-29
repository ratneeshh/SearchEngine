package com.ratnesh.web_crawler.service;

import com.ratnesh.web_crawler.model.Indexing;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IndexingService {

    public Indexing indexPage(Document document, String url) {
        // Collect title
        String title = document.title();
        if (title == null || title.isEmpty()) {
            title = "No Title";
        }

        // Collect description
        String description = "";
        Element metaDescription = document.selectFirst("meta[name=description]");
        if (metaDescription != null) {
            description = metaDescription.attr("content");
        } else {
            String textContent = document.text();
            description = textContent.length() > 200 ? textContent.substring(0, 200) + "..." : textContent;
        }

        // Extract words
        String textContent = document.text().toLowerCase();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(textContent);
        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            if (word.chars().allMatch(Character::isAlphabetic)) {
                words.add(word);
            }
        }

        // Create the indexed page
        Indexing indexedPage = new Indexing(url, title, description, words);

        System.out.println("Indexed: " + url + ". \nHere's the info: \nTitle: " + title + "\nDescription: " + description + "\nNumber of words: " + words.size() + "\n");

        return indexedPage;
    }
}
