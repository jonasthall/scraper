package com.bewilder.scraper.regexp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bewilder.scraper.Scraper;

public abstract class RegExpScraper<T> implements Scraper<T> {
	Pattern prePattern = null;
	Pattern pattern = null;

	
	public RegExpScraper(String regExp) {
		pattern = Pattern.compile(regExp,Pattern.DOTALL);
	}
	public RegExpScraper(Pattern pattern) {
		this.pattern = pattern;
	}


	@Override
	public Collection<T> scrape(String data) {
		
		Collection<T> results = new LinkedList<T>();
		Matcher m;
		if (prePattern != null) {
			System.out.println("PREPATTERN: "+this.prePattern.toString());
			m = prePattern.matcher(data);
			if (m.find()) {
				data = m.group(1);
			}
		}
		System.out.println("PATTERN: "+this.pattern.toString());
		m = pattern.matcher(data);
		while (m.find()) {			
			T result = createResult(m);
			results.add(result);			
		}					

		return results;
	}


	public abstract T createResult(Matcher matcher);
}
