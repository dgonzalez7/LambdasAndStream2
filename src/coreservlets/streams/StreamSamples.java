package coreservlets.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

public class StreamSamples 
{

	public static void main(String[] args) 
	{
		forEachExamples();
		System.out.println();
		mapExamples();
		System.out.println();
		filterExamples();
		System.out.println();
		combinedExample();
		System.out.println();
		lazyEvaluationExample();
		
		System.out.println();
		reduceExamples();
	}

	/** Examples of the forEach method */

	public static void forEachExamples() 
	{
		List<Employee> googlers = EmployeeSamples.getGooglers();
		googlers.stream().forEach(System.out::println);

		googlers.stream().forEach(e -> e.setSalary(e.getSalary() * 11/10)); 
		googlers.stream().forEach(System.out::println);
		
		System.out.println();
		
		Consumer<Employee> giveRaise = e -> {
			System.out.printf("%s earned $%,d before raise.%n",
					e.getFullName(), e.getSalary());
			e.setSalary(e.getSalary() * 11/10);
			System.out.printf("%s will earn $%,d after raise.%n",
					e.getFullName(), e.getSalary());
		};
		googlers.stream().forEach(giveRaise);
		
		System.out.println();
		List<Employee> sampleEmployees = EmployeeSamples.getSampleEmployees();
		sampleEmployees.stream().forEach(giveRaise);
	}
	
	/** A simple static method that turns a Stream into a List, then prints the List
	 *  and the associated message.
	 */
	private static <T> void printStream(Stream<T> s, String message) 
	{
		System.out.printf("%s: %s.%n", 
				message, s.collect(Collectors.toList()));
	}  

	/** Very basic and introductory examples of the map method. More on map later. */

	public static void mapExamples() 
	{
		List<Double> nums = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
		printStream(nums.stream(), "Original nums");
		printStream(nums.stream().map(n -> n * n), "Squares");
		printStream(nums.stream().map(n -> n * n).map(Math::sqrt), 
				"Square roots of the squares");

		Integer[] ids = { 1, 2, 4, 8 };
		printStream(Stream.of(ids), "IDs");
		printStream(Stream.of(ids).map(EmployeeSamples::findGoogler)
				.map(Person::getFullName), 
				"Names of Googlers with given IDs");
	}
	
	/** Very basic and introductory examples of the filter method. More on filter later. */

	public static void filterExamples() 
	{
		Integer[] nums = { 1, 2, 3, 4, 5, 6 };
		printStream(Stream.of(nums), "Original nums");
		printStream(Stream.of(nums).filter(n -> n%2 == 0), "Even nums");
		printStream(Stream.of(nums).filter(n -> n>3), "Nums > 3");
		printStream(Stream.of(nums)
				.filter(n -> n%2 == 0)
				.filter(n -> n>3), 
				"Even nums > 3");

		Integer[] ids = { 16, 8, 4, 2, 1 };
		printStream(Stream.of(ids).map(EmployeeSamples::findGoogler) 
				.filter(e -> e != null)
				.filter(e -> e.getSalary() > 500_000),
				"Googlers with salaries over $500K");

	}
	
	/** An example that combines map and filter operations. Used to illustrate lazy evaluation. */

	public static void combinedExample() 
	{
		Integer[] ids = { 16, 8, 4, 2, 1 };
		System.out.printf("First Googler with salary over $500K: %s%n", 
				Stream.of(ids).map(EmployeeSamples::findGoogler) 
				.filter(e -> e != null)
				.filter(e -> e.getSalary() > 500_000)
				.findFirst()
				.orElse(null));
	}
	
	/** Same as combinedExample, but with print statements added inside the lambdas. */
	  
