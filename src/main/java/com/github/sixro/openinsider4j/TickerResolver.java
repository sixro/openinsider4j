package com.github.sixro.openinsider4j;

import com.github.sixro.commons.finance.Ticker;

/**
 * Represents a ticker resolver.
 */
public interface TickerResolver {

    /**
     * Resolve a ticker starting from only the ticker code.
     *
     * @param tickerCode the ticker code
     * @return a ticker
     */
    Ticker resolve(String tickerCode);

}
