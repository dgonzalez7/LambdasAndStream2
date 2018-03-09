import java.util.Arrays;
import java.util.Comparator;

public class SortUsingLambdas 
{
	public static void main(String[] args) 
	{
		// Java 7 style
		
		String[] testStrings = {"one", "two", "three", "four"}; 
	
		System.out.println(Arrays.toString(testStrings));
		
		Arrays.sort(testStrings, new Comparator<String>() 
		{
				@Override
				public int compare(String s1, String s2) 
				{
					return(s1.length() - s2.length());
				}
		});
		
		System.out.println(Arrays.toString(testStrings));
		
		// ***********************************
		// Java 8 using lambdas
		
		String[] testStrings2 = {"one", "two", "three", "four"}; 
		
		System.out.println(Arrays.toString(testStrings2));
		
		Arrays.sort(testStrings2, (String s1, String s2) -> {return(s1.length() - s2.length()); });
		
		System.out.println(Arrays.toString(testStrings2));

		// ***********************************
		// Java 8 using lambdas - shorter syntax
		
		String[] testStrings3 = {"one", "two", "three", "four"}; 
		
		System.out.println(Arrays.toString(testStrings3));
		
		Arrays.sort(testStrings3, (s1, s2) -> s1.length() - s2.length());
		
		System.out.println(Arrays.toString(testStrings3));
	}
}
