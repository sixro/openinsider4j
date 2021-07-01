package com.github.sixro.openinsider4j;

import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class ElementBasedInsiderTrade implements InsiderTrade {

    private static final DateTimeFormatter FORMATTER_FILING_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Element element;

    ElementBasedInsiderTrade(Element element) {
        this.element = element;
    }

    @Override
    public LocalDateTime filingDateTime() {
        String text = element
            .select("tr > td:eq(1) > div > a")
            .text();
        return LocalDateTime.parse(text, FORMATTER_FILING_DATETIME);
    }

    @Override
    public LocalDate tradeDate() {
        String text = element
            .select("tr > td:eq(2) > div")
            .text();
        return LocalDate.parse(text);
    }

    @Override
    public String ticker() {
        String text = element
            .select("tr > td:eq(3) > b > a")
            .text();
        return text;
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
}
