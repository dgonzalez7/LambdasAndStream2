package coreservlets.readfiles1;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FourLetterWords 
{

	public static void main(String[] args) throws Exception
	{
		String inputFile = "enable1-word-list.txt";
		String outputFile = "four-letter-words.txt";
		int length = 4;
		List<String> words =
				Files.lines(Paths.get(inputFile))
				.filter(word -> word.length() == length)
				.map(String::toUpperCase)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		Files.write(Paths.get(outputFile), words, Charset.defaultCharset());
		System.out.printf("Wrote %s words to %s.%n", words.size(), outputFile);
	}
}