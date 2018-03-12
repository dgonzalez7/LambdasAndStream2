package coreservlets.function;

import java.util.List;
import java.util.function.Predicate;

import static coreservlets.function.FunctionUtils.*;

public class HigherOrderFunctionsExamples 
{
	private static List<Employee> employees = EmployeeSamples.getSampleEmployees();
	
	public static void main(String[] args) 
	{
		predicateExamples();
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
}
