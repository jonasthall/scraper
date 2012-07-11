package com.bewilder.scraper.video;

import java.util.regex.Matcher;

import com.bewilder.scraper.Scraper;
import com.bewilder.scraper.regexp.EasyRegExpScraper;

public class VideoScraper extends EasyRegExpScraper<Video> implements Scraper<Video> {

	public VideoScraper(String easyRegExp) {
		super(easyRegExp);
	}

	public String url(Matcher m) {
		return m.group(1);
	}
	
	public String text(Matcher m) {
		return m.group(2);
	}
	
	
	@Override
	public Video createResult(Matcher matcher) {
		String url = this.url(matcher);
		if (url != null) {
			String text = this.text(matcher);
			return new Video(text, url);
		}
		return null;
	}

}
