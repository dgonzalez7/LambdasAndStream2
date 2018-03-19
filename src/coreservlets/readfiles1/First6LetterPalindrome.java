package coreservlets.readfiles1;

import java.nio.file.*;

import coreservlets.strings.StringUtils;

public class First6LetterPalindrome 
{

	public static void main(String[] args) throws Exception
	{
		String inputFile = "enable1-word-list.txt";
		String firstPalindrome = 
				Files.lines(Paths.get(inputFile))
				.filter(word -> word.length() == 6)
				.filter(StringUtils::isPalindrome)
				.findFirst()
				.orElse(null);
		System.out.printf("First 6-letter palindrome is %s.%n", firstPalindrome);
	}
}