package com.ratnesh.web_crawler.controller;


import com.ratnesh.web_crawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @GetMapping("/start-crawl")
    public String startCrawl(@RequestParam String url) {
        webCrawlerService.crawl(url);
        return "Crawling started for " + url;
    }
}
