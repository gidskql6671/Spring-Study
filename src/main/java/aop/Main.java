package aop;

import aop.calculator.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        Calculator cal = ctx.getBean(Calculator.class);
        long result = cal.factorial(5);
        System.out.println("cal.factorial(5) = " + result);
        System.out.println(cal.getClass().getName());
        
        ctx.close();
    } 
}
