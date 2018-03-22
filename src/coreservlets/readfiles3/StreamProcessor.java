package coreservlets.readfiles3;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.*;

@FunctionalInterface
public interface StreamProcessor 
{
	void processStream(Stream<String> strings);
	
	public static void processFile(String filename, StreamProcessor processor) 
	{
		try(Stream<String> lines = Files.lines(Paths.get(filename))) 
		{
			processor.processStream(lines);
		} 
		catch(IOException ioe) 
		{
			System.err.println("Error reading file: " + ioe);
		}
	}
}
