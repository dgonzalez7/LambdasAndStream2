package coreservlets.paths;

import java.nio.file.*;

public class PathExamples 
{

	public static void main(String[] args) 
	{
	    Path p1 = Paths.get("input-file.txt");
	    System.out.println("Simple Path");
	    System.out.printf("toString: %s%n%n", p1);
	    Path p2 = p1.toAbsolutePath();
	    System.out.println("Absolute Path");
	    System.out.printf("toString: %s%n", p2);
	    System.out.printf("getFileName: %s%n", p2.getFileName());
	    System.out.printf("getName(0): %s%n", p2.getName(0));
	    System.out.printf("getNameCount: %d%n", p2.getNameCount());
	    System.out.printf("subpath(0,2): %s%n", p2.subpath(0,2));
	    System.out.printf("getParent: %s%n", p2.getParent());
	    System.out.printf("getRoot: %s%n", p2.getRoot());
	}

}
