package com.wanghan.java8.feature.lambda.strategy_pattern;

public class SalaryFilter implements MyFilter<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() < 5000;
    }
}
