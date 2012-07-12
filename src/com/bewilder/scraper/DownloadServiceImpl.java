package com.bewilder.scraper;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class DownloadServiceImpl implements DownloadService {

	@Override
	public String download(String url) {
		try {
		URLConnection conn = null;
		String type = "";
		int retry = 0;
		while (retry < 3) {
			try {
				conn = new URL(url).openConnection();
				type = conn.getContentType();
				break;
			} catch(Exception e) {
				retry++;
			}
		}
		int i = type.indexOf("charset=");
		String encoding = conn.getContentEncoding();
		if (i != -1) {
			encoding = type.substring(i+8);
		}
		if (encoding == null) {
			encoding = "UTF-8";
		}
		InputStream is = conn.getInputStream();
		return readStreamToEnd(is, encoding);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String readStreamToEnd(InputStream is) {
		return readStreamToEnd(is, Charset.defaultCharset().name());
	}
	public String readStreamToEnd(InputStream is, String encoding) {
		StringBuffer sb = new StringBuffer();
		byte [] buf = new byte[4096];
		int n = -1;
		try {
		do {
			n = is.read(buf, 0, buf.length);
			if (n > 0) {
				sb.append(new String(buf,0,n,encoding));
			}
		} while (n > 0);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

}
