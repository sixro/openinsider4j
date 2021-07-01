package com.openinsider4j;

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
        assertEquals(LocalDateTime.parse("2021-06-28T17:16:18"), trade.filingDateTime());
        assertEquals(LocalDate.parse("2021-06-24"), trade.tradeDate());
        assertEquals("ASAN", trade.ticker());
        assertEquals("Asana, Inc.", trade.companyName());
        assertEquals("Moskovitz Dustin A.", trade.insiderName());
        assertArrayEquals(new String[]{ "Pres", "CEO", "Chair", "10%" }, trade.titles());
        assertEquals(InsiderTrade.Type.PURCHASE, trade.type());
    }

}