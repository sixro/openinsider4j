package com.github.sixro.openinsider4j;

/**
 * Represents <a href="http://openinsider.com" >openinsider.com</a>.
 */
public interface OpenInsider {

    /**
     * Returns insider trades shown at the moment.
     *
     * @return insider trades
     */
    InsiderTrades insiderTrades();

}
