package coreservlets.readfiles3;

import java.util.Arrays;

import coreservlets.strings.StringUtils;

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
		System.out.println("\n\n");
		testLetterCount(filename);
		System.out.println("\n\n");
		testFirstMatch(filename);
		System.out.println("\n\n");
		testAllMatches(filename);
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
	
	public static void testLetterCount(String filename) 
	{
		List<String> testWords = Arrays.asList("hi", "hello", "hola");
		System.out.printf("In list %s:%n", testWords);
		int sum1 = FileUtils.letterCount(testWords.stream(), 
				word -> word.contains("h"),
				word -> !word.contains("i"));
		printLetterCountResult(sum1, "contain h but not i");
		
		System.out.printf("In file %s:%n", filename);
		int sum2 = FileUtils.letterCount(filename,
				StringUtils::isPalindrome);
		printLetterCountResult(sum2, "are palindromes");
		
		int sum3 = FileUtils.letterCount(filename,
				word -> word.contains("q"),
				word -> !word.contains("qu"));
		printLetterCountResult(sum3, "contain q not followed by u");
		
		int sum4 = FileUtils.letterCount(filename,
				word -> true);
		printLetterCountResult(sum4, "are in English language");
	}
	
	private static void printLetterCountResult(int sum, String message) 
	{
		System.out.printf("  %,d total letters in words that %s.%n", sum, message);
	}  
	
	public static void testFirstMatch(String filename) 
	{
		List<Integer> testNums = Arrays.asList(1, 10, 2, 20, 3, 30);
		Integer match1 = FileUtils.firstMatch(testNums.stream(),
				n -> n > 2,
				n -> n < 10,
				n -> n % 2 == 1);
		System.out.printf("First word in list %s that is greater than 2, less than 10, and odd is %s.%n", testNums, match1);
		
		String match2 = FileUtils.firstMatch(filename, 
				word -> word.contains("q"),
				word -> !word.contains("qu"));
		System.out.printf("First word in file %s with q not followed by u is %s.%n", filename, match2);
	}
	
	public static void testAllMatches(String filename) 
	{
		List<Integer> testNums = Arrays.asList(2, 4, 6, 8, 10, 12);
		List<Integer> matches1 = 
				FileUtils.allMatches(testNums.stream(), 
						n -> n > 5,
						n -> n < 10);
		System.out.printf("All numbers in list %s that are greater than 5 and less than 10: %s.%n", testNums, matches1);
		
		List<String> matches2 = FileUtils.allMatches(filename, 
				word -> word.contains("q"),
				word -> !word.contains("qu"));
		System.out.printf("All words in file %s with q not followed by u: %s.%n", filename, matches2);
	}
}