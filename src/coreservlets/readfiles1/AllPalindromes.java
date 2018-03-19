package coreservlets.readfiles1;

import java.nio.file.Files;
import java.nio.file.Paths;

import coreservlets.strings.StringUtils;

public class AllPalindromes 
{

	public static void main(String[] args) throws Exception
	{
		String inputFile = "enable1-word-list.txt";
		Files.lines(Paths.get(inputFile))
		.filter(StringUtils::isPalindrome)
		.forEach(System.out::println);
	}
}