package aop;

import aop.calculator.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        Calculator cal = ctx.getBean(Calculator.class);
        
        cal.factorial(5);
        cal.factorial(5);
        cal.factorial(10);
        
        ctx.close();
    } 
}
