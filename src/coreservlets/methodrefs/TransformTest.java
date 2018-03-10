package coreservlets.methodrefs;

public class TransformTest 
{
	public static void main(String[] args) 
	{
		String s = "Test";

		// SomeClass::staticMethode
		String result1 = Utils.transform(s, Utils::makeExciting);
		System.out.println(result1);

		// Seems easier...
		String result1b = Utils.makeExciting(s);
		System.out.println(result1b);
		
		// SomeObject::instanceMethod
		String prefix = "Blah";
		String result2 = Utils.transform(s, prefix::concat);
		System.out.println(result2);
		
		// SomeClass::instanceMethod
		String result3 = Utils.transform(s, String::toUpperCase);
		System.out.println(result3);
	}
}