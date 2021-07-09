package com.github.sixro.openinsider4j;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

class ElementsBasedInsiderTrades implements InsiderTrades {

    private final Elements elements;
    private final TickerResolver tickerResolver;

    ElementsBasedInsiderTrades(Elements elements, TickerResolver tickerResolver) {

        this.elements = elements;
        this.tickerResolver = tickerResolver;
    }

    @Override
    public Iterator<InsiderTrade> iterator() {
        return new ElementsBasedInsiderTrades.Iterator<>(this.elements.iterator(), tickerResolver);
    }

    private static class Iterator<I> implements java.util.Iterator<InsiderTrade> {
        private final java.util.Iterator<Element> it;
        private final TickerResolver tickerResolver;

        Iterator(java.util.Iterator<Element> it, TickerResolver tickerResolver) {
            this.it = it;
            this.tickerResolver = tickerResolver;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public InsiderTrade next() {
            Element el = it.next();
            return new ElementBasedInsiderTrade(el, tickerResolver);
        }

    }
}
