package aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CalculatorPointCut {
    
    @Pointcut("execution(public * aop.calculator..*(..))")
    public void calculatorTarget() {

    }
}
