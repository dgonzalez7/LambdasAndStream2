package coreservlets.readfiles1;

import java.nio.file.*;

public class XsAndYs 
{

	public static void main(String[] args) throws Exception
	{
		String inputFile = "enable1-word-list.txt";
		int letterCount = 
				Files.lines(Paths.get(inputFile))
				.filter(word -> word.contains("x"))
				.filter(word -> word.contains("y"))
				.mapToInt(String::length)
				.sum();
		System.out.printf("%,d total letters in words with " +
				"both x and y.%n", letterCount);
	}
}