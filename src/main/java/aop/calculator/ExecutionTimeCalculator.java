package aop.calculator;

public class ExecutionTimeCalculator implements Calculator {

    private final Calculator delegate;

    public ExecutionTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();

        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", 
                delegate.getClass().getSimpleName(), 
                num, (end - start));
        
        return result;
    }
}