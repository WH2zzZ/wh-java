package com.wanghan.java8.feature.lambda.strategy_pattern;

public class AgeFilter implements MyFilter<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() > 40;
    }
}
