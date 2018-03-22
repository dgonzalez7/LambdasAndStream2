package coreservlets.readfiles2;

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
	    try(Stream<String> words = Files.lines(Paths.get(filename))) 
	    {
	      printAllPalindromes(words);
	    } 
	    catch(IOException ioe) 
	    {
	      System.err.println("Error reading file: " + ioe);
	    }
	  }
}
