package com.bewilder.scraper;

import java.util.Collection;

public interface Scraper<T> {

	Collection<T> scrape(String data);
}
