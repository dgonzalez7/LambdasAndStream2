package coreservlets.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSamplesPartitions 
{

	public static void main(String[] args) 
	{
		stringJoinerExamples();
		System.out.println();
		collectExamples();
	}
	
	// To support the tiny StringJoiner example inside the collect examples.

	public static void stringJoinerExamples() 
	{
		StringJoiner joiner1 = new StringJoiner(", ");
		String result1 =
				joiner1.add("Java").add("Lisp").add("Ruby").toString();
		System.out.println(result1);
		
		String result2 =
				String.join(", ", "Java", "Lisp", "Ruby");
		System.out.println(result2);
	}

	public static void collectExamples() 
	{
		List<Integer> ids = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16); // and imagine 1000 more ids
		List<Employee> emps = 
				ids.stream().map(EmployeeSamples::findGoogler)
				.filter(e -> e != null)
				.collect(Collectors.toList());
		System.out.printf("Googlers with ids %s: %s.%n", 
				ids, emps);
		String lastNames = ids.stream().map(EmployeeSamples::findGoogler)
				.filter(e -> e != null)
				.map(Employee::getLastName)
				.collect(Collectors.joining(", "))
				.toString(); 
		System.out.printf("Last names of Googlers with ids %s: %s.%n", 
				ids, lastNames);
		
		System.out.println();
		List<Employee> googlers = EmployeeSamples.getGooglers();
		Set<String> firstNames =
				googlers.stream().map(Employee::getFirstName)
				.collect(Collectors.toSet());
		Stream.of("Larry", "Harry", "Peter", "Deiter", "Eric", "Barack")
		.forEach(s -> System.out.printf
				("%s is a Googler? %s.%n",
						s,
						firstNames.contains(s) ? "Yes" : "No"));
		
		System.out.println();
		TreeSet<String> firstNames2 =
				googlers.stream()
				.map(Employee::getFirstName)
				.collect(Collectors.toCollection(TreeSet::new));
		Stream.of("Larry", "Harry", "Peter", "Deiter", "Eric", "Barack")
		.forEach(s -> System.out.printf
				("%s is a Googler? %s.%n",
						s,
						firstNames2.contains(s) ? "Yes" : "No"));
		
		System.out.println();
		Map<Boolean,List<Employee>> richTable =
				googlers.stream()
				.collect(Collectors.partitioningBy(e -> e.getSalary() > 1000000));
		System.out.printf("Googlers with salaries over $1M: %s.%n", richTable.get(true));
		System.out.printf("Destitute Googlers: %s.%n", richTable.get(false));

		System.out.println();
		Map<String,List<Emp>> officeTable =
				EmpSamples.getSampleEmps().stream().collect(Collectors.groupingBy(Emp::getOffice));
		System.out.printf("Emps in Mountain View: %s.%n", officeTable.get("Mountain View"));
		System.out.printf("Emps in NY: %s.%n", officeTable.get("New York"));
		System.out.printf("Emps in Zurich: %s.%n", officeTable.get("Zurich"));
	}
}
