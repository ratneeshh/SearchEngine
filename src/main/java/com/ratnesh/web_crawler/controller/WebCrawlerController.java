package com.ratnesh.web_crawler.controller;


import com.ratnesh.web_crawler.model.Indexing;
import com.ratnesh.web_crawler.service.IndexingService;
import com.ratnesh.web_crawler.service.WebCrawlerService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class WebCrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @Autowired
    private IndexingService indexingService;

    @GetMapping("/crawl-and-index")
    public List<Indexing> crawlAndIndex(@RequestParam String url) throws IOException {
        List<Map.Entry<String, Document>> crawledDocuments = webCrawlerService.crawl(url);
        return crawledDocuments.stream()
                .map(entry -> indexingService.indexPage(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }
}

