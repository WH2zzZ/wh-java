package com.wanghan.lambda.strategy_pattern;

public class AgeFilter implements MyFilter<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() > 40;
    }
}
