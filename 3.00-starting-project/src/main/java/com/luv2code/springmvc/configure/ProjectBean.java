package com.luv2code.springmvc.configure;

import com.luv2code.springmvc.models.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProjectBean {

    @Bean
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

    @Bean
    @Scope(value = "prototype")
    Grade getMathGrade(double grade) {
        return new MathGrade(grade);
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("mathGrades")
    MathGrade getGrade() {
        return new MathGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("scienceGrades")
    ScienceGrade getScienceGrade() {
        return new ScienceGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("historyGrades")
    HistoryGrade getHistoryGrade() {
        return new HistoryGrade();
    }

}
