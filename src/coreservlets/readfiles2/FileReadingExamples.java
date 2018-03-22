package coreservlets.readfiles2;

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
	}

	
	public static void testAllPalindromes(String filename)
	{
		List<String> testWords = Arrays.asList("bog", "bob", "dam", "dad");
		System.out.printf("All palindroms in list %s:%n", testWords);
		FileUtils.printAllPalindromes(testWords.stream());
		System.out.printf("All palindroms in list %s:%n", filename);
		FileUtils.printAllPalindromes(filename);
	}
}
