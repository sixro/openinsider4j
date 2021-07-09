package com.github.sixro.openinsider4j;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class OpenInsiderIT {

    public static final ZoneId TZ_USA = TimeZone.getTimeZone("America/New_York")
        .toZoneId();

    @Test public void returns_trade() {
        OpenInsider oi = new OpenInsider();
        InsiderTrades trades = oi.insiderTrades();

        Iterator<InsiderTrade> it = trades.iterator();
        assertTrue(it.hasNext());
        InsiderTrade trade = it.next();

        System.out.println("*** " + trade);
        assertTrue(trade.filingDateTime().isAfter(LocalDateTime.parse("2021-06-28T17:16:18").atZone(TZ_USA)));
        assertTrue(trade.tradeDate().isAfter(LocalDate.parse("2021-06-24")));
        assertNotNull(trade.ticker());
        assertFalse(trade.companyName().isBlank());
        assertFalse(trade.insiderName().isBlank());
        assertTrue(trade.titles().length > 0);
        assertEquals(InsiderTrade.Type.PURCHASE, trade.type());
        assertNotNull(trade.price());
        assertTrue(trade.quantity() > 0);
        assertNotNull(trade.value());
    }

}