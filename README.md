# SearchEngine
 
# Web Crawler and Indexer

This project is a simple web crawler and indexer built using Spring Boot and JSoup. The crawler fetches web pages starting from a given URL, extracts links to other pages, and indexes the content of the pages it visits. The index includes the page title, description, and a list of words found on the page.

## Features

- Crawl web pages starting from a given URL
- Index page title, description, and words
- Extract and follow links to other pages
- Crawl and index up to a specified limit of pages

## Technologies Used

- **Spring Boot**: Framework for building the web application
- **JSoup**: Library for parsing HTML and extracting data

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/web-crawler-and-indexer.git
    cd web-crawler-and-indexer
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

### Usage

To start the crawling and indexing process, send a GET request to the `/crawl-and-index` endpoint with the `url` parameter set to the starting URL. For example:

```bash
http://localhost:8080/crawl-and-index?url=https://en.wikipedia.org/wiki/Google
```
If you want the complete procedures how it works and the flow, in my handwriting

- [here it is](https://github.com/ratneeshh/SearchEngine/blob/main/ratnesh%20pdf.pdf)

### Inspired by : The Coding Sloth [link](https://www.youtube.com/watch?v=WCpimlH0Kck&t=193s)
