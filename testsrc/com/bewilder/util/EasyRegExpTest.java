package com.bewilder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;


public class EasyRegExpTest {

	@Test
	public void starShouldMatchAnythingButStartTag() {
		assertRegExpMatches("<br>(*)<img", "<br>anything<img", "anything");
	}
	
	@Test
	public void starShouldNotMatchStartTag() {
		assertRegExpNoMatch("<br>*<img", "<br>anyt<b>hing<img");
	}

	@Test
	public void spaceInTagShouldMatchAnythingButEndTag() {
		assertRegExpMatches("<a href=\"(*)\">(*)</a>", "<a class=\"css\" href=\"url\">text</a>", "url", "text");
	}

	@Test
	public void spaceInTagShouldNotMatchEndTag() {
		assertRegExpNoMatch("<a href=\"(*)\">(*)</a>", "<a class=\"css\"><img href=\"url\">text</a>");
	}
	
	
	@Test
	public void endTagShouldMatchAnythingPreceedingButEndTag() {
		assertRegExpMatches("<a href=\"(*)\">(*)</a>", "<a href=\"url\" class=\"css\">text</a>", "url", "text");
	}
	
	@Test
	public void attributesBeforeAndAfterShouldBeAllowed() {
		assertRegExpMatches("<a href=\"(*)\">*<b>(*)</b></a>", "<a id=\"hej\" \n\n href=\"url\" \n\n target=\"hej\">hej hej<b>text</b></a>", "url", "text");
	}
	
	@Test
	public void anyWhiteSpaceBeforeAndAfterShouldBeAllowed() {		
		assertRegExpMatches("<a href=\"(/program/*)\">(*)</a>", "\r\n\t\r\n<a href=\"/program/223047/video/280395\" class=\"name ajax\">Storslagen final hägrar i 100 höjdare</a>\r\n\t\r\n", "/program/223047/video/280395", "Storslagen final hägrar i 100 höjdare");
		
	}
	
	@Test
	public void starAfterBracketsSHouldNotBeReplaced() {
		assertRegExpMatches("<param name=\"flashvars\" value=\"*(rtmp*/)([^\"/]*\\.mp4)*\"", "<param name=\"flashvars\" value=\"streamer:rtmp://server/path/to/my/file.mp4; nextfile\"", "rtmp://server/path/to/my/", "file.mp4");
	}
	
	
	@Test
	public void doubleStarShouldMatchAnything() {
		assertRegExpMatches("<a>(**)<d>", "<a><b>\r\n\r\n<c src=\"hej\"><d>", "<b>\r\n\r\n<c src=\"hej\">");
	}


	private void assertRegExpMatches(String pattern, String tryout, String ... expectedGroups) {
		Pattern p = EasyRegExp.createPattern(pattern);
		Matcher m = p.matcher(tryout);
		Assert.assertTrue(m.find());
		for (int i = 0; i < expectedGroups.length; i++) {
			Assert.assertEquals(expectedGroups[i], m.group(i+1));			
		}
	}
	private void assertRegExpNoMatch(String pattern, String tryout) {
		Pattern p = EasyRegExp.createPattern(pattern);
		Matcher m = p.matcher(tryout);
		Assert.assertFalse(m.matches());
	}
	
	

	
}
