package coreservlets.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamSamplesMore 
{

	public static void main(String[] args) 
	{
		limitExamples();
		System.out.println();
		sortedExamples();
		System.out.println();
		matchExamples();
	}

	/** Examples of the limit and skip methods. */

	public static void limitExamples() 
	{
		List<Employee> googlers = EmployeeSamples.getGooglers();
		// Calls getFirstName only 6 times
		List<String> emps = 
				googlers.stream()
				.map(Person::getFirstName)
				.limit(8)
				.skip(2)
				.collect(Collectors.toList());
		System.out.printf("Names of 6 Googlers: %s.%n", 
				emps);
	}
	
	/** Examples of the sorted, min, max, and distinct methods. */

	public static void sortedExamples() 
	{
		List<Integer> ids = Arrays.asList(9, 11, 10, 8);
		List<Employee> emps1 = 
				ids.stream().map(EmployeeSamples::findGoogler)
				.sorted((e1, e2) -> e1.getLastName()
						.compareTo(e2.getLastName()))
				.collect(Collectors.toList());
		List<Employee> emps2 = 
				ids.stream().map(EmployeeSamples::findGoogler)
				.sorted((e1, e2) -> e1.getSalary() - e2.getSalary())
				.collect(Collectors.toList());
		
		System.out.printf("Googlers with ids %s sorted by last name: %s.%n", 
				ids, emps1);
		System.out.printf("Googlers with ids %s sorted by salary: %s.%n", 
				ids, emps2);

		System.out.println();
		List<Employee> sampleEmployees = EmployeeSamples.getSampleEmployees();
		// Using limit does not short-circuit the sorting
		List<Employee> emps3 = 
				sampleEmployees.stream()
				.sorted(Person::firstNameComparer)
				.limit(2)
				.collect(Collectors.toList());
		System.out.printf("Employees sorted by first name: %s.%n", 
				emps3);

		// min is faster than sorting and choosing the first
		System.out.println();
		Employee alphabeticallyFirst = 
				ids.stream().map(EmployeeSamples::findGoogler)
				.min((e1, e2) -> e1.getLastName()
						.compareTo(e2.getLastName()))
				.get();
		System.out.printf("Googler from %s with earliest last name: %s.%n", 
				ids, alphabeticallyFirst);
		Employee richest = 
				ids.stream().map(EmployeeSamples::findGoogler)
				.max((e1, e2) -> e1.getSalary() - 
						e2.getSalary())
				.get();
		System.out.printf("Richest Googler from %s: %s.%n", 
				ids, richest);
		
		System.out.println();
		List<Integer> ids2 = 
				Arrays.asList(9, 10, 9, 10, 9, 10);
		List<Employee> emps4 = 
				ids2.stream().map(EmployeeSamples::findGoogler)
				.distinct()
				.collect(Collectors.toList());
		System.out.printf("Unique Googlers from %s: %s.%n", 
				ids2, emps4);
	}
	
	/** Examples of the noneMatch, anyMatch, allMatch, and count methods. */

	public static void matchExamples() 
	{
		List<Employee> googlers = EmployeeSamples.getGooglers();
		boolean isNobodyPoor = 
				googlers.stream().noneMatch(e -> e.getSalary() < 200000);
		Predicate<Employee> megaRich = e -> e.getSalary() > 7000000;
		boolean isSomeoneMegaRich = googlers.stream().anyMatch(megaRich);
		boolean isEveryoneMegaRich = googlers.stream().allMatch(megaRich);
		long numberMegaRich = googlers.stream().filter(megaRich).count();
		
		System.out.printf("Nobody poor? %s.%n", isNobodyPoor);
		System.out.printf("Someone mega rich? %s.%n", isSomeoneMegaRich);
		System.out.printf("Everyone mega rich? %s.%n", isEveryoneMegaRich);
		System.out.printf("Number mega rich: %s.%n", numberMegaRich);
	}
}
