package coreservlets.readfiles1;

import java.nio.file.*;

public class QsWithoutUs 
{

	public static void main(String[] args) throws Exception
	{
		String inputFile = "enable1-word-list.txt";
		long wordCount = 
				Files.lines(Paths.get(inputFile))
				.filter(word -> word.contains("q"))
				.filter(word -> !word.contains("qu"))
				.count();
		System.out.printf("%s words with q but not u.%n", wordCount);
	}
}