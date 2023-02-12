package jUnit;

import org.junit.Test;

public class SetNamingTest {
	
	@Test
	public void turnintoKeySetter() {
		String key = "username";
		
		String firstKey = "set";
		String upperkey = key.substring(0, 1).toUpperCase();
		String remainKey = key.substring(1);
		
		System.out.println("firstKey : " + firstKey);
		System.out.println("upperkey : " + upperkey);
		System.out.println("remainKey : " + remainKey);
		
		String result = firstKey + upperkey + remainKey;
		System.out.println("result : "  + result);
		
		
	}
}
