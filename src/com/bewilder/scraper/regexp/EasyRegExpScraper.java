package com.bewilder.scraper.regexp;

import com.bewilder.util.EasyRegExp;

public abstract class EasyRegExpScraper<T> extends RegExpScraper<T> {
	public EasyRegExpScraper(String easyRegExp) {
		super(EasyRegExp.createPattern(easyRegExp));
	}
}
