package com.github.sixro.openinsider4j;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class ElementsBasedInsiderTrades implements InsiderTrades {

    private final Elements elements;

    ElementsBasedInsiderTrades(Elements elements) {

        this.elements = elements;
    }

    @Override
    public Iterator<InsiderTrade> iterator() {
        return new ElementsBasedInsiderTrades.Iterator<>(this.elements.iterator());
    }

    private static class Iterator<I> implements java.util.Iterator<InsiderTrade> {
        private final java.util.Iterator<Element> it;

        Iterator(java.util.Iterator<Element> it) {
            this.it = it;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public InsiderTrade next() {
            Element el = it.next();
            return new ElementBasedInsiderTrade(el);
        }

    }
}
