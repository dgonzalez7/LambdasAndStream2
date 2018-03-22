package coreservlets.readfiles3;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
import coreservlets.strings.*;

public class FileUtils 
{
	/** Prints all palindromes in the Stream. */

	public static void printAllPalindromes(Stream<String> words) 
	{
		words.filter(StringUtils::isPalindrome)
			.forEach(System.out::println);
	}
	
	/** Prints all palindromes in the file. */

	public static void printAllPalindromes(String filename) 
	{
		StreamProcessor.processFile(filename, FileUtils::printAllPalindromes);
	}
	
	/** Prints the n-length palindromes in the Stream. */

	public static void printPalindromes(Stream<String> words, int length) {
		words.filter(word -> word.length() == length)
		.filter(StringUtils::isPalindrome)
		.forEach(System.out::println);
	}
	
	/** Prints the n-length palindromes in the file. */

	public static void printPalindromes(String filename, int length) 
	{
		StreamProcessor.processFile(filename, lines -> printPalindromes(lines, length));
	}
	
	/** Returns the first palindrome in the Stream. 
	 *  Returns null if there is no match.
	 */

	public static String firstPalindrome(Stream<String> words) 
	{
		return(words.filter(StringUtils::isPalindrome)
				.findFirst()
				.orElse(null));
	}

	/** Returns the first palindrome in the file. 
	 *  Returns null if there is no match.
	 */

	public static String firstPalindrome(String filename) 
	{
		return(StreamAnalyzer.analyzeFile(filename, FileUtils::firstPalindrome));
	}
}
