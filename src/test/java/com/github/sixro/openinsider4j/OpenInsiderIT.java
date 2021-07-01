package com.github.sixro.openinsider4j;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class OpenInsiderIT {

    @Test public void not_empty() {
        OpenInsider oi = new OpenInsider();
        InsiderTrades trades = oi.insiderTrades();

        Iterator<InsiderTrade> it = trades.iterator();
        assertTrue(it.hasNext());
        InsiderTrade trade = it.next();
        
        assertTrue(trade.filingDateTime().isAfter(LocalDateTime.parse("2021-06-28T17:16:18")));
        assertTrue(trade.tradeDate().isAfter(LocalDate.parse("2021-06-24")));
        assertFalse(trade.ticker().isBlank());
        assertFalse(trade.companyName().isBlank());
        assertFalse(trade.insiderName().isBlank());
        assertTrue(trade.titles().length > 0);
        assertEquals(InsiderTrade.Type.PURCHASE, trade.type());
    }

}