	public static void lazyEvaluationExample() 
	{
		Function<Integer,Employee> findGoogler = 
				n -> 
				{ 
					System.out.println("Finding Googler with ID " + n);
					return(EmployeeSamples.findGoogler(n));
				};
				
		Predicate<Employee> checkForNull = 
				e -> 
				{ 
					System.out.println("Checking for null");
					return(e != null);
				};
						
		Predicate<Employee> checkSalary = 
				e -> 
				{ 
					System.out.println("Checking if salary > $500K");
					return(e.getSalary() > 500_000);
				};
				
		Integer[] ids = { 16, 8, 4, 2, 1 };
		System.out.printf("First Googler with salary over $500K: %s%n", 
				Stream.of(ids)
				.map(findGoogler)
				.filter(checkForNull)
				.filter(checkSalary)
				.findFirst()
				.orElse(null));
	}
	
	/** Examples of the reduce method and related reduction operations. */

	public static void reduceExamples() 
	{
		List<String> letters = Arrays.asList("a", "b", "c", "d");
		String concat = letters.stream().reduce("", String::concat);
		System.out.printf("Concatenation of %s is %s.%n", 
				letters, concat);
		String reversed = letters.stream().reduce("", (s1,s2) -> s2+s1);
		System.out.printf("Reversed concatenation of %s is %s.%n", 
				letters, reversed);
		String upperReversed = 
				letters.stream().reduce("", (s1,s2) -> s2.toUpperCase() + s1);
		System.out.printf("Uppercase reversed concatenation of %s is %s.%n", 
				letters, upperReversed);
		String upperReversed2 = 
				letters.stream().reduce("", (s1,s2) -> s2+s1).toUpperCase();
		System.out.printf("Uppercase reversed concatenation of %s is %s.%n", 
				letters, upperReversed2);

		System.out.println();
		Employee poorest = new Employee("None", "None", -1, -1);
		BinaryOperator<Employee> richer = (e1, e2) -> {
			return(e1.getSalary() >= e2.getSalary() ? e1 : e2);
		};
		List<Employee> googlers = EmployeeSamples.getGooglers();
		Employee richestGoogler = googlers.stream().reduce(poorest, richer);
		System.out.printf("Richest Googler is %s.%n", richestGoogler);

		System.out.println();
		List<Double> nums1a = Arrays.asList(1.2, -2.3, 4.5, -5.6);
		double maxNum1 = 
				nums1a.stream().reduce(Double.MIN_VALUE, Double::max);
		System.out.printf("Max of %s is %s.%n", nums1a, maxNum1);
		
		double[] nums1b = {1.2, -2.3, 4.5, -5.6};
		double maxNum2 = 
				DoubleStream.of(nums1b).max().orElse(Double.MIN_VALUE);
		System.out.printf("Max of [same numbers] is %s.%n", 
				maxNum2);

		System.out.println();
		List<Integer> nums2 = Arrays.asList(1, 2, 3, 4);
		int sum1 = nums2.stream().reduce(0, Integer::sum);
		System.out.printf("Sum of %s is %s.%n", nums2, sum1);
		int sum2 = nums2.stream().reduce(Integer::sum).get();
		System.out.printf("Sum of %s is %s.%n", nums2, sum2);
		int[] nums3 = { 1, 2, 3, 4 };
		int sum3 = Arrays.stream(nums3).sum();
		System.out.printf("Sum of {1, 2, 3, 4} is %s.%n", sum3);

		int product = nums2.stream().reduce(1, (n1, n2) -> n1 * n2);
		System.out.printf("Product of %s is %s.%n", nums2, product);

		System.out.println();
		int sum4 = nums2.stream().map(EmployeeSamples::findGoogler)
				.map(Employee::getSalary)
				.reduce(0, Integer::sum);
		System.out.printf("Combined salaries of Googlers with IDs %s is $%,d.%n", 
				nums2, sum4);
		int sum5 = nums2.stream().map(EmployeeSamples::findGoogler)
				.mapToInt(Employee::getSalary)
				.sum();
		System.out.printf("Combined salaries of Googlers with IDs %s is $%,d.%n", 
				nums2, sum5);
	}
}
