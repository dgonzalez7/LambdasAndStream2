package coreservlets.timing2;

@FunctionalInterface
public interface Op 
{
	static final double ONE_BILLION = 1_000_000_000;
	
	void runOp();
	
	// Static method
	static void timeOp(Op operation)
	{
		long startTime = System.nanoTime();
		operation.runOp();
		long endTime = System.nanoTime();
		double elapsedSeconds = (endTime - startTime) / ONE_BILLION;
		System.out.printf("  Elapsed time: %.3f seconds.%n", elapsedSeconds);
	}
	
	// Concrete method
	default Op combinedOp(Op secondOp) 
	{
		return (() -> {
			runOp();
			secondOp.runOp();
		});
	}
}