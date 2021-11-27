package com.study.dong;

import com.study.dong.dao.MemberDao;
import com.study.dong.service.MemberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DongApplication.class)
public class AppContext {
}
