package com.openinsider4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Represents <a href="http://openinsider.com" >openinsider dot com</a>.
 *
 * @author Sixro
 * @since 1.0
 */
public class OpenInsider {

    /**
     * Represents the {@code URI} parsed to get data.
     */
    public static final URI URI;
    static {
        try {
            URI = new URI("http://openinsider.com/screener?s=&o=&pl=&ph=&ll=&lh=&fd=30&fdr=&td=0&tdr=&fdlyl=&fdlyh=&daysago=&xp=1" +
                "&vl=1000&vh=&ocl=&och=&sic1=-1&sicl=100&sich=9999&isceo=1&grp=0&nfl=&nfh=&nil=&nih=&nol=&noh=&v2l=&v2h=&oc2l=" +
                "&oc2h=&sortcol=0&cnt=100&page=1");
        } catch (URISyntaxException e) {
            throw new RuntimeException("wrong URI", e);
        }
    }

    /**
     * Returns insider trades show at the moment.
     *
     * @return insider trades
     */
    public InsiderTrades insiderTrades() {
        try {
            Document doc = Jsoup.connect(URI.toString()).get();
            Elements elements = doc.select(".tinytable > tbody >tr");
            return new ElementsBasedInsiderTrades(elements);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse URI " + URI, e);
        }
    }

}
