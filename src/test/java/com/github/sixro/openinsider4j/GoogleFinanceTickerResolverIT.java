package com.github.sixro.openinsider4j;

import com.github.sixro.commons.finance.Market;
import com.github.sixro.commons.finance.Ticker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleFinanceTickerResolverIT {

    private final GoogleFinanceTickerResolver resolver = new GoogleFinanceTickerResolver();

    @Test
    public void resolve_nasdaq() {
        Ticker ticker = resolver.resolve("CYCN");
        assertEquals(Ticker.valueOf("CYCN", Market.NASDAQ), ticker);
    }

    @Test
    public void resolve_nyse() {
        Ticker ticker = resolver.resolve("ASAN");
        assertEquals(Ticker.valueOf("ASAN", Market.NYSE), ticker);
    }

    @Test
    public void no_ticker_found() {
        assertThrows(NoSuchTickerException.class, () -> resolver.resolve("STOCAZZO"));
    }

}