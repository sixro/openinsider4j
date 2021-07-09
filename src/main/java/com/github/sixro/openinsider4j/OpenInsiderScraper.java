package com.github.sixro.openinsider4j;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Represents an open insider using scraping tecnique.
 */
public final class OpenInsiderScraper implements OpenInsider {

    private static final URI URI;
    static {
        try {
            URI = new URI("http://openinsider.com/screener?s=&o=&pl=&ph=&ll=&lh=&fd=30&fdr=&td=0&tdr=&fdlyl=&fdlyh=&daysago=&xp=1" +
                "&vl=1000&vh=&ocl=&och=&sic1=-1&sicl=100&sich=9999&isceo=1&grp=0&nfl=&nfh=&nil=&nih=&nol=&noh=&v2l=&v2h=&oc2l=" +
                "&oc2h=&sortcol=0&cnt=100&page=1");
        } catch (URISyntaxException e) {
            throw new RuntimeException("wrong URI", e);
        }
    }

    private final TickerResolver tickerResolver;

    /**
     * Creates an open insider using a ticker resolver based on Google Finance.
     *
     * @see GoogleFinanceTickerResolver
     */
    public OpenInsiderScraper() {
        this(new GoogleFinanceTickerResolver());
    }

    /**
     * Creates an open insider using the specified ticker resolver.
     *
     * @param tickerResolver the ticker resolver
     */
    public OpenInsiderScraper(TickerResolver tickerResolver) {
        this.tickerResolver = tickerResolver;
    }

    @Override
    public InsiderTrades insiderTrades() {
        try {
            Connection connection = Jsoup.connect(URI.toString());
            Document doc = connection.get();
            Elements elements = doc.select(".tinytable > tbody >tr");
            return new ElementsBasedInsiderTrades(elements, tickerResolver);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse URI " + URI, e);
        }
    }

}
