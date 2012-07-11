package com.bewilder.util;

public class StringEscaper {
	private static String[] htmlEscape = new String[256];
	
	static {
		htmlEscape[47] = "&#47;";
		
		htmlEscape[197] = "&Aring;";
		htmlEscape[196] = "&Auml;";
		htmlEscape[214] = "&Ouml;";

		htmlEscape[229] = "&aring;";
		htmlEscape[228] = "&auml;";
		htmlEscape[246] = "&ouml;";
		
	}
	
	public static String escape(String data) {
		char [] chars = data.toCharArray();
		StringBuffer sb = new StringBuffer(data.length());
		for (char c : chars) {
			int i = (int)c;
			if (i > 256) {
				sb.append("&#");
				sb.append(i);
				sb.append(';');
			}
			else if (htmlEscape[i] != null) {
				sb.append(htmlEscape[i]);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
