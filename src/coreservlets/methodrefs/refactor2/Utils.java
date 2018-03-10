package coreservlets.methodrefs.refactor2;

import java.util.function.Function;

public class Utils 
{
	public static String transform(String s, Function<String, String> f)
	{
		return f.apply(s);
	}
	
	public static String makeExciting(String s)
	{
		return (s + "!!");
	}
	
	private Utils() {}
}
