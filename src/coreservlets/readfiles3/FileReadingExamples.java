package coreservlets.readfiles3;

import java.util.Arrays;
import java.util.*;

public class FileReadingExamples 
{

	public static void main(String[] args) 
	{
		String filename = "enable1-word-list.txt";
		if (args.length > 0) 
		{
			filename = args[0];
		}
		testAllPalindromes(filename);
		// test3LetterPalindromes(filename);
		testPalindromes(filename, 3, 4, 7);
		testFirstPalindrome(filename); 
	}
	
	public static void testAllPalindromes(String filename)
	{
		List<String> testWords = Arrays.asList("bog", "bob", "dam", "dad");
		System.out.printf("All palindroms in list %s:%n", testWords);
		FileUtils.printAllPalindromes(testWords.stream());
		System.out.printf("All palindroms in list %s:%n", filename);
		FileUtils.printAllPalindromes(filename);
	}
	
	public static void testPalindromes(String filename, int... lengths) 
	{
		List<String> testWords = Arrays.asList("rob", "bob", "reed", "deed");
		for(int length: lengths) 
		{
			System.out.printf("%s-letter palindromes in list %s:%n", length, testWords);
			FileUtils.printPalindromes(testWords.stream(), length);
			System.out.printf("%s-letter palindromes in file %s:%n", length, filename);
			FileUtils.printPalindromes(filename, length);
		}
	}
	
	public static void testFirstPalindrome(String filename) 
	{
		List<String> testWords = 
				Arrays.asList("bog", "bob", "dam", "dad");
		String firstPalindrome = 
				FileUtils.firstPalindrome(testWords.stream());
		System.out.printf("First palindrome in list %s is %s.%n", testWords, firstPalindrome);
		firstPalindrome = FileUtils.firstPalindrome(filename);
		System.out.printf("First palindrome in file %s is %s.%n", filename, firstPalindrome);
	}
}