package com.bewilder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyRegExp {

	public static Pattern createPattern(String easyRegExp) {
		String realRegExp = null;
		realRegExp = doubleStarMatchesAnything(easyRegExp);
		realRegExp = starMatchesAnythingButStartTagAndQuotes(realRegExp);
		realRegExp = endTagAllowsExtraAttributes(realRegExp);
		realRegExp = startTagAllowsExtraAttributes(realRegExp);
		return Pattern.compile(realRegExp, Pattern.DOTALL);
	}

	private static String doubleStarMatchesAnything(String easyRegExp) {
		return easyRegExp.replaceAll("\\*\\*", ".+?");
	}

	private static String endTagAllowsExtraAttributes(String easyRegExp) {
		return easyRegExp.replaceAll(">", "[^>]*>");
	}

	private static String startTagAllowsExtraAttributes(String easyRegExp) {
		Matcher m = Pattern.compile("<(\\S+)\\s").matcher(easyRegExp);
		StringBuffer sb = new StringBuffer();
		while(m.find()) {
			m.appendReplacement(sb, "<"+m.group(1)+"[^>]*");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private static String starMatchesAnythingButStartTagAndQuotes(
			String easyRegExp) {
		return easyRegExp.replaceAll("(?<!\\])\\*", "[^<\"]*?");
	}
}
