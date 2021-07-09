package com.github.sixro.openinsider4j;

import com.github.sixro.commons.finance.Ticker;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Element;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

@SuppressWarnings("PMD.ExcessivePublicCount")
class ElementBasedInsiderTrade implements InsiderTrade {

    private static final DateTimeFormatter FORMATTER_FILING_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneId TZ_USA = TimeZone.getTimeZone("America/New_York").toZoneId();

    private final Element element;
    private final TickerResolver tickerResolver;

    public ElementBasedInsiderTrade(Element element, TickerResolver tickerResolver) {
        this.element = element;
        this.tickerResolver = tickerResolver;
    }

    @Override
    public ZonedDateTime filingDateTime() {
        String text = element
            .select("tr > td:eq(1) > * > a")
            .text();
        LocalDateTime dt = LocalDateTime.parse(text, FORMATTER_FILING_DATETIME);
        return dt.atZone(TZ_USA);
    }

    @Override
    public LocalDate tradeDate() {
        String text = element
            .select("tr > td:eq(2) > div")
            .text();
        return LocalDate.parse(text);
    }

    @Override
    public Ticker ticker() {
        String text = element
            .select("tr > td:eq(3) > * > a")
            .text();
        Ticker ticker = tickerResolver.resolve(text);
        return ticker;
    }

    @Override
    public String companyName() {
        String text = element
            .select("tr > td:eq(4) > a")
            .text();
        return text;
    }

    @Override
    public String insiderName() {
        String text = element
            .select("tr > td:eq(5) > a")
            .text();
        return text;
    }

    @Override
    public String[] titles() {
        String text = element
            .select("tr > td:eq(6)")
            .text();
        String[] ret = Arrays.stream(text.split(","))
            .map(String::trim)
            .toArray(String[]::new);
        return ret;
    }

    @Override
    public Type type() {
        String text = element
            .select("tr > td:eq(7)")
            .text();
        return Type.valueOf(text.split("-")[1].trim().toUpperCase());
    }

    @Override
    public MonetaryAmount price() {
        String text = element
            .select("tr > td:eq(8)")
            .text();
        if (!text.startsWith("$"))
            throw new IllegalStateException("unexpected 'price' not starting with currency symbol: " + text);

        text = text.substring(1);
        return Money.parse(text + " USD");
    }

    @Override
    public int quantity() {
        String text = element
            .select("tr > td:eq(9)")
            .text();
        if (!text.startsWith("+"))
            throw new IllegalStateException("unexpected 'quantity' not starting with '+' character: " + text);
        text = text.replaceAll("[\\+,]", "");
        return Integer.parseInt(text);
    }

    @Override
    public MonetaryAmount value() {
        String text = element
            .select("tr > td:eq(12)")
            .text();
        if (!text.startsWith("+$"))
            throw new IllegalStateException("unexpected 'value' not starting with '+$': " + text);

        text = text.replaceAll("[\\+\\$,]", "");
        return Money.parse(text + " USD");
    }

    @Override
    public String toString() {
        return new StringBuilder(ElementBasedInsiderTrade.class.getSimpleName())
            .append("{")
            .append("filingDateTime=").append(filingDateTime()).append(", ")
            .append("tradeDate=").append(tradeDate()).append(", ")
            .append("ticker=").append(ticker()).append(", ")
            .append("companyName=\"").append(companyName()).append("\", ")
            .append("insiderName=\"").append(insiderName()).append("\", ")
            .append("titles=").append(Arrays.toString(titles())).append(", ")
            .append("type=").append(type()).append(", ")
            .append("price=").append(price()).append(", ")
            .append("quantity=").append(quantity()).append(", ")
            .append("value=").append(value())
            .append("}")
            .toString();
    }
}
