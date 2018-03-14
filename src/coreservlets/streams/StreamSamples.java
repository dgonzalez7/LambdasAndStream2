package coreservlets.streams;

import java.util.List;
import java.util.function.Consumer;

public class StreamSamples 
{

	public static void main(String[] args) 
	{
		forEachExamples();
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
}
