package com.bewilder.scraper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class TreeScraper<T> implements Scraper<T> {

	protected abstract List<? extends Scraper<T>> getScrapers();

	@Override
	public Collection<T> scrape(String data) {
		List<? extends Scraper<T>> scrapers = getScrapers();
		for (int i = scrapers.size()-1; i >= 0; i--) {
			Collection<T> results = scrapers.get(i).scrape(data);
			if (!results.isEmpty()) {
				return results;
			}
		}
		return new LinkedList<T>();
	}

	public abstract T getRoot();
}
