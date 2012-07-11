package com.bewilder.scraper.video;

import java.net.MalformedURLException;
import java.net.URL;

import com.bewilder.util.StringEscaper;

public class Video {

	public static final String VIDEO_ITEM = "###";
	private String text;
	private String url;
	public Video(String text, String url) {
		this.text = text;
		this.url = url;
	}
	
	public String getText() {
		return StringEscaper.escape(text);
	}

	public String getUrl() {
		return url;
	}

	public String getUrl(Video node) {
		if (node == null || url.startsWith("local:")) {
			return url.replaceFirst("local:", "");
		} else {
			try {
				return new URL(new URL(node.getUrl()), this.url).toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
