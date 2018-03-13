package coreservlets.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import static coreservlets.function.FunctionUtils.*;

public class HigherOrderFunctionsExamples 
{
	private static List<Employee> employees = EmployeeSamples.getSampleEmployees();
	private static List<String> words = 
		    Arrays.asList("hi", "hello", "hola", "bye", "goodbye", "adios");
	
	public static void main(String[] args) 
	{
		predicateExamples();
		System.out.println();
		transformExamples();
		System.out.println();
		transform2Examples();
		System.out.println();
		consumerExamples();
		System.out.println();
		customHigherOrderFunctionExamples();
	}

	public static void predicateExamples()
	{
		Predicate<Employee> isRich = e -> e.getSalary() > 200_000;
		Predicate<Employee> isEarly = e -> e.getEmployeeId() <= 10;
		System.out.printf("Rich employees: %s.%n", allMatches(employees, isRich));
		System.out.printf("Employees hired early: %s.%n", allMatches(employees, isEarly));

		System.out.printf("Employees that are rich AND hired early: %s.%n", allMatches(employees, isRich.and(isEarly)));
		System.out.printf("Employees that are rich OR hired early: %s.%n", allMatches(employees, isRich.or(isEarly)));
		System.out.printf("Employees that are NOT rich: %s.%n", allMatches(employees, isRich.negate()));

		Employee polly = employees.get(1);
		Predicate<Employee> isPolly = Predicate.isEqual(polly);
		System.out.printf("Employees is the list that are 'equal' to Polly: %s.%n", allMatches(employees, isPolly));
	}

	public static void transformExamples() 
	{

		Function<String,String> makeUpperCase = String::toUpperCase;
		List<String> upperCaseWords = transform(words, makeUpperCase);

		Function<String,String> makeExciting = word -> word + ": Wow!";
		List<String> excitingWords = transform(words, makeExciting);

		Function<String,String> makeBoth1 = makeExciting.compose(makeUpperCase);
		List<String> excitingUpperCaseWords1 = transform(words, makeBoth1);

		Function<String,String> makeBoth2 = makeUpperCase.andThen(makeExciting);
		List<String> excitingUpperCaseWords2 = transform(words, makeBoth2);
		
		System.out.printf("Original words: %s.%n", words);
		System.out.printf("Uppercase: %s.%n", upperCaseWords);
		System.out.printf("Exciting: %s.%n", excitingWords);
		System.out.printf("Exciting uppercase[1]: %s.%n", excitingUpperCaseWords1);
		System.out.printf("Exciting uppercase[2]: %s.%n", excitingUpperCaseWords2);
	}
	
	public static void transform2Examples() 
	{
		Function<String,String> makeUpperCase = String::toUpperCase;
		List<String> upperCaseWords = transform2(words, makeUpperCase);

		Function<String,String> makeExciting = word -> word + ": Wow!";
		List<String> excitingWords = transform2(words, makeExciting);

		List<String> excitingUpperCaseWords = transform2(words, makeExciting, makeUpperCase);
		
		System.out.printf("Original words: %s.%n", words);
		System.out.printf("Uppercase: %s.%n", upperCaseWords);
		System.out.printf("Exciting: %s.%n", excitingWords);
		System.out.printf("Exciting uppercase: %s.%n", excitingUpperCaseWords);
	}
	
	public static void consumerExamples() 
	{
		List<Employee> myEmployees = Arrays.asList(new Employee("Bill", "Gates", 1, 200000),
				new Employee("Larry", "Ellison", 2, 100000)); 
		System.out.println("Original employees:");
		processEntries(myEmployees, System.out::println);
		
		Consumer<Employee> giveRaise = e -> e.setSalary(e.getSalary() * 11 / 10);
		System.out.println("Employees after raise:");
		processEntries(myEmployees, giveRaise.andThen(System.out::println));
	}
	
	public static Predicate<Employee> buildIsRichPredicate(double salaryLowerBound) {
		return(e -> e.getSalary() > salaryLowerBound);
	}

	public static void customHigherOrderFunctionExamples() 
	{
		List<Employee> richEmployees1 = allMatches(employees, buildIsRichPredicate(200_000));
		System.out.printf("Rich employees [via method that returns Predicate]: %s.%n", richEmployees1);
		
		Function<Integer, Predicate<Employee>> makeIsRichPredicate = salaryLowerBound -> (e -> e.getSalary() > salaryLowerBound);
		List<Employee> richEmployees2 = allMatches(employees, makeIsRichPredicate.apply(200_000));
		System.out.printf("Rich employees [via Function that returns Predicate]: %s.%n", richEmployees2);
	}

	// private HigherOrderFunctionExamples() {}
}
