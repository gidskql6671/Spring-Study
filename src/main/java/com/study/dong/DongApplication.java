package com.study.dong;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DongApplication {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);
        
        ctx.close();
    }
}
