package coreservlets.streams;

import java.util.stream.Stream;

public class StreamSamplesInfinite 
{

	public static void main(String[] args) 
	{
		generateExamples();
	}
	
	public static void generateExamples() 
	{
		System.out.println("2 Random numbers:");
		Stream.generate(Math::random).limit(2).forEach(System.out::println);
		System.out.println("4 Random numbers:");
		Stream.generate(Math::random).limit(4).forEach(System.out::println);
		
		System.out.println();
		System.out.println("5 Fibonacci numbers:");
		FibStream.makeFibStream(5).forEach(System.out::println);
		
		System.out.println("25 Fibonacci numbers:");
		FibStream.makeFibStream(25)
			.forEach(System.out::println);
		
		System.out.println();
		System.out.println("14 Twitter messages:");
		Stream.iterate("Big News!!", msg -> msg + "!!!!!!!!!!")
			.limit(14)
			.forEach(System.out::println);
		
//		System.out.println();
//		System.out.println("10 100-digit primes:");
//		PrimeStream.makePrimeStream(100, 10)
//		.forEach(System.out::println);

//		BigInteger[] primes = PrimeStream.makePrimeArray(100, 5);
//		for(BigInteger prime: primes) 
//		{
//			System.out.println("Prime is " + prime);
//		}

	}
}