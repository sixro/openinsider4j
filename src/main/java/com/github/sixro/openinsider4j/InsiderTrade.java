package com.github.sixro.openinsider4j;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Represents an insider trade.
 *
 * @author Sixro
 * @since 1.0
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public interface InsiderTrade {

    /**
     * Represents the type of trade.
     */
    enum Type { PURCHASE, SALE }

    /**
     * Returns the filing date time.
     *
     * <p>
     * Returned {@code ZonedDateTime} is in {@code America/New_York} time zone.
     * </p>
     *
     * @return the filing date time
     */
    ZonedDateTime filingDateTime();

    /**
     * Returns the trade date.
     *
     * @return the trade date
     */
    LocalDate tradeDate();

    /**
     * Returns the ticker.
     *
     * @return the ticker
     */
    String ticker();

    /**
     * Returns the company name.
     *
     * @return the company name
     */
    String companyName();

    /**
     * Returns the insider name.
     *
     * @return the insider name
     */
    String insiderName();

    /**
     * Returns the titles that participate to the trade.
     *
     * @return the titles
     */
    String[] titles();

    /**
     * Returns the type of trade.
     *
     * @return the type of trade
     */
    Type type();

    /**
     * Returns the price at which the insider trader make this operation.
     *
     * @return the price
     */
    MonetaryAmount price();

    /**
     * Returns the purchased quantity.
     *
     * @return the purchased quantity
     */
    int quantity();

    /**
     * Returns the value of the operation.
     *
     * @return the value of the operation
     */
    MonetaryAmount value();

}
