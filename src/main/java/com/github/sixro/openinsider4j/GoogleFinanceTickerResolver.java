package com.github.sixro.openinsider4j;

import com.github.sixro.commons.finance.Market;
import com.github.sixro.commons.finance.Ticker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Represents a ticker resolver using Google Finance.
 */
public final class GoogleFinanceTickerResolver implements TickerResolver {

    private static final String USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";

    private static final String QUOTE_URL = "https://www.google.com/finance/quote/%s:%s";
    private static final Collection<String> SUFFIXES = Arrays.asList("NYSE", "NASDAQ");

    @Override
    public Ticker resolve(String tickerCode) {
        for (String suffix: SUFFIXES) {
            try {
                Document doc = Jsoup.connect(String.format(QUOTE_URL, tickerCode, suffix))
                    .userAgent(USER_AGENT)
                    .followRedirects(true)
                    .get();
                Elements el = doc.select("h1");
                if (!el.isEmpty())
                    return Ticker.valueOf(tickerCode, Market.valueOf(suffix));
            } catch (IOException e) {
                throw new RuntimeException("Unexpected error trying to check ticker '" + tickerCode + "' on market " + suffix, e);
            }
        }
        throw new NoSuchTickerException("Unable to find any ticker from code '" + tickerCode + "'");
    }
}
