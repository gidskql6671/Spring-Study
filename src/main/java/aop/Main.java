package aop;

public class Main {
    
    public static void main(String[] args) {
        ExecutionTimeCalculator etc1 = new ExecutionTimeCalculator(new IterCalculator());
        System.out.println(etc1.factorial(20));

        ExecutionTimeCalculator etc2 = new ExecutionTimeCalculator(new RecCalculator());
        System.out.println(etc2.factorial(20));
    } 
}
